4.1 Introduction
==========

The motivation for large-scale stream processing is simple: **much of “big data” is received in real time, and is most valuable at its time of arrival**. For example, a social network may wish to detect trending conversation topics in minutes; a search site may wish to model which users visit a new page; and a service operator may wish to monitor program logs to detect failures in seconds. To enable these applications, there is a need for stream processing models that scale to large clusters.

Challenges
------------
1. Indeed, fast recovery is more important in streaming than it was in batch jobs: while a 30 second delay to recover from a fault or straggler is a nuisance in a batch setting, it can mean losing the chance to make a key decision in a streaming setting.

Models
----------
1. **Continuous operator model**: Most distributed streaming systems, including **Storm [14], TimeStream [87], MapReduce Online [34], and streaming databases [18, 26, 29]**, are based on a continuous operator model, in which long-running, stateful operators receive each record, update internal state, and send new records. While this model is quite natural, it makes it difﬁcult to handle faults and stragglers.
   * Speciﬁcally, given the continuous operator model, systems perform **recovery**
through two approaches [58]: 
      - **replication**, where there are two copies of each node [18, 93], 
      - or **upstream backup**, where nodes buffer sent messages and replay them to a new copy of a failed node [87, 34, 14]. 

   Neither approach is attractive in large clusters: replication costs 2* the hardware, while upstream backup takes a long time to recover, as the whole system must wait for a new node to serially rebuild the failed node’s state by rerunning data through an operator. In addition, neither approach handles stragglers: in upstream backup, a straggler must be treated as a failure, incurring a costly recovery step, while replicated systems use synchronization protocols like Flux [93] to coordinate replicas, so a straggler will slow down both replicas.

2. **discretized streams (D-Streams)**: We propose a new stream processing model, discretized streams (D-Streams), that overcomes these challenges. Instead of managing long-lived operators, D-Streams structure streaming computations as a series of **stateless, deterministic batch computations on small time intervals**. For example, we might place the data received every second (or every 100ms) into an interval, and run a MapReduce operation on each interval to compute a count. Similarly, we can run a rolling count over several intervals by adding the new count from each interval to the old result. By structuring computations this way, D-Streams make 
   * (1) the **state** at each timestep fully deterministic given the input data, forgoing the need for synchronization protocols, and 
   * (2) the **dependencies** between this state and older data visible at a ﬁne granularity. We show that this enables powerful recovery mechanisms, similar to those in batch systems, that outperform replication and upstream backup.

Two challenges in realizing the D-Streammodel
---------------------------------------------
1. The ﬁrst is making the **latency (interval granularity) low**. Traditional batch systems, such as Hadoop, fall short here because they keep state in replicated, on-disk storage systems between jobs. Instead, we build on the RDD data structure introduced in Chapter 2, which keeps data in memory and can recover it without replication by tracking the lineage graph of operations used to build it. With RDDs, we show that we can attain end-to-end latencies below a second. We believe that this is sufﬁcient for many real-world big data applications, **where the timescale of the events tracked (e.g., trends in social media) is much higher**.
2. The second challenge is recovering quickly from faults and stragglers. Here, we use the determinism of D-Streams to provide a new recovery mechanism that has not been present in previous streaming systems: **parallel recovery** of a lost node’s state. When a node fails, each node in the cluster works to recompute part of the lost node’s RDDs, resulting in signiﬁcantly faster recovery than upstream backup without the cost of replication. Parallel recovery was hard to perform in continuous processing systems due to the complex state synchronization protocols needed even for basic replication (e.g., Flux [93]),1 but becomes simplewith the fully deterministic D-Stream model. In a similar way, D-Streams can recover from stragglers using speculative execution [36], while previous streaming systems do not handle them.

Advantage of our model 
-------------------------
Finally, because D-Streams use the same processing model and data structures (RDDs) as batch jobs, a powerful advantage of our model is that streaming queries can seamlessly be combined with batch and interactive computation.

4.2 Goals and Background
========================
Many important applications process large streams of data arriving in real time. Our work targets applications that need to run on tens to hundreds of machines, and tolerate a latency of several seconds. Some examples are:

1. **Site activity statistics**: Facebook built a distributed aggregation system called Puma that gives advertisers statistics about users clicking their pages within 10–30 seconds and processes 106 events/s [94].
2. **Cluster monitoring**: Datacenter operators often collect and mine program logs to detect problems, using systems like Flume [9] on hundreds of nodes [52].
3. **Spam detection**: A social network such as Twitter may wish to identify new spam campaigns in real time using statistical learning algorithms [102].

For these applications, we believe that the **0.5–2 second latency** of D-Streams is adequate, as it is well below the timescale of the trends monitored. We purposely **do not target applications with latency needs below a few hundred milliseconds, such as high-frequency trading**.

### Goals
1. Scalability to hundreds of nodes.
2. Minimal cost beyond base processing—we do not wish to pay a 2* replication overhead, for example.
3. Second-scale latency.
4. Second-scale recovery from faults and stragglers.

4.3 Discretized Streams (D-Streams)
===================================
Most previous systems use the same **continuous operator** model. In this model streaming computations are divided into a set of long-lived stateful operators and each operator processes records as they arrive **by updating internal state** (e.g a table tracking page view counts over a window) and sending new records in response [29]. Figure 4.1(a) illustrates.

4.3.1 Computation Model
-------------------------
We treat a streaming computation as **a series of deterministic batch computations on small time intervals**. The data received in each interval is stored reliably across the cluster to form an **input dataset for that interval**. Once the time interval completes, this dataset is processed via **deterministic parallel operations, such as map, reduce and groupBy**, to produce new datasets representing either program outputs or intermediate state. 

1. In the former case, **the results** may be pushed to an external system in a distributed manner. 
2. In the latter case, **the intermediate state** is stored as resilient distributed datasets (RDDs), a fast storage abstraction that avoids replication by using lineage for recovery, as we shall explain. This state dataset may then **be processed along with the next batch of input data** to produce a **new dataset of updated intermediate states**. Figure 4.1(b) shows our model.

We implemented our system, Spark Streaming, based on this model. We used Spark as our **batch processing engine** for each **batch of data**. Figure 4.2 shows a high-level sketch of the computation model in the context of Spark Streaming. This
is explained in more detail later.
In our API, users deﬁne programs by manipulating objects called discretized streams (D-Streams). A D-Stream is a sequence of immutable, partitioned datasets (RDDs) that can be acted on by deterministic transformations. These transformations yield new D-Streams, and may create intermediate state in the form of RDDs.

Spark Streaming exposes D-Streams through a functional API similar to LINQ [115, 3] in the Scala programming language.4 The code for our program is:

    {% highlight scala %}
    pageViews = readStream("http://...", "1s")
    ones = pageViews.map(event => (event.url, 1))
    counts = ones.runningReduce((a, b) => a + b)
    {% endhighlight %}

This code creates a D-Stream called pageViews by reading an event stream over HTTP, and groups these into 1-second intervals. It then transforms the event stream to get a new D-Stream of (URL, 1) pairs called ones, and performs a running count of these with a stateful runningReduce transformation. The arguments to map and runningReduce are Scala function literals.

To execute this program, Spark Streaming will receive **the data stream**, divide it into **one second batches and store them in Spark’s memory as RDDs** (see Figure 4.2).

Additionally, it will invoke RDD transformations like map and reduce to process the RDDs. To execute these transformations, Spark will ﬁrst launch map tasks to process the events and generate the url-one pairs. Then it will launch reduce tasks that take both **the results of the maps** and **the results of the previous interval’s reduces**, stored in an RDD. These tasks will produce a new RDD with the updated counts. Each D-Stream in the program thus turns into a sequence of RDDs. 

Finally, to recover from faults and stragglers, both D-Streams and RDDs track their lineage, that is, the graph of deterministic operations used to build them. Spark tracks this information at the level of partitions within each distributed dataset, as shown in Figure 4.3. When a node fails, it recomputes the RDD partitions that were on it by re-running the tasks that built them from the original input data stored reliably in the cluster. The system also periodically checkpoints state RDDs (e.g., by asynchronously replicating every tenth RDD) to prevent inﬁnite recomputation, but this does not need to happen for all data, because recovery is often fast: the lost partitions can be recomputed in parallel on separate nodes. In a similar way, if a node straggles, we can speculatively execute copies of its tasks on other nodes [36], because they will produce the same result.

    D-Streams can parallelize recovery over hundreds of cores and **recover in 1–2 seconds even when checkpointing every 30s** (x4.6.2).

4.3.2 Timing Considerations
----------------------------

4.3.3 D-Stream API
------------------
Because D-Streams are **primarily an execution strategy** (describing how to break a computation into steps), they can be used to implement many of the standard operations in streaming systems, such as **sliding windows** and **incrementa processing** [29, 15], by simply batching their execution into small timesteps. To illustrate, we describe the operations in Spark Streaming, though other interfaces (e.g., SQL) could also be supported.

1. Deﬁne input streams to be read from outside: The program can deﬁne input streams to be read from outside, which receive data either 
    * by having nodes listen on a port or 
    * by loading it periodically from a storage system (e.g., HDFS). 
2. It can then apply two types of operations to these streams:
   * **Transformations**, which create a new D-Stream from one or more parent streams. These may be stateless, applying separately on the RDDs **in each time interval**, or they may produce state **across intervals**.
   * **Output operations**, which let the program write data to external systems. For example, the save operation will output each RDD in a D-Stream to a database.
3. Finally, the user calls output operators to send results out of Spark Streaming into external systems (e.g., for display on a dashboard). We offer two such operators: **save**, which writes each RDD in a D-Stream to a storage system (e.g., HDFS or HBase), and **foreachRDD**, which runs a user code snippet (any Spark code) on each RDD. For example, a user can print the top K counts with counts.foreachRDD(rdd => print(rdd.top(K))).

### 在每一个time interval中(in each time interval)
D-Streams support the same **stateless** transformations available in typical batch frameworks [36, 115], including map, reduce, groupBy, and join. We provide all the operations in Spark. For example, a program could run a canonical MapReduce word count on each time interval of a D-Stream of words using the following code:

    {% highlight scala %}
    pairs = words.map(w => (w, 1))
    counts = pairs.reduceByKey((a, b) => a + b)
    {% endhighlight %}

### 在time intervals之上/之间(across intervals)
In addition, D-Streams provide several **stateful** transformations for computations spanning multiple intervals, based on standard stream processing techniques such as sliding windows [29, 15]. These include:

1. **Windowing**: The window operation groups all the records from a **sliding window** of past time intervals into one RDD. For example, calling *words.window("5s")* in the code above yields a D-Stream of RDDs containing the words in intervals [0, 5), [1, 6), [2, 7), etc. ~即将**多个interval data**组成**window data**

2. **Incremental aggregation**: For the common use case of computing an aggregate, like a count or max, over a sliding window, D-Streams have several variants of an **incremental reduceByWindow operation**. The simplest one only takes an associative merge function for combining values. For instance, in the code above, one can write:

    {% highlight scala %}
    pairs.reduceByWindow("5s", (a, b) => a + b)
    {% endhighlight %}

    This computes a per-interval count for each time interval only once, but has to add the counts for the past ﬁve seconds repeatedly, as shown in Figure 4.4(a).
    If the aggregation function is also **invertible**, a more efﬁcient version also takes a function for **“subtracting” values** and maintains the state incrementally(Figure 4.4(b)):

    {% highlight scala %}
    pairs.reduceByWindow("5s", (a,b) => a+b, (a,b) => a-b)
    {% endhighlight %}

3. **State tracking**: Often, an application has **to track states for various objects in response to a stream of events indicating state changes**. For example, a program monitoring online video delivery may wish to track the number of active sessions, where a session starts when the system receives a “join” event for a new client and ends when it receives an “exit” event. It can then ask questions such as “how many sessions have a bitrate above X.”

    D-Streams provide a **updateStateByKey** operation that transforms streams of (Key, Event) records into streams of (Key, State) records based on three arguments:
    * An initialize function for creating a State from the ﬁrst Event for a new key.
    * An update function for returning a new State given an old State and an Event for its key.
    * A timeout for dropping old states.

  For example, one could count the active sessions from a stream of (ClientID,Event) pairs called as follows:

    {% highlight scala %}
    sessions = events.track(
    (key, ev) => 1, // initialize function
    (key, st, ev) => (ev == Exit ? null : 1), // update function
    "30s") // timeout
    counts = sessions.count() // a stream of ints
    {% endhighlight %}

  This code sets each client’s state to 1 if it is active and drops it by returning null from update when it leaves. Thus, sessions contains a (ClientID, 1) element for each active client, and counts counts the sessions.

4.3.4 Consistency Semantics
-----------------------------
One beneﬁt of D-Streams is that they provide clean consistency semantics.
Consistency of state across nodes can be a problem in streaming systems that process each record eagerly. 

4.3.5 Uniﬁcation with Batch & Interactive Processing
----------------------------------------------------
Because D-Streams follow the same processing model, data structures (RDDs), and fault tolerance mechanisms as batch systems, the two can seamlessly be combined. Spark Streaming provides several powerful features to unify streaming and batch processing.

1. First, D-Streams can be combined with static RDDs computed using a standard Spark job. For instance, one can join a stream of message events against a precomputed spam ﬁlter, or compare them with historical data.
2. Second, users can run a D-Stream program on previous historical data using a “batch mode.” This makes it easy compute a new streaming report on past data.
3. Third, users run ad-hoc queries on D-Streams interactively by attaching a Scala  console to their Spark Streaming program and running arbitrary Spark operations on the RDDs there. For example, the user could query the most popular words in a time range by typing:

    {% highlight scala %}
    counts.slice("21:00", "21:05").topK(10)
    {% endhighlight %}

  Discussions with developers who have written both ofﬂine (Hadoop-based) and online processing applications show that these features have signiﬁcant practical value.

4.3.6 Summary
-------------
To end our overview of D-Streams, we compare them with continuous operator systems in Table 4.1. The main difference is that D-Streams divide work into small, deterministic tasks operating on batches. This raises their minimum latency, but lets them employ highly efﬁcient recovery techniques. In fact, some continuous operator systems, like TimeStream and Borealis [87, 18], also delay records, in order to deterministically execute operators that have multiple upstream parents (by waiting for periodic “punctuations” in streams) and to provide consistency. This raises their latency past the millisecond scale and into the second scale of D-Streams.

4.4 System Architecture
=========================
As shown in the ﬁgure, Spark Streaming reuses many components of Spark, but we also modiﬁed and added multiple components to enable streaming. We discuss those changes in Section 4.4.2.

Spark Streaming consists of three components:
---------------------------------------------
1. A **master** that tracks the D-Stream lineage graph and schedules **tasks** to compute new RDD partitions.
2. **Worker** nodes that receive data, store the partitions of input and computed
RDDs, and execute tasks.
3. A **client library** used to send data into the system.

4.4.1 Application Execution
----------------------------

4.4.2 Optimizations for Stream Processing
-----------------------------------------

### Sigiﬁcant optimizations and changes to this batch engine to support streaming:
1. **Network communication**: We rewrote Spark’s data plane to use asynchronous I/O to let tasks with remote inputs, such as reduce tasks, fetch them faster.
2. **Timestep pipelining**: Because the tasks inside each timestep may not perfectly utilize the cluster (e.g., at the end of the timestep, there might only be a few tasks left running), we modiﬁed Spark’s scheduler to allow submitting tasks from the next timestep before the current one has ﬁnished. For example, consider our **ﬁrst map + runningReduce** job in Figure 4.3. Because the maps at each step are independent, we can begin **running the maps for timestep 2 before timestep 1’s reduce ﬁnishes**.
3. **Task Scheduling**: We made multiple optimizations to Spark’s task scheduler, such as hand-tuning the size of control messages, to be able to launch parallel jobs of hundreds of tasks every few hundred milliseconds.
4. **Storage layer**: We rewrote Spark’s storage layer to support asynchronous check-pointing of RDDs and to increase performance. Because RDDs are immutable, they can be checkpointed over the network without blocking computations on them and slowing jobs. The new storage layer also uses zero-copy I/O for this when possible.
5. **Lineage cutoff**: Because lineage graphs between RDDs in D-Streams can grow indeﬁnitely, we modiﬁed the scheduler **to forget lineage after an RDD has been checkpointed**, so that its state does not grow arbitrarily. Similarly, other data structures in Spark that grew without bound were given a **periodic cleanup proces**s.
6. **Master recovery**: Because streaming applications need to run 24/7, we added support for recovering the Spark master’s state if it fails (Section 4.5.3).

4.4.3 Memory Management
-------------------------

4.5 Fault and Straggler Recovery
================================

4.5.1 Parallel Recovery
---------------------------

4.5.2 Straggler Mitigation
---------------------------

4.5.3 Master Recovery
---------------------

4.6 Evaluation
================

4.7 Discussion
===============

4.8 Related Work
================
Streaming Databases
Large-scale Streaming
Message Queueing Systems
Incremental Processing
Parallel Recovery

4.9 Summary
===============
































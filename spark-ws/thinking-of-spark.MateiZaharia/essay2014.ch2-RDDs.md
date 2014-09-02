2.1 Introduction
==========

A. Efﬁcient data sharing across computations
-------------
Our work starts from the observation that many of the applications that data ﬂow models were not suited for have a characteristic in common: they all require **efﬁcient data sharing across computations**. For example, 

1. iterative algorithms, such as PageRank, K-means clustering, or logistic regression, need to make multiple passes over the same dataset; 
2. interactive data mining often requires running multiple ad-hoc queries on the same subset of the data; 
3. and streaming applications need to maintain and share state across time. 

Unfortunately, although **data ﬂow frameworks** offer numerous computational operators, they lack efﬁcient primitives for data sharing. In these frameworks, the only way to share data between computations (e.g., between two MapReduce jobs) is to write it to an external stable storage system, e.g., a distributed ﬁle system. This incurs substantial overheads due to data replication, disk I/O, and serialization, which can dominate application execution.

Indeed, examining the specialized frameworks built for these new applications, we see that many of them optimize data sharing. For example, Pregel [72] is a system for iterative graph computations that **keeps intermediate state in memory** while HaLoop [22] is an iterative MapReduce system that can **keep data partitioned in an efﬁcient way across steps**. Unfortunately, these frameworks **only support speciﬁc computation patterns (e.g., looping a series of MapReduce steps)**, and perform data sharing implicitly for these patterns. They do not provide abstractions for more general reuse, e.g., to let a user load several datasets into memory and run ad-hoc queries across them.

Instead, we propose a new abstraction called **resilient distributed datasets (RDDs)** that gives users **direct control of data sharing**. RDDs are fault-tolerant, parallel data structures that let users explicitly store data on disk or in memory, control its partitioning, and manipulate it using a rich set of operators. They offer a simple and efﬁcient programming interface that can capture both current specialized models and new applications.

B. Fault Tolerant
--------------------
The main challenge in designing RDDs is deﬁning a programming interface that can provide fault tolerance efﬁciently. Existing abstractions for in-memory storage on clusters, such as distributed shared memory [79], key-value stores [81], databases, and Piccolo [86], offer an interface based on **ﬁne-grained updates to mutable state (e.g., cells in a table)**. With this interface, the only ways to provide fault tolerance are **to replicate the data across machines** or **to log updates across machines**. Both approaches are expensive for data-intensive workloads, as they require copying large amounts of data over the cluster network, whose bandwidth is far lower than that of RAM, and they incur substantial storage overhead.

In contrast to these systems, RDDs provide an interface based on **coarse-grained transformations (e.g., map, ﬁlter and join)** that apply the same operation to many data items. This allows them to efﬁciently provide fault tolerance by logging the transformations used to build a dataset (its lineage) rather than the actual data. If a partition of an RDD is lost, the RDD has enough information about how it was derived from other RDDs **to recompute just that partition**. Thus, lost data can be recovered, often quite quickly, without requiring costly replication.

Although an interface based on coarse-grained transformations may at ﬁrst seem limited, RDDs are a good ﬁt for many parallel applications, because **these applications naturally apply the same operation to multiple data items**. 

2.2 RDD Abstraction
==========

2.2.1 Deﬁnition
------------
Formally, **an RDD is a read-only, partitioned collection of records**. RDDs can only be created through deterministic operations on either (1) data in stable storage or (2) other RDDs. We call these operations **transformations** to differentiate them from other operations on RDDs. Examples of transformations include map, ﬁlter, and join.

**RDDs do not need to be materialized at all times**. Instead, an RDD has enough information about how it was derived from other datasets (its lineage) to compute its partitions from data in stable storage. This is a powerful property: **in essence, a program cannot reference an RDD that it cannot reconstruct after a failure**.

Finally, users can control two other aspects of RDDs: **persistence** and **partitioning**. Users can indicate which RDDs they will reuse and choose a storage strategy for them (e.g., in-memory storage). They can also ask that an RDD’s elements be partitioned across machines based on a key in each record. This is useful for placement optimizations, such as ensuring that two datasets that will be joined together are hash-partitioned in the same way.

2.2.2 Spark Programming Interface
------------
Spark exposes RDDs through a language-integrated API similar to DryadLINQ [115] and FlumeJava [25], where each **dataset** is represented as an object and **transformations** are invoked using methods on these objects.

Programmers start by deﬁning one or more RDDs through **transformations** on data in stable storage (e.g., map and ﬁlter). They can then use these RDDs in **actions**, which are operations that return a value to the application or export data to a storage system. Examples of actions include count (which returns the number of elements in the dataset), collect (which returns the elements themselves), and save (which outputs the dataset to a storage system). Like DryadLINQ, Spark computes RDDs lazily the ﬁrst time they are used in an action, so that it can pipeline transformations.

In addition, programmers can call a persist method **to indicate which RDDs they want to reuse in future operations**. Spark keeps persistent RDDs in memory by default, but it can spill them to disk if there is not enough RAM. Users can also request other persistence strategies, such as storing the RDD only on disk or replicating it across machines, through ﬂags to persist. Finally, users can set a persistence priority on each RDD to specify which in-memory data should spill to disk ﬁrst.

### Example: Console Log Mining
The Spark scheduler will **pipeline the latter two transformations** and **send a set of tasks to compute them to the nodes holding the cached partitions** of errors. In addition, if a partition of errors is lost, Spark rebuilds it by applying a ﬁlter on only the corresponding partition of lines.

2.2.3 Advantages of the RDD Model
------------------------
To understand the beneﬁts of **RDDs** as a distributed memory abstraction, we compare them against **distributed shared memory (DSM)** in Table 2.1. In DSM systems, applications read and write to arbitrary locations in a global address space.

Note that under this (DSM) deﬁnition, we include not only **traditional shared memory systems** [79], but also other systems where applications **make ﬁne-grained writes to shared state**, including Piccolo [86], which provides a shared DHT, and **distributed databases**. DSM is a very general abstraction, but this generality makes it harder to implement in an efﬁcient and fault-tolerant manner on commodity clusters.

2.2.4 Applications Not Suitable for RDDs
-----------------

2.3 Spark Programming Interface
========================

To use Spark, developers write a **driver program** that connects to a cluster of workers, as shown in Figure 2.2. **The driver deﬁnes one or more RDDs and invokes actions on them**. Spark code on the driver also tracks the RDDs’ lineage. The workers are long-lived processes that can store RDD partitions in RAM across operations.

Users provide arguments to RDD operations like map **by passing closures (function literals)**. Scala **represents each closure as a Java object, and these objects can be serialized and loaded on another node to pass the closure across the network**. Scala also saves any variables bound in the closure as ﬁelds in the Java object. For example, one can write code like var x = 5; rdd.map(_ + x) to add 5 to each element of an RDD.5

2.3.1 RDD Operations in Spark
------------------------
**Transformations** are lazy operations that deﬁne a new RDD without immediately computing it, while **actions** launch a computation to return a value to the program or write data to external storage.

2.3.2 Example Applications
------------------------

2.4 Representing RDDs
=======================

One of the challenges in providing RDDs as an abstraction is choosing a representation for them that **can track lineage** across a wide range of transformations.

In a nutshell, we propose representing each RDD through a common interface that exposes **ﬁve pieces of information**: 

Five pieces of information
---------------------------
1. a set of **partitions**, which are atomic pieces of the dataset; ~ partitions()
2. a set of **dependencies on parent RDDs**;                      ~dependencies()
3. a function for **computing the dataset based on its parents**; ~iterator(p, parentIters)
4. and **metadata about its partitioning scheme**                 ~partitioner()
5. and **data placement**.                                        ~preferredLocations(p)

For example, an RDD representing an HDFS ﬁle has a partition for each block of the ﬁle and knows which machines each block is on. Meanwhile, the result of a map on this RDD has the same partitions, but applies the map function to the parent’s data when computing its elements. We summarize this interface in Table 2.3.

<table>
<tr>
  <td><b>Operation</b>
  </td>
  <td><b>Meaning<b>
  </td>
</tr>

<tr>
  <td>partitions()
  </td>
  <td>Return a list of Partition objects
  </td>
</tr>

<tr>
  <td>preferredLocations(p)
  </td>
  <td>List nodes where partition p can be accessed faster due to data locality
  </td>
</tr>

<tr>
  <td>dependencies()
  </td>
  <td>Return a list of dependencies
  </td>
</tr>

<tr>
  <td>iterator(p, parentIters)
  </td>
  <td>Compute the elements of partition p given iterators for its parent partitions
  </td>
</tr>

<tr>
  <td>partitioner()
  </td>
  <td>Return metadata specifying whether the RDD is hash/range partitioned
  </td>
</tr>
</table>

(How to represent dependencies between RDDs) We found it both sufficient and useful to classify dependencies into two types: 
-----------------------------------------
1. narrow dependencies, where each partition of the parent RDD is used by at most one partition of the child RDD, 
2. wide dependencies, where multiple child partitions may depend on it.(i.e., shuffle dependencies)
For example, map leads to a narrow dependency, while join leads to to wide dependencies (unless the parents are hash-partitioned). Figure 4 shows other examples.

(This distinction is useful for two reasons) We build stages at wide dependencies and pipeline narrow transformations inside each stage.
------------------------------
1. First, narrow dependencies allow for pipelined execution on one cluster node, which can compute all the parent partitions. For example, one can apply a map followed by a filter on an element-by-element basis. In contrast, wide dependencies require data from all parent partitions to be available and to be shuffled across the nodes using a MapReduce-like operation. 
2. Second, recovery after a node failure is more efficient with a narrow dependency, as only the lost parent partitions need to be recomputed, and they can be recomputed in parallel on different nodes. In contrast, in a lineage graph with wide dependencies, a single failed node might cause the loss of some partition from all the ancestors of an RDD, requiring a complete re-execution.

Materialize
----------------
For wide dependencies (i.e., shuffle dependencies), we currently materialize intermediate records on the nodes holding parent partitions to simplify fault recovery, much like MapReduce materializes map outputs.

Some RDD implementations
----------------------
1. **HDFS ﬁles**: The input RDDs in our samples have been ﬁles in HDFS. For these RDDs, partitions returns one partition for each block of the ﬁle (with the block’s offset stored in each Partition object), preferredLocations gives the nodes the block is on, and iterator reads the block.
2. **map**: Calling map on any RDD returns a MappedRDD object. This object has the same partitions and preferred locations as its parent, but applies the function passed to map to the parent’s records in its iterator method.
3. **union**: Calling union on two RDDs returns an RDD whose partitions are the union of those of the parents. Each child partition is computed through a narrow dependency on one parent.
4. **sample**: Sampling is similar to mapping, except that the RDD stores a random number generator seed for each partition to deterministically sample parent records.
5. **join**: Joining two RDDs may lead to either two narrow dependencies (if they are both hash/range partitioned with the same partitioner), two wide dependencies, or a mix (if one parent has a partitioner and one does not). In either case, the output RDD has a partitioner (either one inherited from the parents or a default hash partitioner).

2.5 Implementation
=================
We have implemented Spark in about 34,000 lines of Scala. 

The system runs over diverse cluster managers including
----------------
1. Apache Mesos [56], 
2. Hadoop YARN [109], 
3. Amazon EC2 [4], 
4. and its own built-in cluster manager. 

Each Spark program runs as a separate application on the cluster, with its own driver (master) and workers, and resource sharing between these applications is handled by the cluster manager.

2.5.1 Job Scheduling
-------------------
- Overall, our scheduler is similar to Dryad’s [19], but it additionally takes into account which partitions of persistent RDDs are available in memory. 
- Whenever a user runs an action (e.g., count or save) on an RDD, the scheduler examines that RDD’s lineage graph to build a DAG of stages to execute, as illustrated in Figure 5. 
- Each stage contains **as many pipelined transformations with narrow dependencies as possible**. The boundaries of the stages are the shuffle operations required for wide dependencies, or any already computed partitions that can short-circuit the computation of a parent RDD. The scheduler then launches tasks to compute missing partitions from each stage until it has computed the target RDD.
- If a task fails, we re-run it on another node as long as its stage’s parents are still available. If some stages have become unavailable (e.g., because an output from the “map side” of a shufﬂe was lost), we resubmit tasks to compute the missing partitions in parallel. We do not yet tolerate scheduler failures, though replicating the RDD lineage graph would be straightforward.
- If a task runs slowly (i.e., is a **straggler**), we also launch a speculative backup copy on another node, similar toMapReduce [36], and take the output of whichever copy ﬁnishes ﬁrst.

### 把代码传送到哪里?
Our scheduler assigns tasks to machines based on data locality using delay scheduling [32]. 
     If a task needs to process a partition that is available in memory on a node, we send it to that node. 
     Otherwise, if a task processes a partition for which the containing RDD provides preferred locations (e.g., an HDFS file), we send it to those.

### Materialize
For wide dependencies (i.e., shufﬂe dependencies), we currently materialize intermediate records on the nodes holding parent partitions to simplify fault recovery, much like MapReduce materializes map outputs.

2.5.2 Multitenancy
--------------------

2.5.3 Interpreter Integration
-------------------

### Scala Interpreter 类名是行号,给每行一个类
The Scala interpreter normally operates by compiling a class for each line typed by the user, loading it into the JVM, and invoking a function on it. This class includes a singleton object that contains the variables or functions on that line and runs the line’s code in an initialize method. For example, if the user types var x = 5 followed by println(x), the interpreter deﬁnes a class called Line1 containing x and causes the second line to compile to println(Line1.getInstance().x).

### spark对Scala Interpreter的改造: 
We made two changes to the interpreter in Spark:
-------------------
     1. Class shipping: To let the worker nodes fetch the bytecode for the classes created on each line, we made the interpreter serve these classes over HTTP.
     2. Modiﬁed code generation: Normally, the singleton object created for each line of code is accessed through a static method on its corresponding class. This means that when we serialize a closure referencing a variable deﬁned on a previous line, such as Line1.x in the example above, Java will not trace through the object graph to ship the Line1 instance wrapping around x. Therefore, the worker nodes will not receive x. We modiﬁed the code generation logic to reference the instance of each line object directly.

2.5.4 Memory Management
-------------------

three options for storage of persistent RDDs:
     (1) in-memory storage as deserialized Java objects,
     (2) in-memory storage as serialized data, 
     (3) and on-disk storage. 

The ﬁrst option provides the fastest performance,

To manage the limited memory available, we use an LRU eviction policy at the level of RDDs. When a new RDD partition is computed but there is not enough space to store it, we evict a partition from the least recently accessed RDD, unless this is the same RDD as the one with the new partition. 
In that case, we keep the old partition in memory to prevent cycling partitions from the same RDD in and out. This is important because most operations will run tasks over an entire RDD, so it is quite likely that the partition already in memory will be needed in the future.We found this default policy to work well in all our applications so far, but we also give users further control via a “persistence priority” for each RDD.

2.5.5 Support for Checkpointing
-------------------
Although lineage can always be used to recover RDDs after a failure, such recovery may be time-consuming for RDDs with long lineage chains. Thus, it can be helpful to checkpoint some RDDs to stable storage.

In general, checkpointing is useful for *RDDs with long lineage graphs containing wide dependencies*, such as the rank datasets in our PageRank example (x3.2.2). In these cases, a node failure in the cluster may result in the loss of some slice of data from each parent RDD, requiring a full recomputation [20]. 
In contrast, for *RDDs with narrow dependencies on data in stable storage*, such as the points in our logistic regression example (x3.2.1) and the link lists in PageRank, checkpointing may never be worthwhile. If a node fails, lost partitions from these RDDs can be recomputed in parallel on other nodes, at a fraction of the cost of replicating the whole RDD.

However, we are also investigating how to perform automatic checkpointing. Because our scheduler knows the size of each dataset as well as the time it took to ﬁrst compute it, it should be able to select an optimal set of RDDs to checkpoint to minimize system recovery time [30].

2.6 Evaluation
==================

2.7 Discussion
==============
Although RDDs seem to offer **a limited programming interface due to their immutable nature and coarse-grained transformations**, we have found themsuitable for a wide class of applications. In particular, RDDs can express a surprising number of cluster programming models that have so far been proposed as separate frameworks, allowing users to compose these models in one program (e.g., run a MapReduce operation to build a graph, then run Pregel on it) and share data between them. In this section, we discuss which programming models RDDs can express and why they are so widely applicable (x2.7.1). In addition, we discuss another beneﬁt of the lineage information in RDDs that we are pursuing, which is to facilitate debugging across these models (x2.7.3).

2.7.1 Expressing Existing Programming Models
-------------------------
RDDs can efﬁciently express a number of cluster programming models that have so far been proposed as independent systems. By “efﬁciently,” we mean that not only can RDDs be used to produce the same output as programs written in these models, but that RDDs can also capture the optimizations that these frameworks perform, such as keeping speciﬁc data in memory, partitioning it to minimize communication, and recovering from failures efﬁciently. The models expressible using RDDs include:

1. **MapReduce**: This model can be expressed using the *ﬂatMap* and *groupByKey* operations in Spark, or *reduceByKey* if there is a combiner.
2. **DryadLINQ**: The DryadLINQ system provides a wider range of operators than MapReduce over the more general Dryad runtime, but these are all bulk operators that correspond directly to RDD transformations available in Spark (*map,groupByKey, join*, etc).
3. **SQL**: Like DryadLINQ expressions, SQL queries perform data-parallel operations on datasets, so they can be implemented using RDD transformations. In Chapter 3, we describe an efﬁcient implementation of SQL over RDDs called Shark.
4. **Pregel**: Google’s Pregel [72] is a specialized model for iterative graph applications that at ﬁrst looks quite different from the set-oriented programming models in other systems. In Pregel, a program runs as **a series of coordinated “supersteps.”**
  On each superstep, **each vertex in the graph runs a user function that can update state associated with the vertex, change the graph topology**, and **send messages to other vertices for use in the next superstep**. This model can express many graph algorithms, including shortest paths, bipartite matching, and PageRank. 
  The key observation that lets us implement this model with RDDs is that *Pregel applies the same user function to all the vertices on each iteration*. Thus, we can store the vertex states for each iteration in an RDD and perform a bulk transformation(*ﬂatMap*) to apply this function and generate an RDD of messages. We can then *join* this RDD with the vertex states to perform the message exchange. Equally importantly, RDDs allow us to keep vertex states in memory like Pregel does, to minimize communication by controlling their partitioning, and to support partial recovery on failures. We have implemented Pregel as a 200-line library on top of Spark and refer the reader to [118] for more details.
**Iterative MapReduce**: Several recently proposed systems, including **HaLoop** [22] and **Twister** [37], provide an iterative MapReduce model where the user gives the system **a series of MapReduce jobs to loop**. The systems keep data partitioned consistently across iterations, and Twister can also keep it in memory. Both optimizations are simple to express with RDDs, and we were able to implement HaLoop as a 200-line library using Spark.

2.7.2 Explaining the Expressivity of RDDs
-------------------------
Why are RDDs able to express these diverse programming models? The reason is that the restrictions on RDDs have little impact in many parallel applications. In particular, although RDDs can only be created through bulk transformations, many **parallel programs naturally apply the same operation to many records**, making them easy to express. Similarly, the immutability of RDDs is not an obstacle because one can create multiple RDDs to represent versions of the same dataset. In fact, most of today’s MapReduce applications run over ﬁlesystems that do not allow updates to ﬁles, such as HDFS. In later chapters (3 and 5), we study the expressiveness of RDDs in more detail.

2.7.3 Leveraging RDDs for Debugging
------------------------------------

2.8 Related Work
==================

Cluster Programming Models:
---------------------------
### First, data ﬂow models
First, data ﬂowmodels such asMapReduce [36], Dryad [61] and CIEL [77] support a rich set of operators for processing data but share it through stable storage systems. RDDs represent a more efﬁcient data sharing abstraction than stable storage because they avoid the cost of data replication, I/O and serialization.
### .....

Caching Systems:
-------------------

Relational Databases:
---------------------

2.9 Summary
==============



























The cluster environment comes with several challenges for programmability.
==========
1. parallelism:	
2. faults:	both outright node failures and stragglers (slow nodes)
3. sharing:	 Finally, clusters are typically shared between multiple users, requiring runtimes that can dynamically scale computations up and down and exacerbating the possibility of interference.

A wide range of new programming models for clusters
=============
1.  At ﬁrst, Google’s MapReduce: was found poorly suited for other types of workloads, leading to a wide range of specialized models that differed signiﬁcantly from MapReduce. For example, at Google,
2. a wide range of **specialized** models
  * Pregel: a bulk-synchronous parallel (BSP) model for iterative graph algorithms.
  * F1:  runs fast, but non-fault-tolerant, SQL queries.
  * Storm
  * Impala
  * Piccolo
  * GraphLab

Spark's serveral key advantages over current systems
==============
1. It supports batch, interactive, iterative and streaming computations in the same runtime, enabling rich applications that combine these modes, and offering signiﬁcantly higher performance for these combined applications than disparate systems.
2. It provides fault and straggler tolerance across these computation modes at a very low cost. Indeed, in several areas (streaming and SQL), our RDD-based approach yields new systemdesigns with signiﬁcantly stronger fault tolerance properties than the status quo.
3. It achieves performance that is often 100 better than MapReduce, and comparable with specialized systems in individual application domains.
4. It is highly amenable to multitenancy, allowing applications to scale up and down elastically and share resources in a responsive fashion.

1.1 Problems with Specialized Systems
===============

Models of cluster computing systems:
-------------
1. general computations
  * MapReduce
  * Dryad
2. interactive SQL queries
  * Dremel
  * Impala
3. graph processing
  * Pregel
4. Machine Learning
  * GraphLab

Specialized systems' serveral drawbacks:
-------------
1. Work duplication: Many specialized systems still need to solve the same underlying problems, such as work distribution and fault tolerance. 
2. Composition: It is both expensive and unwieldy to compose computations
in different systems. For “big data” applications in particular, intermediate datasets are large and expensive to move. ... Therefore, pipelines composed of multiple systems are often highly inefﬁcient compared to a uniﬁed system.
3. Limited scope: 
4. Resource sharing:
5. Management and administration:

1.2 Resilient Distributed Datasets (RDDs)
================
a common feature that MapReduce lacks: **efﬁcient data** sharing across parallel computation stages. With an efﬁcient data sharing abstraction and MapReduce-like operators, all of these workloads can be expressed efﬁciently, capturing the key optimizations in current specialized systems. RDDs offer such an abstraction for a broad set of parallel computations, in a manner that is both efﬁcient and fault-tolerant.

fault-tolerant
-----------
structured computations as a **directed acyclic graph (DAG)** of tasks. This allowed them to efﬁciently **replay** just part of the DAG for fault recovery. 

### replication
Between separate computations, however (e.g., between steps of an iterative algorithm), these models provided no storage abstraction other than **replicated ﬁle systems**, which add signiﬁcant costs due to data replication across the network.
### spark: avoid replication
RDDs are a fault-tolerant distributed memory abstraction that **avoids replication**. Instead, each RDD **remembers the graph of operations used to build it**, similarly to batch computing models, and can efﬁciently recompute data lost on failure. As long as the operations that create RDDs are relatively coarse-grained, i.e., **a single operation applies to many data elements**, this technique is much more efﬁcient than replicating the data over the network. RDDs work well for a wide range of today’s data-parallel algorithms and programming models, all of which apply each operation to many items.

1.3 Models Implemented on RDDs
==============
Iterative Algorithms
--------------
such as those used in graph processing, numerical optimization, andmachine learning. RDDs can capture a wide variety of such models, including **Pregel**, iterative MapReduce models like **HaLoop** and **Twister**, and a deterministic version of the **GraphLab** and **PowerGraph** models .

Relational Queries
----------------
both as long-running, many-hour batch jobs and as interactive queries.

MapReduce
------------

Stream Processing
------------

Combining these Models
----------------

1.4 Summary of Results
=================
In particular current systems are based on a **continuous operator model**, where **long-lived stateful operators** process each record as it arrives. To recover a lost node, they need to either keep two copies of each operator, or replay upstream data through a costly serial process.

We propose a new model, **discretized streams (D-Streams)**, that overcomes this problem. Instead of using long-lived stateful processes, D-streams execute streaming computations as **a sequence of short, deterministic batch computations, storing state in RDDs in-between**. The D-Stream model allows fast fault recovery without replication by parallelizing the recovery along the dependency graph of the RDDs involved. In addition, it supports straggler mitigation through speculative execution [36], i.e., running speculative backup copies of slow tasks. 


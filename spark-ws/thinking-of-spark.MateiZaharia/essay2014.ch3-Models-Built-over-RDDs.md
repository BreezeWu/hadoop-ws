3.1 Introduction
==========

To recap previous chapter, RDDs provide the following facilities:
-------------
1. Immutable storage of arbitrary records across a cluster (in Spark, the records
are Java objects).
2. Control over data partitioning, using a key for each record.
3. Coarse-grained operators that take into account partitioning.
4. Low latency due to in-memory storage.

We next show how these have been used to implement more sophisticated
processing and storage.

3.2 Techniques for Implementing Other Models on RDDs
=====================================================
In specialized engines, not only **the operators on data** but **its storage format and access methods** are optimized. For example, a SQL engine like Shark may process data in a column-oriented format, while a graph engine like GraphX may perform best when data is indexed. We discuss common ways that have emerged to implement these optimizations with RDDs, leveraging the fault tolerance and composition beneﬁts of the RDD model while keeping the performance of specialized systems.

3.2.1 Data Format Within RDDs
-----------------------------
For example, in analytical databases, one common optimization is column-oriented storage and compression. In the Shark system, we store multiple database records in one Spark record and apply these optimizations. 

3.2.2 Data Partitioning
------------------------
A second optimization common in specialized models is partitioning data across a cluster in domain-speciﬁc ways to speed up an application. For example, Pregel and HaLoop keep data partitioned using a possibly user-deﬁned function to speed up joins against a dataset. Parallel databases commonly provide various forms of partitioning as well.

3.2.3 Dealing with Immutability
---------------------------
Fundamentally it is no different from **having mutable datasets** and **tracking version numbers for these purposes**. However, one might ask whether it leads to inefﬁciency.

Two techniques can yield good performance in many cases:
1. Compound data structures represented as **multiple co-partitioned RDDs**, as in the PageRank example in the previous section, allow the program to mutate only the part of the state that need to change. In many algorithms, some ﬁelds of records never change while others change on almost every iteration, so this approach can capture them efﬁciently.
2. Even within a record, **pointers can be used to reuse state from the previous “version” of the record when the internal data structures are immutable**. For example, strings in Java are immutable, so a map on a (Int, String) record that changes the Int but keeps the same String will just have a pointer to the previous String object, without copying it. More generally, persistent data structures from functional programming [64] can be used to represent incremental updates to other forms of data (e.g., hash tables) as a delta from a previous version. It is quite pleasant that many ideas from functional programming can directly help RDDs.

3.2.4 Implementing Custom Transformations
--------------------------------------------

3.3 Shark: SQL on RDDs
======================

3.3.1 Motivation
------------------

### Modern data analysis faces several challenges
1. First, data volumes are expanding quickly, creating the need to scale out across clusters of hundreds of commodity machines. 
2. Second, this scale increases the incidence of faults and stragglers (slow tasks), complicating parallel database design. 
3. Third, the complexity of data analysis has also grown: modern data analysis employs sophisticated statistical methods,such as machine learning algorithms, that go well beyond the roll-up and drill-down capabilities of traditional enterprise data warehouse systems. 
4. Finally, despite these increases in scale and complexity, users still expect to be able to query data at interactive speeds.

### Two major lines of systems
1. The ﬁrst, consisting of **MapReduce and various generalizations** : However, MapReduce engines lack many of the features that make databases efﬁcient, and thus exhibit high latencies of tens of seconds to hours. Even systems that have signiﬁcantly optimized MapReduce for SQL queries,such as Google’s Tenzing [27], or that combine it with a traditional database on each node, such as HadoopDB [1], report a minimum latency of 10 seconds.
2. Instead, most **MPP(massively parallel processing) analytic databases (e.g., Vertica, Greenplum, Teradata)** and several of the new low-latency engines built for MapReduce environments (e.g., Google F1 [95], Impala [60]) employ a coarser-grained recovery model, where an entire query must be resubmitted if a machine fails. This works well for short queries where a retry is cheap, but faces signiﬁcant challenges for long queries as clusters scale up [1]. In addition, these systems often *lack the rich analytics functions* that are easy to implement in MapReduce, such as machine learning and graph algorithms.

### Shark
1. First, to store and process relational data efﬁciently, we implemented **in-memory columnar storage and compression**.
2. Second, to optimize SQL queries based on the data characteristics even in the presence of analytics functions and UDFs, we extended Spark with **Partial DAG Execution (PDE)**: Shark can reoptimize a running query after running the ﬁrst few stages of its task DAG, choosing better join strategies or the right degree of parallelism based on observed statistics. 
3. Third, we leverage other properties of the Spark engine not present in traditional MapReduce systems, such as **control over data partitioning**.

Our implementation of Shark is compatible with Apache Hive [104], **supporting all of Hive’s SQL dialect and UDFs and allowing execution over unmodiﬁed Hive data warehouses**. It **augments SQL with complex analytics functions written in Spark, using Spark’s Java, Scala or Python APIs**. These functions can be combined with SQL in a single execution plan, providing in-memory data sharing and fast recovery across both types of processing.

3.4 Implementation
====================

3.4.1 Columnar Memory Store
-----------------------------

3.4.2 Data Co-partitioning
---------------------------

3.4.3 Partition Statistics and Map Pruning
-------------------------------------------

3.4.4 Partial DAG Execution (PDE)
---------------------------------

3.5 Performance
================

3.5.1 Methodology and Cluster Setup
-----------------------------------

3.5.2 Pavlo et al. Benchmarks
------------------------------

3.5.3 Microbenchmarks
-------------------------

3.5.4 Fault Tolerance
----------------------

3.5.5 Real Hive Warehouse Queries
---------------------------------

3.6 Combining SQL with Complex Analytics
========================================

3.6.1 Language Integration
----------------------------

3.6.2 Execution Engine Integration
-----------------------------------

3.6.3 Performance
------------------

3.7 Summary
===========



































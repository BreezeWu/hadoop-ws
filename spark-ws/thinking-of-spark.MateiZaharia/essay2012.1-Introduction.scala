// ----------------------------------------------------------------------------
// 1.Introduction

// RDDs are motivated by two types of applications that current computing frameworks handle inefficiently: iterative algorithms and interactive data mining tools.

// (1) interative algorithms
// (2) interactive data mining tools

// To achieve fault tolerance efficiently, RDDs provide a restricted form of shared memory, based on coarse-grained transformations rather than fine-grained updates to shared state. 

// (1) Data reuse is common in many iterative machine learning and graph algorithms, including PageRank, K-means clustering, and logistic regression. 
// (2) Another compelling use case is interactive data mining, where a user runs multiple ad-hoc queries on the same subset of the data. 

// Unfortunately, in most current frameworks, the only way to reuse data between computations (e.g., between two MapReduce jobs) is to write it to an external stable storage system, e.g., a distributed file system.
// This incurs substantial overheads due to data replication, disk I/O, and serialization, which can dominate application execution times.

// Recognizing this problem, researchers have developed specialized frameworks for some applications that require data reuse. 
// For example, Pregel [22] is a system for iterative graph computations that keeps intermediate data in memory, while HaLoop [7] offers an iterative MapReduce interface. However, these frameworks only support specific computation patterns (e.g., looping a series of MapReduce steps), and perform data sharing implicitly for these patterns.

// RDDs
// RDDs are fault-tolerant, parallel data structures that let users explicitly persist intermediate results in memory, control their partitioning to optimize data placement, and manipulate them using a rich set of operators.

// 复制(deplication)或日志回滚的机制
// Existing abstractions for in-memory storage on clusters, such as distributed shared memory [24], key-value stores [25], databases, and Piccolo [27], offer an interface based on fine-grained updates to mutable state (e.g., cells in a table).
// With this interface, the only ways to provide fault tolerance are to replicate the data across machines or to log updates across machines. 
// Both approaches are expensive for data-intensive workloads, as they require copying large amounts of data over the cluster network, whose bandwidth is far lower than that of RAM, and they incur substantial storage overhead.

// 重新计算的机制
// In contrast to these systems, RDDs provide an interface based on coarse-grained transformations (e.g., map, filter and join) that apply the same operation to many data items. 
// This allows them to efficiently provide fault tolerance by logging the transformations used to build a dataset (its lineage) rather than the actual data.
// 注意:   Checkpointing the data in some RDDs may be useful when a lineage chain grows large, however, and we discuss how to do it in §5.4.

// Although an interface based on coarse-grained transformations may at first seem limited, RDDs are a good  fit for many parallel applications, because *these applications naturally apply the same operation to multiple data items*. 

//  Spark provides a convenient language-integrated programming interface similar to DryadLINQ [31] in the Scala programming language [2]. In addition, Spark can be used interactively to query big datasets from the Scala interpreter. 






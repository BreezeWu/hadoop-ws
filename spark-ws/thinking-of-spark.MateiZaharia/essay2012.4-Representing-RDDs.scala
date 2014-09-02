// ----------------------------------------------------------------------------
// 4 Representing RDDs

// One of the challenges in providing RDDs as an abstraction is choosing a representation for them that can track lineage across a wide range of transformations.

// In a nutshell, we propose representing each RDD through a common interface that exposes five pieces of information: 
//  (1) a set of partitions, which are atomic pieces of the dataset;        ~ partitions()
//  (2) a set of dependencies on parent RDDs;                                           ~dependencies()
//  (3) a function for computing the dataset based on its parents;      ~iterator(p, parentIters)
//  and (4) metadata about its partitioning scheme                              ~partitioner()
//  and (5) data placement.                                                                         ~preferredLocations(p)

// For example, an RDD representing an HDFS file has a partition for each block of the file and knows which machines each block is on. Meanwhile, the result of a map on this RDD has the same partitions, but applies  the map function to the parent’s data when computing its elements. We summarize this interface in Table 3.

// how to represent dependencies between RDDs.
// We found it both sufficient and useful to classify dependencies into two types: 
//      (1) narrow dependencies, where each partition of the parent RDD is used by at most one partition of the child RDD, 
//      (2) wide dependencies, where multiple child partitions may depend on it.        ~ i.e., shuffle dependencies
// For example, map leads to a narrow dependency, while join leads to to wide dependencies (unless the parents are hash-partitioned).
// Figure 4 shows other examples.

// ==> we build build stages at wide dependencies and pipeline narrow transformations inside each stage.

// This distinction is useful for two reasons. 
//      First, narrow dependencies allow for pipelined execution on one cluster node, which can compute all the parent partitions. For example, one can apply a map followed by a filter on an element-by-element basis. In contrast, wide dependencies require data from all parent partitions to be available and to be shuffled across the nodes using a MapReduce-like operation. 
//      Second, recovery after a node failure is more efficient with a narrow dependency, as only the lost parent partitions need to be recomputed, and they can be recomputed in parallel on different nodes. In contrast, in a lineage graph with wide dependencies, a single failed node might cause the loss of some partition from all the ancestors of an RDD, requiring a complete re-execution.

// For wide dependencies (i.e., shuffle dependencies), we currently materialize intermediate records on the nodes holding parent partitions to simplify fault recovery, much like MapReduce materializes map outputs.



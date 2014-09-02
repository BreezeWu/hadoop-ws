// ----------------------------------------------------------------------------
// 3 Spark Programming Interface

// However, nothing about the RDD abstraction requires a functional language.

//  driver program
// To use Spark, developers write a *driver program* that connects to a cluster of workers, as shown in Figure 2.
// The driver defines one or more RDDs and invokes actions on them. Spark code on the driver also tracks the RDDs’ lineage. The workers are long-lived processes that can store RDD partitions in RAM across operations.

// Scala’s closure objects
// As we showed in the log mining example in Section 2.2.1, users provide arguments to RDD operations like map by passing closures (function literals).
// Scala represents each closure as a Java object, and these objects can be serialized and loaded on another node to pass the closure across the network. Scala also saves any variables bound in the closure as fields in the Java object. For example, one can write code like var x = 5; rdd.map(_ + x) to add 5 to each element of an RDD.

// 3.1 RDD Operations in Spark
// 3.2 Example Applications



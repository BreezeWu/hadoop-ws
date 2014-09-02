// ----------------------------------------------------------------------------
// 5 Implementation

// We now sketch several of the technically interesting parts of the system: our job scheduler (§5.1), our Spark interpreter allowing interactive use (§5.2), memory management (§5.3), and support for checkpointing (§5.4).

// ----------------------------------------------------------------------------
// 5.1 Job Scheduling

// Overall, our scheduler is similar to Dryad’s [19], but it additionally takes into account which partitions of persistent RDDs are available in memory. 
// Whenever a user runs an action (e.g., count or save) on an RDD, the scheduler examines that RDD’s lineage graph to build a DAG of stages to execute, as illustrated in Figure 5. 
// Each stage contains as many pipelined transformations with narrow dependencies as possible. The boundaries of the stages are the shuffle operations required for wide dependencies, or any already computed partitions that can short-circuit the computation of a parent RDD. The scheduler then launches tasks to compute missing partitions from each stage until it has computed the target RDD.

// 把代码传送到哪里?
// Our scheduler assigns tasks to machines based on data locality using delay scheduling [32]. 
//      If a task needs to process a partition that is available in memory on a node, we send it to that node. 
//      Otherwise, if a task processes a partition for which the containing RDD provides preferred locations (e.g., an HDFS file), we send it to those.

// ----------------------------------------------------------------------------
// 5.2 Interpreter Integration

// Scala Interpreter 类名是行号,给每行一个类
// The Scala interpreter normally operates by compiling a class for each line typed by the user, loading it into the JVM, and invoking a function on it. This class includes a singleton object that contains the variables or functions on that line and runs the line’s code in an initialize method. For example, if the user types var x = 5 followed by println(x), the interpreter deﬁnes a class called Line1 containing x and causes the second line to compile to println(Line1.getInstance().x).

// spark对Scala Interpreter的改造: 
// We made two changes to the interpreter in Spark:
//      1. Class shipping: To let the worker nodes fetch the bytecode for the classes created on each line, we made the interpreter serve these classes over HTTP.
//      2. Modiﬁed code generation: Normally, the singleton object created for each line of code is accessed through a static method on its corresponding class. This means that when we serialize a closure referencing a variable deﬁned on a previous line, such as Line1.x in the example above, Java will not trace through the object graph to ship the Line1 instance wrapping around x. Therefore, the worker nodes will not receive x. We modiﬁed the code generation logic to reference the instance of each line object directly.

// ----------------------------------------------------------------------------
// 5.3 Memory Management

// three options for storage of persistent RDDs:
//      (1) in-memory storage as deserialized Java objects,
//      (2) in-memory storage as serialized data, 
//      (3) and on-disk storage. 

// The ﬁrst option provides the fastest performance,

// To manage the limited memory available, we use an LRU eviction policy at the level of RDDs. When a new RDD partition is computed but there is not enough space to store it, we evict a partition from the least recently accessed RDD, unless this is the same RDD as the one with the new partition. 
// In that case, we keep the old partition in memory to prevent cycling partitions from the same RDD in and out. This is important because most operations will run tasks over an entire RDD, so it is quite likely that the partition already in memory will be needed in the future.We found this default policy to work well in all our applications so far, but we also give users further control via a “persistence priority” for each RDD.

// ----------------------------------------------------------------------------
// 5.4 Support for Checkpointing

// In general, checkpointing is useful for *RDDs with long lineage graphs containing wide dependencies*, such as the rank datasets in our PageRank example (x3.2.2). In these cases, a node failure in the cluster may result in the loss of some slice of data from each parent RDD, requiring a full recomputation [20]. 
// In contrast, for *RDDs with narrow dependencies on data in stable storage*, such as the points in our logistic regression example (x3.2.1) and the link lists in PageRank, checkpointing may never be worthwhile. If a node fails, lost partitions from these RDDs can be recomputed in parallel on other nodes, at a fraction of the cost of replicating the whole RDD.

// However, we are also investigating how to perform automatic checkpointing. Because our scheduler knows the size of each dataset as well as the time it took to ﬁrst compute it, it should be able to select an optimal set of RDDs to checkpoint to minimize system recovery time [30].









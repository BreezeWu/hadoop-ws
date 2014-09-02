5.1 Introduction
==========
Nonetheless, the question remains: **why were RDDs so general? Why were they able to approach the performance of specialized systems, and what are the limitations of the model?**

In this chapter, we study these questions by exploring the generality of RDDs from two perspectives. 

1. First, from an **expressiveness point** of view, we show that RDDs can emulate **any** distributed system, and will do so **efﬁciently** in many cases unless the system is sensitive to network latency. In particular, adding data sharing to MapReduce enables this efﬁcient emulation. 
2. Second, from a **systems point** of view, we show that RDDs gave applications control over the most common bottleneck resources in cluster applications (speciﬁcally network and storage I/O), which makes it possible to express the same optimizations for these resources that specialized systems have, and hence achieve similar performance. 
3. Finally, these explorations of RDDs’ generality also identify limitations of the model where it may not emulate other distributed systems efﬁciently, and lead to several possible areas for extensions.

5.2 Expressiveness Perspective
==============================
To characterize the expressiveness of RDDs from a theoretical perspective, we start by **comparing RDDs to the MapReduce model**, which they derive from and build upon. MapReduce was initially used for a wide range of cluster computations, from SQL [104] to machine learning [12], but was gradually replaced by specialized systems.

5.2.1 What Computations can MapReduce Capture?
---------------------------------------
**MapReduce can emulate any distributed computation.**
To see this, note that any distributed system is composed of nodes that perform local computation and occasionally exchange messages. MapReduce offers the **map** operation, which allows local computation, and **reduce**, which allows all-to-all communication. Thus, any distributed system can be emulated (perhaps somewhat inefﬁciently) by breaking down its computation into timesteps, running maps to perform the local computation in each timestep, and batching and exchanging messages at the end of each timestep. A series of MapReduce steps is thus enough to capture the whole result. Figure 5.1 shows how such steps would execute.

5.2.2 Lineage and Fault Recovery
--------------------------
5.2.3 Comparison with BSP
--------------------------
As a second example of the generality of MapReduce and RDDs, we note that the “local computation and all-to-all communication” model discussed above very closely matches Valiant’s Bulk Synchronous Parallel (BSP) model [108]. BSP is a “bridging model” designed to capture the most salient aspects of real hardware (namely that communication has latency, and synchronization is expensive) while being simple enough to analyze mathematically. Thus, not only has it been used directly to design some parallel algorithms, but its cost factors (namely the number of communication steps, amount of local computation on each step, and amount of data communicated by each processor on each step) are natural factors to optimize in most parallel applications. In this sense, we can expect that algorithms that map well to BSP can be evaluated efﬁciently with RDDs.

5.3 Systems Perspective
====================
5.3.1 Bottleneck Resources
--------------------------
5.3.2 The Cost of Fault Tolerance
---------------------------------------

5.4 Limitations and Extensions
==============================
5.4.1 Latency
-------------
5.4.2 Communication Patterns Beyond All-to-All
----------------------------------------------------
5.4.3 Asynchrony
-------------
5.4.4 Fine-Grained Updates
-------------
5.4.5 Immutability and Version Tracking
-------------

5.5 Related Work
==========

5.6 Summary
==========
TODO LIST @ TODAY
=======================
Functional Programming in Scala.2013.pdf
Docker

Things that go together
=======================
Cassandra(OLTP Data Center) + Spark(Analytics OLAP Data Center)
Hadoop + Hive
Spark + Shark(Spark SQL)
Hdfs + tachyon

agenda list: 
1. Cassandra Summit 2014 + Spark Summit 2014
2. SharkServer/ "Job Server"
3. ElasticSearch
4. Model Server

5. Cascading

Technical Stack
=====================

Execution Engines
-------------------
   Batch:   MR v1 & v2, Pig, Cascading, Spark, Tez
ML,Graph:   Mahout, MLlib, GraphX
     SQL:   Hive, Impala, Shark, Drill
NoSQL&Search:HBase, Solr, Accumulo
Streaming:  Spark Streaming, Storm

Data Governance and Operations
-----------------------------
Data Integration & Access:  Sqoop, Flume, HttpFS, Hue
                 Security:  Sentry, Knox
     Workflow & Data Gov.:  Oozie, Falcon   
                Provision:  ZooKeeper, Whirr, Juju, Savannah

Spark on hadoop
---------------
The Complete Spark Stack on Hadoop : Spark, MLlib, GraphX, Shark(Spark SQL), Spark Streaming
(1) Spark Advantages: 
    . EASE OF DEVELOPMENT, 
    . IN-MEMORY PERFORMANCE, 
    . COMBINE WORKFLOWS
(2) Hadoop Advantages:
    . UNLIMITED SCALE
    . ENTERPRISE PLATFORM
    . WIDE RANGE OF APPLICATIONS

xPattern Architecture
=====================

level 0: Virtual Private Cloud
-------------------------------

level 1: Infrastructure, "ETL Engineer"
--------------------------------------
Hadoop:     MapR, ...
No-SQL:     Cassandra, ... DataStax
Search:     Lucene, Solr, ...
ETL:        Talend, ...
Real-Time:  Storm, ...
Interactive: Spark, ...
Log Agg:    Flume
Pub-Sub:    Kafka, ...

agenda list: Cassandra

level 2: Intelligence, "Data Scientist"
--------------------------------------
Information Retrieval
Classification
Inference
Natural Lanaguage
Topic Modeling
Data Mining
Prediction
Optimization

level 3: Application, "Application Engineer"
-------------------------------------------
Web Services
Visualization
Dashboards

agenda list: Ganglia + Nagios & Graphite, Jaws(Shark), Spark Job Server, 

Big Data Platforms => Big Data Applications
=======================================

Spark : An SDK for Big Data Applications, Core + (SQL-MLlib-Streaming-GraphX)

Develop Big Data Applications
---------------
Your Application --> Spark

Develop Applications...
... using your preferred language, Python/Scala/Java
... using existing libraries,
... using Spark’s Public APIs, (SparkContext, RDDs)

Work With Data
---------------
Your Application --> Spark --> Data(HDFS-local-S3-JDBC-Cassandra-...)

.Spark Internals Care For Scheduling Data Operations

Run Your Applications
---------------
Your Application --> Spark --> Cluster(YARN-Mesos-Spark Standalone-...)

. Submit Your Application and the Spark Runtime to a Cluster Manager

The Complete Picture
---------------
Your Application --> Spark --> Cluster --> Data

. Spark Abstracts Runtime Dependencies from Developers
. The Spark library is compiled with compatibility to a specific Hadoop version, "Hadoop Client"
. At runtime, Spark uses reflection to find any Hadoop classes it needs

Spark
=======================================
agenda list: Spark SQL(SQL+DSL)


AMPLab: Integrating 3 Resources
=====================

1. Algorithms:
•  Machine Learning, Statistical Methods	
•  Prediction, Business	Intelligence

2. Machines
•  Clusters and Clouds
•  Warehouse Scale Computing

3. People
•  Crowdsourcing, Human Computation
•  Data	Scientists, Analysts

BDAS: Berkeley Data Analytics Stack
-----------------------------------
The Big Picture: 
•  Serving Models & Data
•  Enables real-time Analytics and Model Updates

The big breakthrough of the “Big Data” ecosystem isn’t scalability	
It’s not really speed either	


It’s flexibility!	


The (Traditional) Big Picture
-----------------------------
Application Databases => ETL => Data WareHouse => Data products, Business Intelligence, Analytics
•  Database Philosophy: One	way in/out --- through the SQL door at the top
•  OLTP + OLAP story	












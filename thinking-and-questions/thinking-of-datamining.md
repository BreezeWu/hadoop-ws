
====
跳出具体场景/具体算法,数据挖掘之路?

1. 结果应用: 挖掘成功怎么向业务/经营策略提供知识或建议?  与人或系统交互
2. 数据质量: 究竟是人为数据问题还是系统数据波动
3. 

====
大数据之上的数据挖掘?


====
图计算

社区关系发现，邻居相似性，

信息蔓延

1. 传统图计算
table view  + graph view 这二者是分离的

2. spark的图计算
table view  + graph view 这二者是整合的

谷歌传统三驾马车： gfs, mapreduce, bigtable
谷歌新三驾马车： pregel graphlab
		Caffeine、Pregel、Dremel

====
spark graphx 《Spark GraphX大规模图计算和图挖掘》
1.property graph
2.图的切分： 边切分(性能低) 与 顶点切分

vertex table , 底层的routing table
edge table

3. triplet = edges.leftouterjoin(vertices)
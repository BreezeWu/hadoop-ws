# -----------------------------------------------------------------------------
# 分析过程中的各个结果 s01
# -----------------------------------------------------------------------------

s01-m1

# -----------------------------------------------------------------------------
# 选择的数据
月用电量(vpm), 实收(rcved_amt), 应收(rcvbl_amt), 欠费(owning_amt)

# 聚类调用
tryKMeansSmart(parsedDataM1,1,1000,20) # 此时,20这个参数不生效

# 聚类结果
# 过程Metrics: 根据计算的先后顺序(分裂顺序)
# /user/spark/metric/k-means-2014-07-28-15-57-39_c15_try15_0
1, 20, 1.137275957094375E12, 2014-07-28 15:57:39, 2014-07-28 15:57:50, org.apache.spark.mllib.clustering.KMeansModel@64becc07
1000, 20, 8.855389556590284E7, 2014-07-28 15:57:50, 2014-07-28 16:00:12, org.apache.spark.mllib.clustering.KMeansModel@6ddf628b
500, 20, 1.5920572239430958E8, 2014-07-28 16:00:12, 2014-07-28 16:00:45, org.apache.spark.mllib.clustering.KMeansModel@65fd1df7
750, 20, 1.148362902319431E8, 2014-07-28 16:00:45, 2014-07-28 16:01:50, org.apache.spark.mllib.clustering.KMeansModel@44b4585d
875, 20, 9.871133131502612E7, 2014-07-28 16:01:50, 2014-07-28 16:03:31, org.apache.spark.mllib.clustering.KMeansModel@4041c345
937, 20, 9.357941979276025E7, 2014-07-28 16:03:31, 2014-07-28 16:05:32, org.apache.spark.mllib.clustering.KMeansModel@2c4d583f
968, 20, 9.148399591759886E7, 2014-07-28 16:05:32, 2014-07-28 16:07:39, org.apache.spark.mllib.clustering.KMeansModel@797d3f1f
984, 20, 8.864175603974956E7, 2014-07-28 16:07:39, 2014-07-28 16:09:54, org.apache.spark.mllib.clustering.KMeansModel@6f5f433c
992, 20, 8.7072893540299E7, 2014-07-28 16:09:54, 2014-07-28 16:12:06, org.apache.spark.mllib.clustering.KMeansModel@39aca504
988, 20, 8.989289239403799E7, 2014-07-28 16:12:06, 2014-07-28 16:14:31, org.apache.spark.mllib.clustering.KMeansModel@3c8c49c4
990, 20, 8.808272289137706E7, 2014-07-28 16:14:31, 2014-07-28 16:16:47, org.apache.spark.mllib.clustering.KMeansModel@7d8147e7
991, 20, 8.836730029169056E7, 2014-07-28 16:16:47, 2014-07-28 16:19:01, org.apache.spark.mllib.clustering.KMeansModel@1a095b16
996, 20, 8.939709981292304E7, 2014-07-28 16:19:01, 2014-07-28 16:21:19, org.apache.spark.mllib.clustering.KMeansModel@58cbe66d
994, 20, 8.844615370922044E7, 2014-07-28 16:21:19, 2014-07-28 16:23:41, org.apache.spark.mllib.clustering.KMeansModel@7f4e158a
993, 20, 8.783361199603339E7, 2014-07-28 16:23:41, 2014-07-28 16:25:56, org.apache.spark.mllib.clustering.KMeansModel@5fac99ae
# 过程Metrics: 根据WSSSE从小到大排序
# /user/spark/metric/k-means-2014-07-28-15-57-39_c15_try15_1

# 最佳K的中心点
# bestK = 992
# 参见文件  clustersCenter_S01_M1_VPM_AMT_0.csv

# -----------------------------------------------------------------------------


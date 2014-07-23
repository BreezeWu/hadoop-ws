	// �����������ݴ���һ��KMeansModel. Ϊ"demo-spark-03-mllib-kmeans-s98-smart.scala"����
	//val clustersOfK: org.apache.spark.mllib.clustering.KMeansModel = clusters
			// begin ------------------------------ ��hiveȡ���� ###����ҵ�����###
			import org.apache.spark.mllib.linalg.Vectors
			import org.apache.spark.mllib.linalg.distributed.RowMatrix
			
			val hiveContext = new org.apache.spark.sql.hive.HiveContext(sc)
			val rddFromHive = hiveContext.hql("SELECT cons_id, ym, volume_per_month FROM bigdata_arc_volume_perm_s01")
			
			// ת��Ϊvector,����Ϊmatrices
			val parsedData = rddFromHive.map(x => Array(
					x(0).toString.toDouble,x(1).toString.toDouble,x(2).toString.toDouble)
				).map(x => Vectors.dense(x)).cache()
			
			// end ------------------------------ ��hiveȡ���� ###����ҵ�����###
			
			// begin =========================================== k-means ��������
			import org.apache.spark.mllib.clustering.KMeans
			import org.apache.spark.mllib.linalg.Vectors
			
			// Cluster the data into two classes using KMeans
			val numClusters = 2
			val numIterations = 20
			val clusters = KMeans.train(parsedData, numClusters, numIterations)
			
			// Evaluate clustering by computing Within Set Sum of Squared Errors
			val WSSSE = clusters.computeCost(parsedData)
			println("Within Set Sum of Squared Errors = " + WSSSE)

// --------------------------------
val clustersOfK: org.apache.spark.mllib.clustering.KMeansModel = clusters
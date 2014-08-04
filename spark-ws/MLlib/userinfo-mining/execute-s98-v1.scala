// --------------------------------------------------------------------------
// ʹ�÷���
//	��spark-shell��ִ��
//		:load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/userinfo-mining/execute-s98-v1.scala

// 1. �������� 
// (1)��������
// :load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/userinfo-mining/create-parsedData-userinfo-s01-v1.scala
// (2)ȫ������
:load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/userinfo-mining/create-parsedData-userinfo-s98-v1.scala

// 2. ���غ���
// ���ຯ��
:load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/userinfo-mining/tryKMeansSmart.scala
// ���ݾ���ģ�ͷ���clusterID
:load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/userinfo-mining/clusteringUserInfo.scala

// ----------------------------------------------------------------------------
// ����1�� Ѱ�����K

val minK = 2
val maxK = 200
val maxIterations = 20 // ��ǰû����Ч

val dataType = "S98"
// --------------------------
// 1. ��Ч����
// (1) ��������
val taskName_GoodM1 = dataType + "_" + "GoodM1"
val resultAccount_GoodM1 = tryKMeansSmart(parsedData_GoodM1,minK,maxK,maxIterations)
val resultW2HDFS_GoodM1 = writeAccount2HDFS(resultAccount_GoodM1,2,taskName_GoodM1)
// (2) ˫������
val taskName_GoodM2 = dataType + "_" + "GoodM2"
val resultAccount_GoodM2 = tryKMeansSmart(parsedData_GoodM2,minK,maxK,maxIterations)
val resultW2HDFS_GoodM2 = writeAccount2HDFS(resultAccount_GoodM2,2,taskName_GoodM2)	
// --------------------------
// 2. ��Ч����
// (1) ǰ�����¶��� 0��NULL
val taskName_BadF3 = dataType + "_" + "BadF3"
val resultAccount_BadF3 = tryKMeansSmart(parsedData_BadF3,minK,maxK,maxIterations)
val resultW2HDFS_BadF3 = writeAccount2HDFS(resultAccount_BadF3,2,taskName_BadF3)
// (2) ��ǰ�����¶��� 0��NULL
val taskName_BadF2ExcludeF3 = dataType + "_" + "BadF2ExcludeF3"
val resultAccount_BadF2ExcludeF3 = tryKMeansSmart(parsedData_BadF2ExcludeF3,minK,maxK,maxIterations)
val resultW2HDFS_BadF2ExcludeF3 = writeAccount2HDFS(resultAccount_BadF2ExcludeF3,2,taskName_BadF2ExcludeF3)

// ----------------------------------------------------------------------------
// ����2�� ���K�ķ�Ⱥ
// --------------------------
// 1. ��Ч����
// (1) ��������
val resultClusterInfo_GoodM1 = getClusteringUserInfoFromAccount(parsedData_GoodM1, resultW2HDFS_GoodM1) 
val resultWClusterInfo2HDFS_GoodM1 = writeClusterInfo2HDFS(resultClusterInfo_GoodM1, taskName_GoodM1)  
// (2) ˫������
val resultClusterInfo_GoodM2 = getClusteringUserInfoFromAccount(parsedData_GoodM2, resultW2HDFS_GoodM2) 
val resultWClusterInfo2HDFS_GoodM2 = writeClusterInfo2HDFS(resultClusterInfo_GoodM2, taskName_GoodM2)  	
// --------------------------
// 2. ��Ч����
// (1) ǰ�����¶��� 0��NULL
val resultClusterInfo_BadF3 = getClusteringUserInfoFromAccount(parsedData_BadF3, resultW2HDFS_BadF3) 
val resultWClusterInfo2HDFS_BadF3 = writeClusterInfo2HDFS(resultClusterInfo_BadF3, taskName_BadF3)
// (2) ��ǰ�����¶��� 0��NULL
val resultClusterInfo_BadF2ExcludeF3 = getClusteringUserInfoFromAccount(parsedData_BadF2ExcludeF3, resultW2HDFS_BadF2ExcludeF3)
val resultWClusterInfo2HDFS_BadF2ExcludeF3 = writeClusterInfo2HDFS(resultClusterInfo_BadF2ExcludeF3, taskName_BadF2ExcludeF3)

// ͳһ��ʾһ�½������
resultW2HDFS_GoodM1
resultW2HDFS_GoodM2
resultW2HDFS_BadF3
resultW2HDFS_BadF2ExcludeF3

resultWClusterInfo2HDFS_GoodM1
resultWClusterInfo2HDFS_GoodM2
resultWClusterInfo2HDFS_BadF3
resultWClusterInfo2HDFS_BadF2ExcludeF3


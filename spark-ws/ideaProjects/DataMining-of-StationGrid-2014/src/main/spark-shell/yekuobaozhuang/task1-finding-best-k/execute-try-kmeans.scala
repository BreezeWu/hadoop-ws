/**
 * Created by HongZe.Wu on 9/28/14.
 */

// ----------------------------------------------------------------------------

// --------------------------
// 聚类数据: (1) 只取参与计算的数据；(2) 转变为Vector, 构建matrices
import org.apache.spark.mllib.linalg.Vectors

// 取 平均值(年度月用电量)数据
val averageRdd = mappedRddData_percent.map(x => x.average).map(y => Vectors.dense(y.toArray)).cache()
// 取 平均值(年度月用电量)数据的百分比
val averageRdd_percent = mappedRddData_percent.map(x => x.averagePercent).map(y => Vectors.dense(y.toArray)).cache()

val data = averageRdd_percent // averageRdd

val env = System.getenv()
val pwd = env.get("PWD")
// ----------------------------------------------------------------------------
// 任务1： 寻找最佳K
val minK = 2
val maxK = 6 //1000
val maxIterations = 20 // 当前没有生效

val resultAccount = tryKMeansSmart(data,minK,maxK,maxIterations)
val resultW2LocalFile = writeAccount2LocalFile(resultAccount, "yekuobaozhuang", pwd)
// ----------------------------------------------------------------------------
// 任务2： 计算簇数量（方式一，利用任务1的计算结果获得最佳K和最佳模型)
// --------------------------
val resultClusterCountInfo = ComputeClusterCount_FromAccount(data, resultAccount)
val resultWClusterCountInfo2HDFS = writeClusterCountInfo2LocalFile(resultClusterCountInfo, "yekuobaozhuang", pwd)
// ----------------------------------------------------------------------------
// 任务2： 计算簇数量（方式二，手工指定最佳K，重新计算模型，然后...）
// --------------------------
val perfectK = 5;
val maxIterations = 20 // 当前没有生效

val env = System.getenv()
val pwd = env.get("PWD")

// 1. 有效数据
// (1) 单月数据
val resultClusterCountInfo_Standalone = ComputeClusterCount_Standalone(data, perfectK, maxIterations)
// 中心点和metric信息
val resultW2LocalFile_Standalone = writeAccount2LocalFile(resultClusterCountInfo_Standalone.account, "yekuobaozhuang", pwd)
// 统计信息
val resultWClusterCountInfo2HDFS_Standalone = writeClusterCountInfo2LocalFile(resultClusterCountInfo_Standalone, "yekuobaozhuang", pwd)
// ----------------------------------------------------------------------------
//  统一显示一下结果变量
// 任务1
resultAccount
println(resultAccount.toRecordOfMetricList)
// 任务2, 方式一
resultClusterCountInfo.clusterCount
// 任务2, 方式二
resultClusterCountInfo_Standalone.clusterCount
resultClusterCountInfo_Standalone.account
// ----------------------------------------------------------------------------
// 统一回显一下HDFS路径
// 任务1
resultW2LocalFile
// 任务2, 方式一
resultWClusterCountInfo2HDFS._2
// 任务2, 方式二
resultWClusterCountInfo2HDFS_Standalone._2
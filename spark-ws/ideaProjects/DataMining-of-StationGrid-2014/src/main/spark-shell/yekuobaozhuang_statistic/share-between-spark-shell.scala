import org.apache.spark.SparkContext
import org.apache.spark.broadcast.Broadcast

/**
 * Created by HongZe.Wu on 10/28/14.
 */

val sc = new SparkContext()

// 在 spark-shell 中发布共享变量
var broadcastVar = sc.broadcast(userCountListList)
broadcastVar.id

val paramList = sc.getConf.getAll

// 在另外一个 spark-shell 中寻找共享变量
val idOfBroadcastVar = 7474
val broadcastVar_userCountListList = new Broadcast(idOfBroadcastVar)


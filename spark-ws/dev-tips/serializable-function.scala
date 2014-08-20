// ----------------------------------------------------------------------------
// Scala + Spark - Task not serializable: java.io.NotSerializableExceptionon. When calling function outside closure only on classes not objects
// http://stackoverflow.com/questions/22592811/scala-spark-task-not-serializable-java-io-notserializableexceptionon-when

import org.apache.spark.SparkContext
object Spark {
  //val ctx = new SparkContext("local", "test")
  val ctx = sc
}

// ----------------------------------------------------------------------------
// This is a WORKING code example :
object working extends App {
    val list = List(1,2,3)

    val rddList = Spark.ctx.parallelize(list)
    //calling function outside closure 
    val after = rddList.map(someFunc(_))

    def someFunc(a:Int)  = a+1

    after.collect().map(println(_))
  }

// ----------------------------------------------------------------------------
// This is the non-working example :
object NOTworking extends App {
     new testing().doIT
  }
//adding extends Serializable wont help
class testing {
    val list = List(1,2,3)

    val rddList = Spark.ctx.parallelize(list)

    def doIT =  {
      //again calling the fucntion someFunc 
      val after = rddList.map(someFunc(_))
      //this will crash (spark lazy)
      after.collect().map(println(_))
    }

    def someFunc(a:Int) = a+1
}

// ----------------------------------------------------------------------------
// 方法一:  make class testing serializable
class Test extends java.io.Serializable {
  val rddList = Spark.ctx.parallelize(List(1,2,3))

  def doIT() =  {
    val after = rddList.map(someFunc)
    after.collect().foreach(println)
  }

  def someFunc(a: Int) = a + 1
}

// ----------------------------------------------------------------------------
// 方法二:  make someFunc function instead of a method (functions are objects in Scala), so that Spark will be able to serialize it:
class Test {
  val rddList = Spark.ctx.parallelize(List(1,2,3))

  def doIT() =  {
    val after = rddList.map(someFunc)
    after.collect().foreach(println)
  }

    // 下面的定义是 val , 而不是 def
  val someFunc = (a: Int) => a + 1
  // As a side note, you can rewrite rddList.map(someFunc(_)) to rddList.map(someFunc), they are exactly the same. Usually, the second is preferred as it's less verbose and cleaner to read.
}



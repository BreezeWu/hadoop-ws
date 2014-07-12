// ----------------------------------------------------------------------------
// 在spark shell上面运行一个简单的逻辑回归
//	http://blog.csdn.net/sunflower_cao/article/details/26708797

// 1.引入向量
import org.apache.spark.util.Vector  

// 2.定义class
case class DataPoint(x:Vector,y:Double)   

// 3.方法定义
def parsePoint(x:Array[Double]):DataPoint = {   
	DataPoint(new Vector(x.slice(0,x.size-2)),x(x.size-1))  
} 

// 4.数据来源及其处理
val inFile = sc.textFile("dm-data/spark-data/spam.data");  
val nums = inFile.map(x => x.split(' ').map(_.toDouble)) 

// 5.运用方法
// 	5.1 构造数据 自变量与因变量
val points = nums.map(parsePoint(_))  

/*
	到此时,原始数据(nums)与DataPoint(points)的区别在于:
	(1) nums的最后一列作为因变量,即DataPoint.y
	(2) 疑问: 为什么要去掉 278.0 ?
scala> inFile.first()
res9: String = 0 0.64 0.64 0 0.32 0 0 0 0 0 0 0.64 0 0 0 0.32 0 1.29 1.93 0 0.96 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0.778 0 0 3.756 61 278 1


scala> nums.first
res10: Array[Double] = Array(0.0, 0.64, 0.64, 0.0, 0.32, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.64, 0.0, 0.0, 0.0, 0.32, 0.0, 1.29, 1.93, 0.0, 0.96, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.778, 0.0, 0.0, 3.756, 61.0, 278.0, 1.0)

scala> points.first
res11: DataPoint = DataPoint((0.0, 0.64, 0.64, 0.0, 0.32, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.64, 0.0, 0.0, 0.0, 0.32, 0.0, 1.29, 1.93, 0.0, 0.96, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.778, 0.0, 0.0, 3.756, 61.0),1.0)

*/

// 	5.2 随机数
import java.util.Random
val rand = new Random
print(rand)

//	5.3 下面语句是在干什么? 
//		var w = Vector(dimNum, _ => rand.nextDouble) //用随机数初始化参数, 即随机一个各个自变量的系数
var w = Vector(nums.first.size-2, _=>rand.nextDouble) 

// 迭代次数
val iteration = 100 
import scala.math._  

// 梯度下降方法?
for (i <- 1 to iterations) {
  val gradient = points.map(p =>
    (1 / (1 + exp(-p.y*(w dot p.x))) - 1) * p.y * p.x
  ).reduce(_ + _)
  w -= gradient
}

// 最后的出来的w是一个什么?
// 就是各个自变量的斜率/系数
w
w.length	// 56

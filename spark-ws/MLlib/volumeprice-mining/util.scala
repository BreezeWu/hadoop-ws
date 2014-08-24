// ----------------------------------------------------------------------------
/**
 * 辅助函数
 *      引入
:load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/volumeprice-mining/util.scala
 *
 */

// -----------------------------------------------------------------------
// 打印 Accumulator
def printAccumulator(acc:YearVolumePriceInfo_RangeCountAccObj) = {    
    println("--------------------------- yearVolume ---------------------------")
    println(acc.yearVolume.counter1)
    println(acc.yearVolume.counter2)
    println(acc.yearVolume.counter3)
    
    println("--------------------------- yearSplit ---------------------------")
    println(acc.yearSplit.counter1)
    println(acc.yearSplit.counter2)
    println(acc.yearSplit.counter3)
    
    println("--------------------------- yearMoney ---------------------------")
    println(acc.yearMoney.counter1)
    println(acc.yearMoney.counter2)
    println(acc.yearMoney.counter3)
}

// -----------------------------------------------------------------------
// 写入文件
def write3RangeCountObj2File(rangeCountAccObj3: RangeCountAccObj, name:String):(String,String,String) = {  
    // 将((Int, Int), Long)) 转换为 (Int, (Int, Int), Long))
    def convert2interval(list:List[((Int, Int), Long)]):List[(Int, (Int, Int), Long)] = {
        val length = list.length
        
        val range = 0 until length by 1
        
        // for comprehension       
        val result = for {
            //i <- range; item <- list        // 这是List中的List
            i <- range              // 这是List
        } yield {
            val item = list(i)      // 没有 item<-list 时需要该行语句
            val begin = item._1._1;
            val index = item._1._2;
            val count = item._2;
            
            val end = if(i==length -1) // 最后一个 
                        Int.MaxValue   // 最大Int
                      else 
                        list(i+1)._1._1;
                        
            val obj = Tuple3(index, Tuple2(begin, end), count)
            obj
        }
        
        return result.toList       // 转换为List
        
        /*
        // 改写的 for
        val result = range.map(i => list.map(item => {
            val begin = item._1._1;
            val index = item._1._2;
            val count = item._2;
            
            val end = if(i==length -1) // 最后一个 
                        9999999   // 随便一个很大的整数
                      else 
                        list(i+1)._1._1;
                        
            val obj = Tuple3(index, Tuple2(begin, end), count)
            obj
        } ))
        */        
    }
    
    // 转换为可打印字符
    val map2PrintString = (x:(Int, (Int, Int), Long)) => x match { case ((index:Int, (begin:Int, end:Int), count:Long)) => s"${index},${begin},${end},${count}"}

    // 文件名
    val rootpath = "/home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/result-data/"
    val filename_prefix = rootpath + "RangeStatistic_" + name
    val file_extend = ".csv"

    val filename_counter1 = filename_prefix + "_counter1" + file_extend
    val filename_counter2 = filename_prefix + "_counter2" + file_extend
    val filename_counter3 = filename_prefix + "_counter3" + file_extend
    
    // 数据
    val counter1 = rangeCountAccObj3.counter1
    val counter2 = rangeCountAccObj3.counter2
    val counter3 = rangeCountAccObj3.counter3
    
    // 将某一个写入文件
    def writeCounter2File(list:List[((Int, Int), Long)], filepath:String) = {
        val file = new java.io.File(filepath)
        val newfile = file.createNewFile()
        
        val filewriter = new java.io.FileWriter(file)
        
        // 写入第一行
        val colsDesc = "索引,起始值(含),终止值(不含),计数"
        filewriter.write(colsDesc)
        
        convert2interval(list).map(map2PrintString).foreach(x => filewriter.write("\n" + x))
        
        filewriter.flush()
        filewriter.close() 
    }
    
    // 执行写入
    writeCounter2File(counter1, filename_counter1)
    writeCounter2File(counter2, filename_counter2)
    writeCounter2File(counter3, filename_counter3)
    
    return Tuple3(filename_counter1, filename_counter2, filename_counter3)
}

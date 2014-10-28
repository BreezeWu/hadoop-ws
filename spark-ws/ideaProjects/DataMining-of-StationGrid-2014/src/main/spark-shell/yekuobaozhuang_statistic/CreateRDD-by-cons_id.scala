import org.apache.spark.rdd.RDD

/**
 * Created by HongZe.Wu on 10/28/14.
 *
 *  在运行 loadDataFromOneFile.scala 之后, 基于csvRdd, 按照CONS_ID合并
 */

// ----------------------------------------------------------------------------
// 基本设置
val MERGE_METHOD_MAX = 0
val MERGE_METHOD_SUM = 1
val g_mergeList = List(
  ("MP_CAP", MERGE_METHOD_MAX),
  ("CONTRACT_CAP", MERGE_METHOD_MAX),
  ("RUN_CAP", MERGE_METHOD_MAX),
  ("SUMPQ", MERGE_METHOD_SUM),
  ("MAXDL", MERGE_METHOD_MAX)
).map(x => (x._1.toUpperCase, x._2))

val g_indexColumnName = "CONS_ID".toUpperCase
val g_specialColumnName = "YM".toUpperCase
// ----------------------------------------------------------------------------
// 剔除首行,并根据 g_indexColumnName 进行分组
val kvRdd_by_CONS_ID = csvRdd.filter(x => {  // 剔除首行
  val v = x(colsIndexOf("MP_ID"));
  !v.contentEquals("MP_ID")
}).groupBy(x => x(colsIndexOf(g_indexColumnName)))
// ----------------------------------------------------------------------------
// 将用户的多条记录转换为一个记录
val recordRdd_by_CONS_ID = kvRdd_by_CONS_ID.map( x => {
  val key = x._1
  val value = x._2

  // 转换为list,并根据YM排序
  val list = value.toList.sortBy(x => x(colsIndexOf("YM")))  // 根据"YM"从小向大排序

  // 假定: 除了 索引列/合并列/特殊列外, 其他数据都与第一行相同
  val typical = list(0)

  // 构建链表
  val ym_list = list.map(x => x(colsIndexOf("YM")))
  val shouldBeMergedListList = g_mergeList.map(z => {
    val colname = z._1
    val method = z._2

    val curList = list.map(x => x(colsIndexOf(colname)))
    (colname, curList)
  })

  // 结果
  (typical, ym_list, shouldBeMergedListList)
})


// ----------------------------------------------------------------------------
// 将有多个 MP_ID 的用户数据合并

// ------------------------------------  >>>>>>>>> 探测数据样本
// 找到有多个MP_ID的用户: recordRdd_by_CONS_ID 中的 ym_list 有重复值
val recordRdd_by_CONS_ID_hasMulti_MP_ID = recordRdd_by_CONS_ID.filter( x => {
  val ym_list = x._2

  val size = ym_list.size
  val size_distinct = ym_list.distinct.size
  val filterFlag = if (size == size_distinct) true else false

  // 保留值
  !filterFlag
})
// 找到一个样本
val oneInstance_hasMulti_MP_ID = recordRdd_by_CONS_ID_hasMulti_MP_ID.first
val cons_id = (oneInstance_hasMulti_MP_ID._1)(colsIndexOf("CONS_ID"))
val ym_list = oneInstance_hasMulti_MP_ID._2
val shouldBeMergedListList = oneInstance_hasMulti_MP_ID._3

// 找个某个列是指定值的记录
def findSpecialRecord(colname:String, value:String,
                      recordRdd:RDD[(Array[String], List[String], List[(String, List[String])])] = recordRdd_by_CONS_ID) = {
  recordRdd.filter(x => {
    (x._1)(colsIndexOf(colname)).contentEquals(value)
  }).collect()
}

val user_id_hasMulti_MP_ID = "810302631" // 或者上面的 cons_id
val oneRecord = findSpecialRecord("CONS_ID", user_id_hasMulti_MP_ID)
// 下面语句供后面语句使用
val x = oneInstance_hasMulti_MP_ID
val z = shouldBeMergedListList(3)
val ym = ym_list_distinct(3)
// ------------------------------------ 探测数据样本 <<<<<<<<<

// 将有多个 MP_ID 的用户数据合并: 执行
val recordRdd_by_CONS_ID_merged = recordRdd_by_CONS_ID.map(x => {
  val typical = x._1
  val ym_list = x._2
  val shouldBeMergedListList = x._3

  val ym_list_distinct = ym_list.distinct
  val mergedListList = shouldBeMergedListList.map(z => {
    val colname = z._1
    val valueList = z._2

    // 这里的 valueList 与 ym_list 一一对应
    if (valueList.size != ym_list.size)
      println("@recordRdd_by_CONS_ID_merged ERROR: SOMETHING WRONG!")

    val newValueList = ym_list_distinct.map(ym => {
      // 找到所有是当前ym的索引: 这里的 valueList 与 ym_list 一一对应,所以使用ym_list
      val indexList = ym_list.zipWithIndex.filter(m => {
        val key = m._1
        key.contentEquals(ym)
      }).map(m => m._2)

      // 在valueList中根据indexList获取值
      val valueListOf_thisYm = valueList.zipWithIndex.filter(v => {
        val value = v._1
        val index = v._2

        // 这个索引index在indexList中则保留
        val filterFlag = indexList.contains(index)

        // 保留值
        filterFlag
      }).map(v => v._1)

      //进行merge
      val methodList = g_mergeList.filter(x => (x._1).contentEquals(colname))
      val method = (methodList.reverse)(0)._2  // 取最后一个
      val mergedValue = method match {
          case MERGE_METHOD_MAX => {
            // 转换为double
            val valueListOf_thisYm_asDouble = valueListOf_thisYm.map(x => {
              // x 有可能为空,如最大电力可能为""
              if (null == x || x.contentEquals("")) {
                Int.MinValue.toDouble  // 这里设置为负值,因为用于求最大值,且将来要用来识别是否有最大电力
              } else {
                x.toDouble
              }
            })
            valueListOf_thisYm_asDouble.reduce((a,b) => scala.math.max(a,b))
          }
          case MERGE_METHOD_SUM => {
            // 转换为double
            val valueListOf_thisYm_asDouble = valueListOf_thisYm.map(x => {
              // x 有可能为空,如""
              if (null == x || x.contentEquals("")) {
                0F  // 这里设置为0, 因为要用于求和
              } else {
                x.toDouble
              }
            })
            valueListOf_thisYm_asDouble.reduce((a,b) => (a+b))
          }
        }

      // 返回值
      mergedValue
    })

    (colname, newValueList)
  })

  // 结果
  //(typical, ym_list, shouldBeMergedListList, mergedListList)
  (typical, ym_list, mergedListList)
}).cache()

val oneInstance = recordRdd_by_CONS_ID_merged.first
val typical = oneInstance._1
val ym_list = oneInstance._2
val shouldBeMergedListList = oneInstance._3
val mergedListList = oneInstance._4

// ----------------------------------------------------------------------------
// 其他转换
// 1. 根据RUN_DATE来计算运行时长 或者
// 2. 将月份转换为季度
// ----------------------------------------------------------------------------
// 一, 生成用于统计分析的 specialRecordRdd

// 二, 导出 csv文件
// 需要找出所有数据集中的ym, 然后基于这个最全的ym列表,来变换为完全矩阵化的数据集.(若用户没有该月数据,则设置为null)
val g_ym_list = recordRdd_by_CONS_ID_merged.map(x => x._2).reduce((a,b) => (a++b).distinct)

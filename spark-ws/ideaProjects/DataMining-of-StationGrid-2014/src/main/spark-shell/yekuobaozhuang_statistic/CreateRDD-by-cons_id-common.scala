import org.apache.spark.rdd.RDD

/**
 * Created by HongZe.Wu on 10/28/14.
 *
 *  在运行 loadDataFromOneFile.scala 之后, 基于csvRdd, 按照CONS_ID合并
 *
 *  recordRdd_by_CONS_ID_merged 是一个根据 CONS_ID 来汇总的一条记录
 *  --- 若一个用户有多个MP_ID,已经对其需要合并的字段如MAXDL,SUMPQ等做了合并.
 *  --- 若用户中的待合并的值非法,如""
 *    (1)所有都非法, 则将其转换为了一个负值(g_when_doubleValue_illegal)
 *    (2)只有部分非法, a. 求MAX时,同上处理; b. 求SUM时, 将非法值转换为0,然后再求SUM
 *
 *  而 recordRdd_by_CONS_ID_merged_fullYmList 在recordRdd_by_CONS_ID_merged的基础上,将链表映射为g_ym_list长度的链表
 *  --- 若用户没有某个月的信息,则这个月的值为null(g_when_no_YM).
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
val g_when_no_YM = null // 若用户没有某个月的信息,则这个月的值为null
val g_when_doubleValue_illegal = Int.MinValue.toDouble  // 若用户中的待合并的值非法,如"", 则将其转换为了一个负值(g_when_doubleValue_illegal)
// ----------------------------------------------------------------------------
// 剔除首行,并根据 g_indexColumnName 进行分组
val kvRdd_by_CONS_ID = csvRdd.filter(x => {  // 剔除首行
  val v = x(colsIndexOf_raw("MP_ID"));
  !v.contentEquals("MP_ID")
}).filter(x => {  // 状态正常
  val v = x(colsIndexOf_raw("STATUS_CODE"));
  v.contentEquals("01") || v.contentEquals("02") || v.contentEquals("03")
}).groupBy(x => x(colsIndexOf_raw(g_indexColumnName)))
// ----------------------------------------------------------------------------
// 将用户的多条记录转换为一个记录
val recordRdd_by_CONS_ID = kvRdd_by_CONS_ID.map( x => {
  val key = x._1
  val value = x._2

  // 转换为list,并根据YM排序
  val list = value.toList.sortBy(x => x(colsIndexOf_raw("YM")))  // 根据"YM"从小向大排序

  // 假定: 除了 索引列/合并列/特殊列外, 其他数据都与第一行相同
  val typical = list(0)

  // 构建链表
  val ym_list = list.map(x => x(colsIndexOf_raw("YM")))
  val shouldBeMergedListList = g_mergeList.map(z => {
    val colname = z._1
    val method = z._2

    val curList = list.map(x => x(colsIndexOf_raw(colname)))
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
/*val oneInstance_hasMulti_MP_ID = recordRdd_by_CONS_ID_hasMulti_MP_ID.first
val cons_id = (oneInstance_hasMulti_MP_ID._1)(colsIndexOf_raw("CONS_ID"))
val ym_list = oneInstance_hasMulti_MP_ID._2
val shouldBeMergedListList = oneInstance_hasMulti_MP_ID._3*/

// 找个某个列是指定值的记录
def findSpecialRecordRdd(colname:String, value:String,
                      recordRdd:RDD[(Array[String], List[String], List[(String, List[String])])] = recordRdd_by_CONS_ID) = {
  recordRdd.filter(x => {
    (x._1)(colsIndexOf_raw(colname)).contentEquals(value)
  })
}

val user_id_hasMulti_MP_ID = "810302631" // 或者上面的 cons_id
/*
// 下面语句供后面语句使用
val oneRecord = findSpecialRecordRdd("CONS_ID", user_id_hasMulti_MP_ID).first
val x = oneRecord
val z = shouldBeMergedListList(3)
val ym = ym_list_distinct(3)*/
// ------------------------------------ 探测数据样本 <<<<<<<<<

// 将有多个 MP_ID 的用户数据合并: 执行
val recordRdd_by_CONS_ID_merged = recordRdd_by_CONS_ID.map(x => {
  val typical = x._1
  val ym_list = x._2
  val shouldBeMergedListList = x._3

  val ym_list_distinct = ym_list.distinct.sorted
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
                g_when_doubleValue_illegal //Int.MinValue.toDouble  // 这里设置为负值,因为用于求最大值,且将来要用来识别是否有最大电力
              } else {
                x.toDouble
              }
            })
            valueListOf_thisYm_asDouble.reduce((a,b) => scala.math.max(a,b))
          }
          case MERGE_METHOD_SUM => {
            // 如果所有都是空,如"",则将valueListOf_thisYm整个记为g_when_doubleValue_illegal
            val sumValue = if (valueListOf_thisYm.forall(x => null == x || x.contentEquals("") )) {
              g_when_doubleValue_illegal
            } else {
              // 转换为double
              val valueListOf_thisYm_asDouble = valueListOf_thisYm.map(x => {
                // x 有可能为空,如""
                if (null == x || x.contentEquals("")) {
                  0D  // 这里设置为0, 因为要用于求和
                } else {
                  x.toDouble
                }
              })
              valueListOf_thisYm_asDouble.reduce((a,b) => (a+b))
            }

            sumValue
          }
        }

      // 返回值
      mergedValue
    })

    (colname, newValueList)
  })

  // 结果
  //(typical, ym_list, shouldBeMergedListList, mergedListList)
  (typical, ym_list_distinct, mergedListList)
}).cache()

/*
val oneInstance = recordRdd_by_CONS_ID_merged.first
val typical = oneInstance._1
val ym_list = oneInstance._2
val shouldBeMergedListList = oneInstance._3
val mergedListList = oneInstance._4

// ----------------------------------------------------------------------------
// 查找特定的样本
def findInstance(id:String) = {
  val rawInstance = specialCsvRdd.filter(x => x(0).contentEquals(id)).collect()
  val instance = recordRdd_by_CONS_ID_merged.filter(x => x._1.contentEquals(id))

  // 直接打印: 中间可能掺杂spark的信息
  rawInstance.foreach(x => {x.foreach(y => print(y + "\t")); print("\n")})
  orgInstance.collect()
}
*/

// ----------------------------------------------------------------------------
// 需要找出所有数据集中的ym, 然后基于这个最全的ym列表,来变换为完全矩阵化的数据集.(若用户没有该月数据,则设置为null)
val g_ym_list = recordRdd_by_CONS_ID_merged.map(x => x._2).reduce((a,b) => (a++b).distinct).sorted
// 为每个用户构建g_ym_list长度的链表
val recordRdd_by_CONS_ID_merged_fullYmList = recordRdd_by_CONS_ID_merged.map(x => {
  val typical = x._1
  val ym_list = x._2
  val mergedListList = x._3

  val newMergedListList = mergedListList.map(ll => {
    val colname = ll._1
    val valueList = ll._2
    val defaultNewValue = g_when_no_YM //null

    val newValueList = g_ym_list.map(ym => {
      // 找到所有是当前ym的索引: 这里的 valueList 与 ym_list 一一对应,所以使用ym_list
      val indexList = ym_list.zipWithIndex.filter(m => {
        val key = m._1
        key.contentEquals(ym)
      }).map(m => m._2)
      // 由于前面已经对链表中的值求了MAX或SUM, 这里的indexList要么为0,要么为1.
      val size_indexList = indexList.size
      //      if (size_indexList != 0 && size_indexList != 1)
      //        println("@recordRdd_by_CONS_ID_merged_fullYmList ERROR: SOMETHING WRONG!")
      // 在valueList中根据indexList获取值
      val newValue = if (size_indexList == 0) {
        defaultNewValue
      } else {
        if (size_indexList == 1) valueList(indexList(0)) else {
          println(s"@recordRdd_by_CONS_ID_merged_fullYmList ERROR: size_indexList is [${size_indexList}] != 1!")
          defaultNewValue
        }
      }
      // 返回值
      newValue
    }).toList

    // 返回值
    (colname, newValueList)
  })

  // 结果
  //(typical, ym_list, mergedListList, newMergedListList)
  (typical, ym_list, newMergedListList)
})

// ============================================================================
// 到这里,
// recordRdd_by_CONS_ID_merged 和 recordRdd_by_CONS_ID_merged_fullYmList 是基础
// ----------------------------------------------------------------------------
// 其他转换
// 1. 根据RUN_DATE来计算运行时长 或者
// 2. 将月份转换为季度

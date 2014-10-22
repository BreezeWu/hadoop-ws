/**
 * Created by HongZe.Wu on 9/28/14.
 */

// case class 成员最多22个，所以要拆分
case class IndexLeft(
                      mp_id:String,
                      mp_no:String,
                      mp_name:String,
                      mp_addr:String,
                      volt_code:String,
                      app_date:String,
                      run_date:String,
                      org_no:String,
                      mr_sect_no:String,
                      line_id:String,
                      tg_id:String,
                      status_code:String,
                      cons_id:String,
                      mp_cap:String,
                      cons_no:String,
                      zxzb:String,
                      cons_name:String,
                      elec_addr:String,
                      trade_code:String,
                      elec_type_code:String
                      )
case class IndexRight(
                       contract_cap:String,
                       run_cap:String,
                       build_date:String,
                       ps_date:String,
                       cancel_date:String,
                       due_date:String
                       )
// 索引
case class Index(id:String, left:IndexLeft, right:IndexRight)
// 一年各月用电量
//case class MonthVolume(m1:Double, m2:Double, m3:Double, m4:Double, m5:Double, m6:Double, m7:Double, m8:Double, m9:Double, m10:Double, m11:Double, m12:Double)
// 一年各月用电量(Array)
type TwelveVolumes = scala.collection.immutable.IndexedSeq[Double] //Array[Double]
val zeroTwelveVolumes = for(i<- Range(0,12)) yield 0.0
// 逐年的月用电量
case class YearsVolumes(y2010:TwelveVolumes, y2011:TwelveVolumes, y2012:TwelveVolumes, y2013:TwelveVolumes, y2014:TwelveVolumes)
// 数据集元素
case class TableObject(index:Index, volumes:YearsVolumes)

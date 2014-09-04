// ----------------------------------------------------------------------------
// ��hive�е�����ת��Ϊ���ŷָ�
// �����
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.linalg.distributed.RowMatrix

// ����Դ
val hiveContext = new org.apache.spark.sql.hive.HiveContext(sc)
val rddFromHive = hiveContext.hql("SELECT cons_id,contract_cap FROM bigdata_user_info_s01")

// �鿴��һ������
 rddFromHive.first	// res1: org.apache.spark.sql.Row = [2.5374088E7,2.0]
 val row1 = rddFromHive.first
 row1(0)
 row1(1)
// -----------------------------------------------------------
	// ������·��  �磺 /user/spark/tmpWorkDir/2014-07-21-10-53-57
	val sparkRoot = "/user/spark/"
	val sparkTmpWD = sparkRoot + "tmpWorkDir/"
	
	// ����ʱ���
	import java.util.Date
	val format = new java.text.SimpleDateFormat("yyyy-MM-dd-HH-mm-ss")	// "dd-MM-yyyy" "yyyy-MM-dd" "yyyy-MM-dd-HH-mm-ss"
	val dateString = format.format(new java.util.Date())
	
	val tmpOutPutFilePath = sparkTmpWD +dateString
// -----------------------------------------------------------
// ����ת���뱣��
// ����res1: org.apache.spark.sql.Row��д�����Ľ���� [2.5502125E7,2.0] ��ʽ
//rddFromHive.saveAsTextFile(tmpOutPutFilePath)

// ��Ҫ����һ���ı���  cons_id���û���ʶ�����ı���������ֵ  
// ????? ��֪����ôת������
//val convertedData = rddFromHive.map(x => Tuple2(x(0), x(1)) )
//convertedData.saveAsTextFile(tmpOutPutFilePath)
//tmpFilePath

// Tuple2���������ݣ�ǰ����С����
val convertedData = rddFromHive.map(x => x(0) + "," + x(1) )
convertedData.first
convertedData.saveAsTextFile(tmpOutPutFilePath)

// ����ļ���·��
tmpFilePath




using sqoop to import data from oracle

sqoop help
============
sqoop help
sqoop help import
sqoop help export

using --options-file
====================
please refer to 'importing-to-hive.sqoop'

without --options-file
=====================

1. whole table
---------------

sqoop import \
--verbose   \
--mapreduce-job-name sqoop_import_wholetable_YKBZ_DETAIL    \
--connect   \
jdbc:oracle:thin:@192.168.1.6:1521/yjzhgl   \
--username  \
UAP \
--password  \
uap \
\
--hive-import   \
--warehouse-dir \
/user/hadoop/tmp-for-sqoop-hive \
\
--table YKBZ_DETAIL \
--split-by YONGDIANLB   \
--hive-table YKBZ_DETAIL_t1_ACC00101 \
--where "DIANYADJ='AC00101'"


2. free form query 
-------------------

注意: 去掉"--warehouse-dir \
      /user/hadoop/tmp-for-sqoop-hive \"

sqoop import \
--verbose   \
--mapreduce-job-name sqoop_import_free-form-query_YKBZ_DETAIL   \
--connect   \
jdbc:oracle:thin:@192.168.1.6:1521/yjzhgl   \
--username  \
uap \
--password  \
uap \
\
--hive-import   \
\
--query 'select * from YKBZ_DETAIL WHERE $CONDITIONS '  \
--split-by YONGDIANLB   \
--boundary-query "select min(YONGDIANLB),max(YONGDIANLB) from YKBZ_DETAIL"  \
--target-dir /user/hadoop/tmp-for-sqoop-hive/YKBZ_DETAIL    \
--hive-table YKBZ_DETAIL_t2 #\
#--where "DIANYADJ='AC00101'"

或者 2. free form query 选择特定字段
----------------

sqoop import \
--verbose   \
--mapreduce-job-name sqoop_import_free-form-query_YKBZ_DETAIL   \
--connect   \
jdbc:oracle:thin:@192.168.1.6:1521/yjzhgl   \
--username  \
uap \
--password  \
uap \
\
--hive-import   \
\
--query 'select YEKUOGDH,GONGDANCJRQ,GONGDIANDW_DS,GONGDIANDW_XQ,YONGHUMC,YONGHUBZRL,YONGHUYYRL,DIANYADJ,YONGDIANLB, 
 XIANCHANGKCSC,GONGDIANFASCSC,GONGDIANFADFKHSC,SHOUDIANGCSJZLSHSC,SHOUDIANGCZJJCSC,SHOUDIANGCJGYSSC,ZHUANGBIAOJDSC,
  VER_NO,ALL_TIME,PLAN_REPLY_TIME,SCENE_RESEARCH_TIME,PLAN_CHECK_TIME,DATA_CHECK_TIME,PROCESS_CHECK_TIME,COMPLETED_ACCEPT_TIME, SEND_ELE_TIME
  from YKBZ_DETAIL WHERE $CONDITIONS '  \
--split-by YONGDIANLB   \
--boundary-query "select min(YONGDIANLB),max(YONGDIANLB) from YKBZ_DETAIL"  \
--target-dir /user/hadoop/tmp-for-sqoop-hive/YKBZ_DETAIL    \
--hive-table YKBZ_DETAIL_t2_selectColumns




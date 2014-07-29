-- ------------------------------------------------
-- 其他表的处理参见 ~/workspace_github/hadoop-ws/oracle-ws/01.data-exploration.sql

-- 分时电价电量 和 阶梯电价电量 的全量表列名为中文,需要转换为英文

-- 分时电价电量 BIGDATA_VOLUME_OF_TS_S98
alter table BIGDATA_VOLUME_OF_TS_S98 rename column 尖峰电量  to kwh_volume_top;
alter table BIGDATA_VOLUME_OF_TS_S98 rename column 尖峰电价  to kwh_prc_top;

-- 阶梯电价电量 BIGDATA_VOLUME_OF_PRC_S01
alter table BIGDATA_VOLUME_OF_TS_S98 rename column 一阶电量  to u_pq_1;
alter table BIGDATA_VOLUME_OF_TS_S98 rename column 一阶电价  to u_pq_1_prc;
alter table BIGDATA_VOLUME_OF_TS_S98 rename column 二阶电量  to u_pq_2;
alter table BIGDATA_VOLUME_OF_TS_S98 rename column 二阶电价  to u_pq_2_prc;
alter table BIGDATA_VOLUME_OF_TS_S98 rename column 三阶电量  to u_pq_3;
alter table BIGDATA_VOLUME_OF_TS_S98 rename column 三阶电价  to u_pq_3_prc;

-- hive中修改列名
/*
alter table BIGDATA_VOLUME_OF_PRC_S01 change column prc_u_pq_1 u_pq_1_prc double AFTER u_pq_1;
alter table BIGDATA_VOLUME_OF_PRC_S01 change column prc_u_pq_2 u_pq_2_prc double AFTER u_pq_2;
alter table BIGDATA_VOLUME_OF_PRC_S01 change column prc_u_pq_3 u_pq_3_prc double AFTER u_pq_3;
*/
/*
hive> desc BIGDATA_VOLUME_OF_TS_S01;
OK
cons_no             	string              	                    
ym                  	string              	                    
kwh_volume_top      	int                 	                    
kwh_prc_top         	double              	                    
kwh_volume_high     	int                 	                    
kwh_prc_high        	double              	                    
kwh_volume_mean     	int                 	                    
kwh_prc_mean        	double              	                    
kwh_volume_low      	int                 	                    
kwh_prc_low         	double              	                    
kwh_volume_bottom   	int                 	                    
kwh_prc_bottom      	double              	                    
Time taken: 1.06 seconds, Fetched: 12 row(s)

hive> desc BIGDATA_VOLUME_OF_PRC_S01;
OK
cons_no             	string              	                    
ym                  	string              	                    
u_pq_1              	int                 	                    
u_pq_1_prc          	double              	                    
u_pq_2              	int                 	                    
u_pq_2_prc          	double              	                    
u_pq_3              	int                 	                    
u_pq_3_prc          	double      
        
*/

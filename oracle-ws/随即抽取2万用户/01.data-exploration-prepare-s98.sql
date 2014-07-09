--# -----------------------------------------------------------------------------
--# �û���Ϣ
--# BIGDATA_USER_INFO_S98
--select * from BIGDATA_USER_INFO_S98 where rownum < 10;
--select min(�û�ID),max(�û�ID) from BIGDATA_USER_INFO_S98
--select min(trade_code),max(trade_code) from META_TRADE_TYPE
--select trade_code from META_TRADE_TYPE
--# �õ����� �� �������� û����Ϣ
--    select 
--        �û�ID cons_id,�ͻ����� cons_name,��۴��� prc_code, ���оֱ��� org_no,
--        �õ���� elec_type_code,�����ѹ volt_code_1,��ͬ���� contract_cap,
--        �������� lode_attr_code,��ǰ�Ƿ����� status_code,�����û�ũ���û� urban_rural_flag,
--        ��ҵ trade_code,��ѹ��ѹ cust_type_code,�û��� cons_no,��ѹ volt_code_2
--    from BIGDATA_USER_INFO_S98
--    where rownum < 10;
    
--# ���õ���
--BIGDATA_ARC_�û�IDVOLUME_PERM_S98
alter table BIGDATA_ARC_VOLUME_PERM_S98 rename column �û�ID  to cons_id;
alter table BIGDATA_ARC_VOLUME_PERM_S98 rename column ���õ���  to volume_per_month;

--# �û����½ɷ�Ƿ����Ϣ 
--BIGDATA_RCVBL_FLOW_PM_S98

alter table BIGDATA_RCVBL_FLOW_PM_S98 rename column �û���  to cons_no;
alter table BIGDATA_RCVBL_FLOW_PM_S98 rename column ����  to YM;
alter table BIGDATA_RCVBL_FLOW_PM_S98 rename column ʵ��  to rcved_amt;
alter table BIGDATA_RCVBL_FLOW_PM_S98 rename column Ӧ��  to rcvbl_amt;
alter table BIGDATA_RCVBL_FLOW_PM_S98 rename column Ƿ��  to owning_amt;
--select '201001','201406' from dual

--# ΥԼ�õ����
--BIGDATA_POWER_STEAL_PERY_S98

alter table BIGDATA_POWER_STEAL_PERY_S98 rename column �û�ID  to cons_id;
alter table BIGDATA_POWER_STEAL_PERY_S98 rename column ���  to Y;
alter table BIGDATA_POWER_STEAL_PERY_S98 rename column ΥԼ�õ����  to inspect_count;

--# �û�����ɷѷ�ʽ�������
--BIGDATA_CHANGE_PAYTYPE_PY_S98

alter table BIGDATA_CHANGE_PAYTYPE_PY_S98 rename column �û�ID  to cons_id;
alter table BIGDATA_CHANGE_PAYTYPE_PY_S98 rename column ���  to Y;
alter table BIGDATA_CHANGE_PAYTYPE_PY_S98 rename column �ɷѷ�ʽ�������  to change_payType_count;


--# �Ƿ���ݵ��    
--BIGDATA_TS_OR_PRCSCOPE_S98
---��С���cons_no 0000000026    8202921013
-- SELECT MIN(cons_no), MAX(cons_no) FROM BIGDATA_TS_OR_PRCSCOPE_S98

alter table BIGDATA_TS_OR_PRCSCOPE_S98 rename column �û���  to cons_no;
alter table BIGDATA_TS_OR_PRCSCOPE_S98 rename column �������  to prc_code;
alter table BIGDATA_TS_OR_PRCSCOPE_S98 rename column �Ƿ��ʱ  to ts_flag;
alter table BIGDATA_TS_OR_PRCSCOPE_S98 rename column �Ƿ����  to ladder_flag;

alter table BIGDATA_TS_OR_PRCSCOPE_S98 add (ts_flag_i int);
alter table BIGDATA_TS_OR_PRCSCOPE_S98 add (ladder_flag_i int);

update table BIGDATA_TS_OR_PRCSCOPE_S98 set ts_flag_i = 0 where  ts_flag = '��';
update table BIGDATA_TS_OR_PRCSCOPE_S98 set ts_flag_i = 1 where  ts_flag = '��';
update table BIGDATA_TS_OR_PRCSCOPE_S98 set ladder_flag_i = 0 where  ladder_flag = '��';
update table BIGDATA_TS_OR_PRCSCOPE_S98 set ladder_flag_i = 1 where  ladder_flag = '��';

--# ��ʱ��۵���    ---������
--BIGDATA_VOLUME_OF_TS_S98
--# ���ݵ�۵���    ---������
--BIGDATA_VOLUME_OF_PRC_S98

--#---------------------meta table -------------
--# ��ҵ���ͱ� ok  550
--META_TRADE_TYPE
--
--# ������ձ� ok  13549  
--META_PROP_LIST
--# META_PROP_LIST        (ORACLE��Ϊ EPSA_LN.SA_PROP_LIST)
--SELECT * from EPSA_LN.SA_PROP_LIST META_PROP_LIST where rownum < 10;

--# -----------------------------------------------------------------------------
--# ���оֱ����    238
--select count(*) from EPSA_LN.sa_org
--select * from EPSA_LN.sa_org
--select min(org_no),max(org_no) from EPSA_LN.sa_org
--select org_no from EPSA_LN.sa_org
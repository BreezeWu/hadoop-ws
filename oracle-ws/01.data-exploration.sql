select * from BIGDATA_CHANGE_PAYTYPE_PY_S98 where rownum < 100;

select count(*) from BIGDATA_CHANGE_PAYTYPE_PY_S01;
select count(*) from BIGDATA_POWER_STEAL_PERY_S01;

select count(*) from BIGDATA_USER_INFO_S98;

# �û���Ϣ
BIGDATA_USER_INFO_S98
# ���õ���
BIGDATA_ARC_VOLUME_PERM_S98
# �û����½ɷ�Ƿ����Ϣ    ---������
BIGDATA_RCVBL_FLOW_PM_S98

# �û�����ɷѷ�ʽ�������
BIGDATA_CHANGE_PAYTYPE_PY_S98
# ΥԼ�õ����
BIGDATA_POWER_STEAL_PERY_S98

# �Ƿ���ݵ��    ---������
BIGDATA_TS_OR_PRCSCOPE_S98
# ��ʱ��۵���    ---������
BIGDATA_VOLUME_OF_TS_S98
# ���ݵ�۵���    ---������
BIGDATA_VOLUME_OF_PRC_S98

#---------------------meta table -------------
# ��ҵ���ͱ� ok  550
META_TRADE_TYPE

# ������ձ� ok  13549  
META_PROP_LIST
# META_PROP_LIST        (ORACLE��ΪEPSA_LN.SA_PROP_LIST)
SELECT * from EPSA_LN.SA_PROP_LIST META_PROP_LIST where rownum < 10;
--select count(*) from bigdata_ts_or_prcScope_s01 where �Ƿ��ʱ = '��';
select * from bigdata_ts_or_prcScope_s01;

--ʶ���ظ��˵ļ�¼
select �û���,count(�û���) from bigdata_ts_or_prcScope_s01
    GROUP BY �û���
    having count(�û���) > 1
    
select * from bigdata_ts_or_prcScope_s01 where �û��� = '8076811676' 

--ʶ���ظ��˵ļ�¼
select �û���,count(�û���) from BIGDATA_USER_INFO_S01
    GROUP BY �û���
    having count(�û���) > 1
    
select * from BIGDATA_USER_INFO_S01 where �û��� = '8076811676' 

--20288
select count(distinct �û���) from bigdata_ts_or_prcScope_s01
--20288
select count(distinct �û���) from BIGDATA_USER_INFO_S01

  
--�ظ��˵ļ�¼
s
select * from BIGDATA_USER_INFO_S01 where �û��� ='8076811676'
    

select �û��� from BIGDATA_USER_INFO_S01 -- order by �û���
minus
select �û��� from bigdata_ts_or_prcScope_s01 --order by �û���;

select �û��� from bigdata_ts_or_prcScope_s01
minus
select �û��� from BIGDATA_USER_INFO_S01

--20289
select count(*) from bigdata_ts_or_prcScope_s01
-- 109
select count(*) from bigdata_ts_or_prcScope_s01 where �Ƿ��ʱ = '��';
-- 18023
select count(*) from bigdata_ts_or_prcScope_s01 where �Ƿ���� = '��';
-- 2266
select count(*) from bigdata_ts_or_prcScope_s01 where �Ƿ���� = '��' and �Ƿ���� = '��';

--20398
select 109+18023+2266 from dual;

-- 20288
select count(*) from BIGDATA_USER_INFO_S01
--19638
select count(*) from BIGDATA_USER_INFO_S01 where ��ǰ�Ƿ����� = '������';
--650
select count(*) from BIGDATA_USER_INFO_S01 where ��ǰ�Ƿ����� = '����';
select 19638+650 from dual;


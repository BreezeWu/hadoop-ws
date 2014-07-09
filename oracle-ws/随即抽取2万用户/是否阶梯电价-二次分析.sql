--select count(*) from bigdata_ts_or_prcScope_s01 where 是否分时 = '是';
select * from bigdata_ts_or_prcScope_s01;

--识别重复了的记录
select 用户号,count(用户号) from bigdata_ts_or_prcScope_s01
    GROUP BY 用户号
    having count(用户号) > 1
    
select * from bigdata_ts_or_prcScope_s01 where 用户号 = '8076811676' 

--识别重复了的记录
select 用户号,count(用户号) from BIGDATA_USER_INFO_S01
    GROUP BY 用户号
    having count(用户号) > 1
    
select * from BIGDATA_USER_INFO_S01 where 用户号 = '8076811676' 

--20288
select count(distinct 用户号) from bigdata_ts_or_prcScope_s01
--20288
select count(distinct 用户号) from BIGDATA_USER_INFO_S01

  
--重复了的记录
s
select * from BIGDATA_USER_INFO_S01 where 用户号 ='8076811676'
    

select 用户号 from BIGDATA_USER_INFO_S01 -- order by 用户号
minus
select 用户号 from bigdata_ts_or_prcScope_s01 --order by 用户号;

select 用户号 from bigdata_ts_or_prcScope_s01
minus
select 用户号 from BIGDATA_USER_INFO_S01

--20289
select count(*) from bigdata_ts_or_prcScope_s01
-- 109
select count(*) from bigdata_ts_or_prcScope_s01 where 是否分时 = '是';
-- 18023
select count(*) from bigdata_ts_or_prcScope_s01 where 是否阶梯 = '是';
-- 2266
select count(*) from bigdata_ts_or_prcScope_s01 where 是否阶梯 = '否' and 是否阶梯 = '否';

--20398
select 109+18023+2266 from dual;

-- 20288
select count(*) from BIGDATA_USER_INFO_S01
--19638
select count(*) from BIGDATA_USER_INFO_S01 where 当前是否销户 = '非销户';
--650
select count(*) from BIGDATA_USER_INFO_S01 where 当前是否销户 = '销户';
select 19638+650 from dual;


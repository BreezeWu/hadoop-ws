/*
C_CONS_BIGDATE_YK            
ARC_E_CONS_PRC_AMT_BIGDATE_YK
C_MP_BIGDATE_YK              
*/
-- 计量点月用电量信息
select count(*) from bigdata_mpvolume; -- 275916
select min(ym), max(ym) from bigdata_mpvolume; -- 201201, AAAAAA
-- 删除ym是'AAAAAA'的数据
delete from bigdata_mpvolume where ym = 'AAAAAA';
select count(*) from bigdata_mpvolume; -- 275905
select min(ym), max(ym) from bigdata_mpvolume; -- 201201, 201409
select count(distinct mp_id) from bigdata_mpvolume;  -- 15677
--行转列
/*
//scala代码生成行专列语句
val range2=Range(201201, 201212+1)
val range3=Range(201301, 201312+1)
val range4=Range(201401, 201412+1)

//样例 :	,sum((case when ym='201207' then volume else 0 end)) as "v201207"

// 注意， 大写的V
def myPrint() = {
range2.foreach(x => println(",sum((case when ym='" +x+"' then volume else 0 end)) as \"V" +x+"\""))
println()
range3.foreach(x => println(",sum((case when ym='" +x+"' then volume else 0 end)) as \"V" +x+"\""))
println()
range4.foreach(x => println(",sum((case when ym='" +x+"' then volume else 0 end)) as \"V" +x+"\""))
}

myPrint
*/
-- drop table bigdata_mpvolume_onerow
create table bigdata_mpvolume_onerow as
select mp_id,
          mp_no,
          mp_name,
          mp_addr,
          volt_code,
          app_date,
          run_date,
          org_no,
          mr_sect_no,
          line_id,
          tg_id,
          status_code,
          cons_id,
          mp_cap,
          cons_no,
          zxzb,
          cons_name,
          elec_addr,
          trade_code,
          elec_type_code,
          contract_cap,
          run_cap,
          build_date,
          ps_date,
          cancel_date,
          due_date
,sum((case when ym='201201' then volume else 0 end)) as "V201201"
,sum((case when ym='201202' then volume else 0 end)) as "V201202"
,sum((case when ym='201203' then volume else 0 end)) as "V201203"
,sum((case when ym='201204' then volume else 0 end)) as "V201204"
,sum((case when ym='201205' then volume else 0 end)) as "V201205"
,sum((case when ym='201206' then volume else 0 end)) as "V201206"
,sum((case when ym='201207' then volume else 0 end)) as "V201207"
,sum((case when ym='201208' then volume else 0 end)) as "V201208"
,sum((case when ym='201209' then volume else 0 end)) as "V201209"
,sum((case when ym='201210' then volume else 0 end)) as "V201210"
,sum((case when ym='201211' then volume else 0 end)) as "V201211"
,sum((case when ym='201212' then volume else 0 end)) as "V201212"

,sum((case when ym='201301' then volume else 0 end)) as "V201301"
,sum((case when ym='201302' then volume else 0 end)) as "V201302"
,sum((case when ym='201303' then volume else 0 end)) as "V201303"
,sum((case when ym='201304' then volume else 0 end)) as "V201304"
,sum((case when ym='201305' then volume else 0 end)) as "V201305"
,sum((case when ym='201306' then volume else 0 end)) as "V201306"
,sum((case when ym='201307' then volume else 0 end)) as "V201307"
,sum((case when ym='201308' then volume else 0 end)) as "V201308"
,sum((case when ym='201309' then volume else 0 end)) as "V201309"
,sum((case when ym='201310' then volume else 0 end)) as "V201310"
,sum((case when ym='201311' then volume else 0 end)) as "V201311"
,sum((case when ym='201312' then volume else 0 end)) as "V201312"

,sum((case when ym='201401' then volume else 0 end)) as "V201401"
,sum((case when ym='201402' then volume else 0 end)) as "V201402"
,sum((case when ym='201403' then volume else 0 end)) as "V201403"
,sum((case when ym='201404' then volume else 0 end)) as "V201404"
,sum((case when ym='201405' then volume else 0 end)) as "V201405"
,sum((case when ym='201406' then volume else 0 end)) as "V201406"
,sum((case when ym='201407' then volume else 0 end)) as "V201407"
,sum((case when ym='201408' then volume else 0 end)) as "V201408"
,sum((case when ym='201409' then volume else 0 end)) as "V201409"
,sum((case when ym='201410' then volume else 0 end)) as "V201410"
,sum((case when ym='201411' then volume else 0 end)) as "V201411"
,sum((case when ym='201412' then volume else 0 end)) as "V201412"
from bigdata_mpvolume
group by 
		  mp_id,
          mp_no,
          mp_name,
          mp_addr,
          volt_code,
          app_date,
          run_date,
          org_no,
          mr_sect_no,
          line_id,
          tg_id,
          status_code,
          cons_id,
          mp_cap,
          cons_no,
          zxzb,
          cons_name,
          elec_addr,
          trade_code,
          elec_type_code,
          contract_cap,
          run_cap,
          build_date,
          ps_date,
          cancel_date,
          due_date
;

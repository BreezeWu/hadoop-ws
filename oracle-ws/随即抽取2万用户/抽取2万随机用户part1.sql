-------------------------------------------------------------------------------
----创建DB连接
--DROP DATABASE LINK "DBLINK_EPMPLN";
--
--CREATE DATABASE LINK "DBLINK_EPMPLN"
-- CONNECT TO JTYDFX_SELECT
-- IDENTIFIED BY jtydfx2014
-- USING '10.160.8.22:1521/EPMPLN';
-- 
-------------------------------------------------------------------------------
--------用户基本信息
--drop table bigdata_user_info_s01;  ---两万随机用户  抽样0.1%
--drop table bigdata_user_info_s10;  ---二十万随机用户 抽样10%，大约200万户

------用户月用电量
--drop table bigdata_arc_volume_perM_s01;
--drop table user_arc_snap_per_M_ALL ---所有用户月用电量

---违约用电次数
--drop table bigdata_power_steal_perY_s01;
--drop table bigdata_power_steal_perY_ALL;   --所有用户逐年违约次数
    
-- 用户逐年缴费方式变更次数
--drop table bigdata_change_payType_pY_s01;  --抽样0.1% 用户逐年缴费方式变更次数

-- 用户逐月缴费欠费信息
--drop table bigdata_rcvbl_flow_pM_s01;  --抽样0.1% 用户逐年缴费方式变更次数

--是否阶梯电价
--drop table bigdata_ts_or_prcScope_s01;

--分时电价电量
--drop table bigdata_volume_of_ts_s01

--阶梯电价电量
--drop table bigdata_volume_of_prc_s01;

select count(*) from bigdata_user_info_s01;
select count(*) from bigdata_arc_volume_perM_s01;
select count(*) from bigdata_power_steal_perY_s01;
select count(*) from bigdata_change_payType_pY_s01;
select count(*) from bigdata_rcvbl_flow_pM_s01;
select count(*) from bigdata_rcvbl_flow_pM_s01_l;

--alter table bigdata_user_info_s01 rename column 电表号 to 用户号;
-------------------------------------------------------------------------------
------------
create table bigdata_user_info_s01 as
(
------用户基本信息begin
    select 
        a.cons_id 用户id,a.cons_name 客户名称,b.prc_code 电价代码, a.org_no 地市局编码,
        ' ' 用电性质, 
        
        (select d.PROP_LIST_NAME from epsa_ln.sa_prop_list d 
        where d.PROP_TYPE_ID= 'elec_type_code' and d.PROP_LIST_ID=elec_type_code) 用电类别,
        
        (select d.PROP_LIST_NAME from epsa_ln.sa_prop_list d 
        where d.PROP_TYPE_ID= 'volt_code' and d.PROP_LIST_ID=volt_code)  供电电压,
        contract_cap 合同容量,
        
        (select d.PROP_LIST_NAME from epsa_ln.sa_prop_list d 
        where d.PROP_TYPE_ID= 'lode_attr_code' and d.PROP_LIST_ID=LODE_ATTR_CODE) 负荷类型,
        
        (case when a.status_code='9' then '销户'else '非销户'end) 当前是否销户,
        
        (select d.PROP_LIST_NAME from epsa_ln.sa_prop_list d 
        where d.PROP_TYPE_ID= 'urban_rural_flag' and d.PROP_LIST_ID=URBAN_RURAL_FLAG)  城镇用户农村用户,
        
        ' ' 出账周期,
        (select trade from epm_ln.sa_c_trade_type e where e.trade_code= a.trade_code) 行业,
        
         (select d.PROP_LIST_NAME from epsa_ln.sa_prop_list d 
        where d.PROP_TYPE_ID= 'cust_type_code' and d.PROP_LIST_ID=cons_sort_code)  高压低压
        
        ,a.cons_no 用户号 --电表号
        ,a.volt_code 电压
        
    from c_cons sample(0.1) a ,c_cons_prc b
    where a.cons_id = b.cons_id and a.elec_type_code not like '0%' --;
    
        --and a.cons_id=25297439 --;
        --and rownum <= 1000 --0000 --;
------用户基本信息end
);
-------------------------------------------------------------------------------
--用户月用电量
--drop table user_arc_snap_per_M_s1 ---抽样1%
--drop table user_arc_snap_per_M_s10 ---抽样10%
--drop table user_arc_snap_per_M_ALL ---所有用户月用电量
--create table user_arc_snap_per_M_s10    ---抽样10%

--------------------*******************--------------------
--抽样0.1%用户月用电量
create table bigdata_arc_volume_perM_s01 ---抽样0.1%
as
(
---月用电量
    select cons_id 用户id, ym 年月,
        sum(t_settle_pq) 月用电量
    from (    
        select 
            snap.cons_id, snap.ym, amt.t_settle_pq
        from arc_e_cons_snap snap , Arc_e_Cons_Prc_Amt amt
            ,bigdata_user_info_s01 a
        where         
            snap.ym=amt.ym
            and snap.calc_id=amt.calc_id
            and SNAP.cons_id = a.用户id 
            --and  substr(snap.ym,1,4) = '2013'
--            and SNAP.cons_id in 
--                (select 用户id from bigdata_user_info_s01)
--            --and snap.ym=201207
--            and 
--                substr(snap.ym,1,4) = '2013'
    )
    group by cons_id,ym    --cons_id,ym
--------------------
);


----------------------*******************--------------------
-----所有用户月用电量
--create table bigdata_arc_volume_perM_ALL ---所有用户月用电量
--as
--(
-----月用电量
--    select cons_id 用户id, ym 年月,
--        sum(t_settle_pq) 月用电量
--    from (    
--        select 
--            snap.cons_id, snap.ym, amt.t_settle_pq
--        from arc_e_cons_snap snap , Arc_e_Cons_Prc_Amt amt
--            ,c_cons a
--        where         
--            snap.ym=amt.ym
--            and snap.calc_id=amt.calc_id
------            and SNAP.cons_id in 
------                (select 用户id from user_info_s10
------                --where rownum < 10
------                )
----            --and snap.ym=201207
----            and 
----                substr(snap.ym,1,4) = '2013'
--    )
--    group by cons_id,ym
----------------------
--);

-------------------------------------------------------------------------------
--drop table bigdata_power_steal_s01;
--drop table bigdata_power_steal_ALL;
--create table bigdata_power_steal_ALL        --所有用户逐年违约次数
--create table bigdata_power_steal_s01        --抽样0.1%
create table bigdata_power_steal_perY_s01        --用户逐年违约次数   0.1%
as
(
---违约用电次数
    select   用户id, 年份
        ,count(inspect_id) 违约用电次数 
    from
    (
        select a.用户id 用户id,d.cons_no 用户号
            ,substr(d.occur_time,1,4) 年份
            , d.*
            from epm_ln.s_power_violate_steal d
                , bigdata_user_info_s01 a
        where
            d.cons_no = a.用户号
--            and d.occur_time between  '20130101' and '20131231' 
--            
--            and d.cons_no in (
--                6220083087,
--                0251130642,
--                0268665717,
--                0253499893,
--                7160506247,
--                8051903514,
--                8006116712,
--                8032318894)
    )          
    group by 用户id,年份
--------------------
);    

--select count(*) from bigdata_power_steal_perY_s01
select count(*) from bigdata_change_payType_pY_s01
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
--drop table bigdata_change_payType_pY_ALL;
--create table bigdata_change_payType_pY_ALL        --所有用户逐年缴费方式变更次数

--drop table bigdata_change_payType_pY_s01;
--create table bigdata_change_payType_pY_s01        --抽样0.1% 用户逐年缴费方式变更次数

create table bigdata_change_payType_pY_s01   --抽样0.1% 用户逐年缴费方式变更次数
as
(
---缴费方式变更次数
    select   用户id, 年份
        ,count(app_id) 缴费方式变更次数 
    from
    (
        select 
            --substr(handle_time,1,4) 年份
            cons_id 用户id,
            to_char(handle_time,'yyyy') 年份
            ,x.*
        from epm_ln.arc_s_app x, bigdata_user_info_s01 a
        where 
            app_type_code ='303' 
            and cons_id = a.用户id
    )          
    group by 用户id,年份
--------------------
);    
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
--drop table  bigdata_a_rcvbl_flow_pM_ALL;
--create table bigdata_a_rcvbl_flow_pM_ALL        --每月电费欠费等信息

--drop table bigdata_a_rcvbl_flow_pM_s01;
--create table bigdata_a_rcvbl_flow_pM_s01        --抽样0.1% 每月电费欠费等信息

create table bigdata_rcvbl_flow_pM_s01   --抽样0.1% 每月电费欠费等信息
as
(
---每月电费欠费等信息
        select 
            x.cons_no 用户号
            ,x.rcvbl_ym 年月
            ,sum(x.rcved_amt) 实收
            ,sum(x.rcvbl_amt) 应收
            ,sum(x.rcvbl_amt - x.rcved_amt + x.rcvbl_penalty - x.rcved_penalty) 欠费
          from a_rcvbl_flow x
                ,bigdata_user_info_s01 a
         where x.cons_no = a.用户号
           and x.settle_flag != '03'
           and x.rcvbl_ym between '201201' and '201405'
         group by x.cons_no, x.rcvbl_ym
--------------------
);  
/*
create table bigdata_rcvbl_flow_pM_s01_l   --抽样0.1% 每月电费欠费等信息 [明细数据]
as
(
---每月电费欠费等信息
        select 
            x.*
          from a_rcvbl_flow x
                ,bigdata_user_info_s01 a
         where x.cons_no = a.用户号
           and x.settle_flag != '03'
           and x.rcvbl_ym between '201201' and '201405'
--------------------
);  
*/  
/*
select sum(x.rcvbl_amt - x.rcved_amt + x.rcvbl_penalty - x.rcved_penalty) 欠费,
       x.rcvbl_ym 电费年月
  from a_rcvbl_flow a
 where x.cons_no = '0250049402'  --8196425805
   and x.settle_flag != '03'
   --and x.rcvbl_ym between '201301' and '201312'
 group by x.rcvbl_ym;
 */
-------------------------------------------------------------------------------

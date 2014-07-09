--select count(*) from bigdata_volume_of_prcScope_s01;
create table bigdata_volume_of_ts_s01
as
( 
-------------------------------------------------------------------------------
---分时耗电率

    select 
        snap.cons_no,snap.ym,
        
    (
    select nvl(sum(kwh.settle_apq),0) from arc_e_kwh_amt kwh ,arc_e_cons_prc_amt amt
    where kwh.prc_amt_id=amt.prc_amt_id
    and amt.calc_id=snap.calc_id
    and amt.ym=snap.ym
    and kwh.ym=snap.ym
    and kwh.prc_ts_code='01'
    )"尖峰电量" ,
    (
    select max(kwh.kwh_prc) from arc_e_kwh_amt kwh ,arc_e_cons_prc_amt amt
    where kwh.prc_amt_id=amt.prc_amt_id
    and amt.calc_id=snap.calc_id
    and amt.ym=snap.ym
    and kwh.ym=snap.ym
    and kwh.prc_ts_code='01'
    )"尖峰电价" ,
    (
    select nvl(sum(kwh.settle_apq),0) from arc_e_kwh_amt kwh ,arc_e_cons_prc_amt amt
    where kwh.prc_amt_id=amt.prc_amt_id
    and amt.calc_id=snap.calc_id
    and amt.ym=snap.ym
    and kwh.ym=snap.ym
    and kwh.prc_ts_code='02'
    )"峰电量" ,
    (
    select max(kwh.kwh_prc) from arc_e_kwh_amt kwh ,arc_e_cons_prc_amt amt
    where kwh.prc_amt_id=amt.prc_amt_id
    and amt.calc_id=snap.calc_id
    and amt.ym=snap.ym
    and kwh.ym=snap.ym
    and kwh.prc_ts_code='02'
    )"峰电价" ,
    (
    select nvl(sum(kwh.settle_apq),0) from arc_e_kwh_amt kwh ,arc_e_cons_prc_amt amt
    where kwh.prc_amt_id=amt.prc_amt_id
    and amt.calc_id=snap.calc_id
    and amt.ym=snap.ym
    and kwh.ym=snap.ym
    and kwh.prc_ts_code='03'
    )"平电量" ,
    (
    select max(kwh.kwh_prc) from arc_e_kwh_amt kwh ,arc_e_cons_prc_amt amt
    where kwh.prc_amt_id=amt.prc_amt_id
    and amt.calc_id=snap.calc_id
    and amt.ym=snap.ym
    and kwh.ym=snap.ym
    and kwh.prc_ts_code='03'
    )"平电价" ,
    (
    select nvl(sum(kwh.settle_apq),0) from arc_e_kwh_amt kwh ,arc_e_cons_prc_amt amt
    where kwh.prc_amt_id=amt.prc_amt_id
    and amt.calc_id=snap.calc_id
    and amt.ym=snap.ym
    and kwh.ym=snap.ym
    and kwh.prc_ts_code='04'
    )"谷电量" ,
    (
    select max(kwh.kwh_prc) from arc_e_kwh_amt kwh ,arc_e_cons_prc_amt amt
    where kwh.prc_amt_id=amt.prc_amt_id
    and amt.calc_id=snap.calc_id
    and amt.ym=snap.ym
    and kwh.ym=snap.ym
    and kwh.prc_ts_code='04'
    )"谷电价" ,
    (
    select nvl(sum(kwh.settle_apq),0) from arc_e_kwh_amt kwh ,arc_e_cons_prc_amt amt
    where kwh.prc_amt_id=amt.prc_amt_id
    and amt.calc_id=snap.calc_id
    and amt.ym=snap.ym
    and kwh.ym=snap.ym
    and kwh.prc_ts_code='05'
    )"脊骨电量" ,
    (
    select max(kwh.kwh_prc) from arc_e_kwh_amt kwh ,arc_e_cons_prc_amt amt
    where kwh.prc_amt_id=amt.prc_amt_id
    and amt.calc_id=snap.calc_id
    and amt.ym=snap.ym
    and kwh.ym=snap.ym
    and kwh.prc_ts_code='05'
    )"脊骨电价"
    from arc_e_cons_snap snap
    where  snap.ym between '201301' and '201312'--= '201307'    ---p_month
    and snap.cons_no in         
        (select 用户号 from bigdata_ts_or_prcScope_s01 where 是否分时 ='是' )
--        (select 用户号 from BIGDATA_USER_INFO_S01 where 是否分时 ='是' )
--        (
--                '0250052688',
--                '0250053216',
--                '0250053229',
--                '0250053232',
--                '0250053245',
--                '0250053258'
--       )
     
-------------------------------------------------------------------------------
);
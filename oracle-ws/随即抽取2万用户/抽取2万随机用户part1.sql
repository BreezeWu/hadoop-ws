-------------------------------------------------------------------------------
----����DB����
--DROP DATABASE LINK "DBLINK_EPMPLN";
--
--CREATE DATABASE LINK "DBLINK_EPMPLN"
-- CONNECT TO JTYDFX_SELECT
-- IDENTIFIED BY jtydfx2014
-- USING '10.160.8.22:1521/EPMPLN';
-- 
-------------------------------------------------------------------------------
--------�û�������Ϣ
--drop table bigdata_user_info_s01;  ---��������û�  ����0.1%
--drop table bigdata_user_info_s10;  ---��ʮ������û� ����10%����Լ200��

------�û����õ���
--drop table bigdata_arc_volume_perM_s01;
--drop table user_arc_snap_per_M_ALL ---�����û����õ���

---ΥԼ�õ����
--drop table bigdata_power_steal_perY_s01;
--drop table bigdata_power_steal_perY_ALL;   --�����û�����ΥԼ����
    
-- �û�����ɷѷ�ʽ�������
--drop table bigdata_change_payType_pY_s01;  --����0.1% �û�����ɷѷ�ʽ�������

-- �û����½ɷ�Ƿ����Ϣ
--drop table bigdata_rcvbl_flow_pM_s01;  --����0.1% �û�����ɷѷ�ʽ�������

--�Ƿ���ݵ��
--drop table bigdata_ts_or_prcScope_s01;

--��ʱ��۵���
--drop table bigdata_volume_of_ts_s01

--���ݵ�۵���
--drop table bigdata_volume_of_prc_s01;

select count(*) from bigdata_user_info_s01;
select count(*) from bigdata_arc_volume_perM_s01;
select count(*) from bigdata_power_steal_perY_s01;
select count(*) from bigdata_change_payType_pY_s01;
select count(*) from bigdata_rcvbl_flow_pM_s01;
select count(*) from bigdata_rcvbl_flow_pM_s01_l;

--alter table bigdata_user_info_s01 rename column ���� to �û���;
-------------------------------------------------------------------------------
------------
create table bigdata_user_info_s01 as
(
------�û�������Ϣbegin
    select 
        a.cons_id �û�id,a.cons_name �ͻ�����,b.prc_code ��۴���, a.org_no ���оֱ���,
        ' ' �õ�����, 
        
        (select d.PROP_LIST_NAME from epsa_ln.sa_prop_list d 
        where d.PROP_TYPE_ID= 'elec_type_code' and d.PROP_LIST_ID=elec_type_code) �õ����,
        
        (select d.PROP_LIST_NAME from epsa_ln.sa_prop_list d 
        where d.PROP_TYPE_ID= 'volt_code' and d.PROP_LIST_ID=volt_code)  �����ѹ,
        contract_cap ��ͬ����,
        
        (select d.PROP_LIST_NAME from epsa_ln.sa_prop_list d 
        where d.PROP_TYPE_ID= 'lode_attr_code' and d.PROP_LIST_ID=LODE_ATTR_CODE) ��������,
        
        (case when a.status_code='9' then '����'else '������'end) ��ǰ�Ƿ�����,
        
        (select d.PROP_LIST_NAME from epsa_ln.sa_prop_list d 
        where d.PROP_TYPE_ID= 'urban_rural_flag' and d.PROP_LIST_ID=URBAN_RURAL_FLAG)  �����û�ũ���û�,
        
        ' ' ��������,
        (select trade from epm_ln.sa_c_trade_type e where e.trade_code= a.trade_code) ��ҵ,
        
         (select d.PROP_LIST_NAME from epsa_ln.sa_prop_list d 
        where d.PROP_TYPE_ID= 'cust_type_code' and d.PROP_LIST_ID=cons_sort_code)  ��ѹ��ѹ
        
        ,a.cons_no �û��� --����
        ,a.volt_code ��ѹ
        
    from c_cons sample(0.1) a ,c_cons_prc b
    where a.cons_id = b.cons_id and a.elec_type_code not like '0%' --;
    
        --and a.cons_id=25297439 --;
        --and rownum <= 1000 --0000 --;
------�û�������Ϣend
);
-------------------------------------------------------------------------------
--�û����õ���
--drop table user_arc_snap_per_M_s1 ---����1%
--drop table user_arc_snap_per_M_s10 ---����10%
--drop table user_arc_snap_per_M_ALL ---�����û����õ���
--create table user_arc_snap_per_M_s10    ---����10%

--------------------*******************--------------------
--����0.1%�û����õ���
create table bigdata_arc_volume_perM_s01 ---����0.1%
as
(
---���õ���
    select cons_id �û�id, ym ����,
        sum(t_settle_pq) ���õ���
    from (    
        select 
            snap.cons_id, snap.ym, amt.t_settle_pq
        from arc_e_cons_snap snap , Arc_e_Cons_Prc_Amt amt
            ,bigdata_user_info_s01 a
        where         
            snap.ym=amt.ym
            and snap.calc_id=amt.calc_id
            and SNAP.cons_id = a.�û�id 
            --and  substr(snap.ym,1,4) = '2013'
--            and SNAP.cons_id in 
--                (select �û�id from bigdata_user_info_s01)
--            --and snap.ym=201207
--            and 
--                substr(snap.ym,1,4) = '2013'
    )
    group by cons_id,ym    --cons_id,ym
--------------------
);


----------------------*******************--------------------
-----�����û����õ���
--create table bigdata_arc_volume_perM_ALL ---�����û����õ���
--as
--(
-----���õ���
--    select cons_id �û�id, ym ����,
--        sum(t_settle_pq) ���õ���
--    from (    
--        select 
--            snap.cons_id, snap.ym, amt.t_settle_pq
--        from arc_e_cons_snap snap , Arc_e_Cons_Prc_Amt amt
--            ,c_cons a
--        where         
--            snap.ym=amt.ym
--            and snap.calc_id=amt.calc_id
------            and SNAP.cons_id in 
------                (select �û�id from user_info_s10
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
--create table bigdata_power_steal_ALL        --�����û�����ΥԼ����
--create table bigdata_power_steal_s01        --����0.1%
create table bigdata_power_steal_perY_s01        --�û�����ΥԼ����   0.1%
as
(
---ΥԼ�õ����
    select   �û�id, ���
        ,count(inspect_id) ΥԼ�õ���� 
    from
    (
        select a.�û�id �û�id,d.cons_no �û���
            ,substr(d.occur_time,1,4) ���
            , d.*
            from epm_ln.s_power_violate_steal d
                , bigdata_user_info_s01 a
        where
            d.cons_no = a.�û���
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
    group by �û�id,���
--------------------
);    

--select count(*) from bigdata_power_steal_perY_s01
select count(*) from bigdata_change_payType_pY_s01
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
--drop table bigdata_change_payType_pY_ALL;
--create table bigdata_change_payType_pY_ALL        --�����û�����ɷѷ�ʽ�������

--drop table bigdata_change_payType_pY_s01;
--create table bigdata_change_payType_pY_s01        --����0.1% �û�����ɷѷ�ʽ�������

create table bigdata_change_payType_pY_s01   --����0.1% �û�����ɷѷ�ʽ�������
as
(
---�ɷѷ�ʽ�������
    select   �û�id, ���
        ,count(app_id) �ɷѷ�ʽ������� 
    from
    (
        select 
            --substr(handle_time,1,4) ���
            cons_id �û�id,
            to_char(handle_time,'yyyy') ���
            ,x.*
        from epm_ln.arc_s_app x, bigdata_user_info_s01 a
        where 
            app_type_code ='303' 
            and cons_id = a.�û�id
    )          
    group by �û�id,���
--------------------
);    
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
--drop table  bigdata_a_rcvbl_flow_pM_ALL;
--create table bigdata_a_rcvbl_flow_pM_ALL        --ÿ�µ��Ƿ�ѵ���Ϣ

--drop table bigdata_a_rcvbl_flow_pM_s01;
--create table bigdata_a_rcvbl_flow_pM_s01        --����0.1% ÿ�µ��Ƿ�ѵ���Ϣ

create table bigdata_rcvbl_flow_pM_s01   --����0.1% ÿ�µ��Ƿ�ѵ���Ϣ
as
(
---ÿ�µ��Ƿ�ѵ���Ϣ
        select 
            x.cons_no �û���
            ,x.rcvbl_ym ����
            ,sum(x.rcved_amt) ʵ��
            ,sum(x.rcvbl_amt) Ӧ��
            ,sum(x.rcvbl_amt - x.rcved_amt + x.rcvbl_penalty - x.rcved_penalty) Ƿ��
          from a_rcvbl_flow x
                ,bigdata_user_info_s01 a
         where x.cons_no = a.�û���
           and x.settle_flag != '03'
           and x.rcvbl_ym between '201201' and '201405'
         group by x.cons_no, x.rcvbl_ym
--------------------
);  
/*
create table bigdata_rcvbl_flow_pM_s01_l   --����0.1% ÿ�µ��Ƿ�ѵ���Ϣ [��ϸ����]
as
(
---ÿ�µ��Ƿ�ѵ���Ϣ
        select 
            x.*
          from a_rcvbl_flow x
                ,bigdata_user_info_s01 a
         where x.cons_no = a.�û���
           and x.settle_flag != '03'
           and x.rcvbl_ym between '201201' and '201405'
--------------------
);  
*/  
/*
select sum(x.rcvbl_amt - x.rcved_amt + x.rcvbl_penalty - x.rcved_penalty) Ƿ��,
       x.rcvbl_ym �������
  from a_rcvbl_flow a
 where x.cons_no = '0250049402'  --8196425805
   and x.settle_flag != '03'
   --and x.rcvbl_ym between '201301' and '201312'
 group by x.rcvbl_ym;
 */
-------------------------------------------------------------------------------

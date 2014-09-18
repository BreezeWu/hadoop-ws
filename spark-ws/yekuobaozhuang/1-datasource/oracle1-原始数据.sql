/*
C_CONS_BIGDATE_YK            
ARC_E_CONS_PRC_AMT_BIGDATE_YK
C_MP_BIGDATE_YK              
*/
create table bigdata_mpinfo as
select * from 
(select c.mp_id,
          c.mp_no,
          c.mp_name,
          c.mp_addr,
          c.volt_code,
          c.app_date,
          c.run_date,
          c.org_no,
          c.mr_sect_no,
          c.line_id,
          c.tg_id,
          c.status_code,
          c.cons_id,
          c.mp_cap,
          c.cons_no,
          c.zxzb,
          a.cons_name,
          a.elec_addr,
          a.trade_code,
          a.elec_type_code,
          a.contract_cap,
          a.run_cap,
          a.build_date,
          a.ps_date,
          a.cancel_date,
          a.due_date,
     from C_CONS_BIGDATE_YK             a,
          ARC_E_CONS_PRC_AMT_BIGDATE_YK b,
          C_MP_BIGDATE_YK               c
    where a.cons_id = c.cons_id
      and a.org_no = c.org_no
 )
-- 计量点月用电量信息
--drop table bigdata_mpvolume;
create table bigdata_mpvolume as 
select * from 
(

select c.mp_id,
          c.mp_no,
          c.mp_name,
          c.mp_addr,
          c.volt_code,
          c.app_date,
          c.run_date,
          c.org_no,
          c.mr_sect_no,
          c.line_id,
          c.tg_id,
          c.status_code,
          c.cons_id,
          c.mp_cap,
          c.cons_no,
          c.zxzb,
          a.cons_name,
          a.elec_addr,
          a.trade_code,
          a.elec_type_code,
          a.contract_cap,
          a.run_cap,
          a.build_date,
          a.ps_date,
          a.cancel_date,
          a.due_date,
          b.ym,
          sum(b.t_settle_pq) as volume
     from C_CONS_BIGDATE_YK             a,
          ARC_E_CONS_PRC_AMT_BIGDATE_YK b,
          C_MP_BIGDATE_YK               c
    where a.cons_id = c.cons_id
      and b.mp_id = c.mp_id
      and b.mp_no = c.mp_no
      and a.org_no = b.org_no
      and a.org_no = c.org_no
      and b.org_no = c.org_no
    group by c.mp_id,
             c.mp_no,
             c.mp_name,
             c.mp_addr,
             c.volt_code,
             c.app_date,
             c.run_date,
             c.org_no,
             c.mr_sect_no,
             c.line_id,
             c.tg_id,
             c.status_code,
             c.cons_id,
             c.mp_cap,
             c.cons_no,
             c.zxzb,
             a.cons_name,
             a.elec_addr,
             a.trade_code,
             a.elec_type_code,
             a.contract_cap,
             a.run_cap,
             a.build_date,
             a.ps_date,
             a.cancel_date,
             a.due_date,
             b.ym
    order by c.org_no, c.mp_id, b.ym
);

-- 查看供电类别信息
select* from epsa_ln.sa_prop_list a where a.PROP_TYPE_ID='elec_type_code'

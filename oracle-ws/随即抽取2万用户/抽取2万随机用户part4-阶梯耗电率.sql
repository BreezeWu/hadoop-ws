create table bigdata_volume_of_prc_s01
as
( 
-------------------------------------------------------------------------------
    ----阶梯耗电率
    select 
        snap.cons_no,snap.ym,
    
    (select nvl(sum(lad.u_pq_1),0)
    from epm_ln.arc_e_mp_ladder_snap lad
    where lad.calc_id=snap.calc_id
    and lad.ym=snap.ym
    and lad.org_no=snap.org_no
    ) "一阶电量" ,                                       ----一阶电量
    (
    select max(det.kwh_prc) from arc_e_cons_prc_amt amt ,e_cat_prc cat, epm_ln.e_cat_prc_det det
    where amt.calc_id=snap.calc_id
    and amt.ym=snap.ym
    --and EPM_LN.uf_is_ladder_prc(amt.PRC_CODE,amt.PARA_VN) = 1
    and exists (select 1 FROM E_CAT_PRC A, epm_ln.E_PRC_SCOPE B
                   WHERE A.CAT_PRC_ID = B.CAT_PRC_ID
                     AND A.PRC_CODE = amt.PRC_CODE
                     AND A.PARA_VN = amt.PARA_VN
                     AND B.RANGE_TYPE_CODE LIKE '3%')
    and amt.prc_code=cat.prc_code
    and cat.para_vn=amt.para_vn
    and cat.cat_prc_id=det.cat_prc_id
    and det.prc_ti_code='03'
    and det.range_type_code=amt.range_type_code
    and amt.range_type_code in ('00','21','22')
    )"一阶电价",
    (select nvl(sum(lad.u_pq_2),0)
    from epm_ln.arc_e_mp_ladder_snap lad
    where lad.calc_id=snap.calc_id
    and lad.ym=snap.ym
    and lad.org_no=snap.org_no
    ) "二阶电量" ,                                       ----一阶电量
    (
    select max(det.kwh_prc)+0.05 from arc_e_cons_prc_amt amt ,e_cat_prc cat, epm_ln.e_cat_prc_det det
    where amt.calc_id=snap.calc_id
    and amt.ym=snap.ym
    --and EPM_LN.uf_is_ladder_prc(amt.PRC_CODE,amt.PARA_VN) = 1
    and exists (select 1 FROM E_CAT_PRC A, epm_ln.E_PRC_SCOPE B
                   WHERE A.CAT_PRC_ID = B.CAT_PRC_ID
                     AND A.PRC_CODE = amt.PRC_CODE
                     AND A.PARA_VN = amt.PARA_VN
                     AND B.RANGE_TYPE_CODE LIKE '3%')
    and amt.prc_code=cat.prc_code
    and cat.para_vn=amt.para_vn
    and cat.cat_prc_id=det.cat_prc_id
    and det.prc_ti_code='03'
    and det.range_type_code=amt.range_type_code
    and amt.range_type_code in ('00','21','22')
    )"二阶电价",
    (select nvl(sum(lad.u_pq_3),0)
    from epm_ln.arc_e_mp_ladder_snap lad
    where lad.calc_id=snap.calc_id
    and lad.ym=snap.ym
    and lad.org_no=snap.org_no
    ) "三阶电量" ,                                       ----一阶电量
    (
    select max(det.kwh_prc)+0.3 from arc_e_cons_prc_amt amt ,e_cat_prc cat, epm_ln.e_cat_prc_det det
    where amt.calc_id=snap.calc_id
    and amt.ym=snap.ym
    --and EPM_LN.uf_is_ladder_prc(amt.PRC_CODE,amt.PARA_VN) = 1
    and exists (select 1 FROM E_CAT_PRC A, epm_ln.E_PRC_SCOPE B
                   WHERE A.CAT_PRC_ID = B.CAT_PRC_ID
                     AND A.PRC_CODE = amt.PRC_CODE
                     AND A.PARA_VN = amt.PARA_VN
                     AND B.RANGE_TYPE_CODE LIKE '3%')
    and amt.prc_code=cat.prc_code
    and cat.para_vn=amt.para_vn
    and cat.cat_prc_id=det.cat_prc_id
    and det.prc_ti_code='03'
    and det.range_type_code=amt.range_type_code
    and amt.range_type_code in ('00','21','22')
    )"三阶电价"
    from arc_e_cons_snap snap 
    where  snap.ym between '201301' and '201312'--= '201307'    ---p_month
    and snap.cons_no in --= --?  ----cons_no
        (select 用户号 from BIGDATA_USER_INFO_S01 )
--        (
--                '0250052688',
--                '0250053216',
--                '0250053229',
--                '0250053232',
--                '0250053245',
--                '0250053258'
--       )

    and exists
    (select 1 from arc_e_consprc_snap prc
    where prc.calc_id=snap.calc_id
    and prc.ym=snap.ym
    --and EPM_LN.uf_is_ladder_prc(prc.PRC_CODE,prc.PARA_VN) = 1 )
    and exists (select 1  FROM E_CAT_PRC A, epm_ln.E_PRC_SCOPE B
                   WHERE A.CAT_PRC_ID = B.CAT_PRC_ID
                     AND A.PRC_CODE = prc.PRC_CODE
                     AND A.PARA_VN = prc.PARA_VN
                     AND B.RANGE_TYPE_CODE LIKE '3%')
    )
 
-------------------------------------------------------------------------------
);

--select count(*) from BIGDATA_USER_INFO_S01;
create table bigdata_ts_or_prcScope_s01
as
( 
-------------------------------------------------------------------------------
---���
select 
    a.cons_no �û���,

    (select x.cat_prc_name
          from epm_ln.e_Cat_Prc x
         where x.para_vn =
               (SELECT PARA_VN
                  FROM epm_ln.E_BILL_PARA_VER
                 WHERE PAR_BGN_DATE <= sysdate
                   AND (PAR_END_DATE IS NULL OR PAR_END_DATE > sysdate)
                   AND RELEASE_FLAG = '1'
                   AND PAR_VER_TYPE = '1')
           and x.prc_code = b.prc_code) "�������",
       decode(b.ts_flag, '1', '��', '0', '��') "�Ƿ��ʱ",
       (case
         when (SELECT COUNT(1)
                 FROM epm_ln.E_CAT_PRC A, epm_ln.E_PRC_SCOPE BB
                WHERE A.CAT_PRC_ID = BB.CAT_PRC_ID
                  AND A.PRC_CODE = b.PRC_CODE
                  AND A.PARA_VN =
                      (SELECT PARA_VN
                         FROM epm_ln.E_BILL_PARA_VER
                        WHERE PAR_BGN_DATE <= sysdate
                          AND (PAR_END_DATE IS NULL OR PAR_END_DATE > sysdate)
                          AND RELEASE_FLAG = '1'
                          AND PAR_VER_TYPE = '1')
                  AND BB.RANGE_TYPE_CODE LIKE '3%') > 0 then
          '��'
         else
          '��'
       end) "�Ƿ����"
  from c_cons a, epm_ln.c_cons_prc b
 where a.cons_id = b.cons_id
   and a.cons_no in 
    (select �û��� from BIGDATA_USER_INFO_S01 )
   
--   (
--            '0250052688',
--            '0250053216',
--            '0250053229',
--            '0250053232',
--            '0250053245',
--            '0250053258'
--   )
-------------------------------------------------------------------------------
);
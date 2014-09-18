select count(*) from bigdata_mpvolume_onerow; -- 15677

select * from bigdata_mpvolume_onerow where rownum < 10;
select count(*) from bigdata_mpvolume_onerow where run_date > TO_DATE('2012-01-01','YYYY-MM-DD') order by run_date

select TO_DATE('2012-01-01','YYYY-MM-DD') from dual;
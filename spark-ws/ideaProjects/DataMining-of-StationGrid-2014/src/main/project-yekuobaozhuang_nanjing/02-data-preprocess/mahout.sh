mahout seqdirectory -i docs -o seqdocs

hadoop dfs -mkdir -p kmeans/ykbz_detail
hadoop dfs -ls -R kmeans
#mahout seqdirectory -i /user/hive/warehouse/ykbz_detail_for_kmeans_tab -o kmeans/ykbz_detail
mahout seqdirectory -i /user/hive/warehouse/ykbz_detail_for_kmeans_tab/ -o kmeans/ykbz_detail/seq

mahout seqdirectory -c UTF-8 -i /user/hive/warehouse/ykbz_detail_for_kmeans_tab/ -o kmeans/ykbz_detail/seq


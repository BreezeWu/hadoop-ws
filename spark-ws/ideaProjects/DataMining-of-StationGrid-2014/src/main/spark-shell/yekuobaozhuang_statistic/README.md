### 执行方法
--- 启动spark-shell
--- 修改 loadDataFromOneFile.scala 文件中的 datasetId 和对应文件路径
--- 加载执行 loadDataFromOneFile.scala
--- 加载执行 statistic_common.scala
--- 加载执行
    statistic_01_le_runnedMonths.scala
    statistic_02_between_runnedMonths.scala
    statistic_03_be_runnedMonths.scala
--- 加载执行
    transferResult2Stdout.scala //在屏幕上输出
    transferResult2CSV.scala //输出到文件
--- 加载执行
    execute_statistic.scala

### 执行命令
cd ~/workspace_github/hadoop-ws/spark-ws/ideaProjects/DataMining-of-StationGrid-2014/src/main/spark-shell/yekuobaozhuang_statistic
export MASTER=local[*]
spark-shell
:load loadDataFromOneFile.scala
:load statistic_common.scala
:load statistic_01_le_runnedMonths.scala
:load statistic_02_between_runnedMonths.scala
:load statistic_03_be_runnedMonths.scala
:load transferResult2Stdout.scala
:load transferResult2CSV.scala
:load execute_statistic.scala

### 其他: 数据探索
:load data-explore.scala

### 使用说明
(1) 选择不同原始数据集, 修改 loadDataFromOneFile.scala 文件中的 datasetId 和对应文件路径
(2) 选择不同的统计方法, 修改 execute_statistic.scala 文件中的 "val curApproach = APPROACH_SEGMENT"
    当前是 APPROACH_SEGMENT
(3) 对于方法三(APPROACH_SEGMENT), 还有三种细分: (请参见"data-explore.scala"和"statistic_03_be_runnedMonths.scala")
    a. 所有月的最大电力都有效(有效样本360个)
    b. 第一个月的最大电力有效(有效样本354个)
    c. 不检查最大电力是否有效(数据量大,约3252个,但无法进行业务解释)
(4) 对于第三种方法, 是混合型样本, 比如, 找到了"运行时长大于3个月"和"运行时长大于6个月"的两个数据集, 前者并不完全包括后者, 但这是两个不同的数据集合.
    我们利用前者来进行"3个月以内, 用户最大电力是否达到合同容量的某个程度", 利用后者来进行"6个月以内, 用户最大电力是否达到合同容量的某个程度".
    所以,最后结果里, 某个比例值(最大电力达到合同容量的程度)其比例并不一定随着月份增加严格增长---总体会增长---因为用户占比的数据集不相同.

    调整方法是, 对每个数据集,都进行 "3个月以内","6个月以内", "9个月以内"...的分析. (当然,若数据集只包括3个月以内的数据, 超过3个月的值总是与3个月相同)
    基于"数据集_2012_原始值_所有用电类别_3.csv"所提供的信息, 我们最后选择">=21个月"这个数据集,对其进行"3/6/9/12/15/18/21个月以内"的统计---而不是只统计"21个月以内".

    ">=21个月"的数据集就是 "data-explore.scala"文件中 rddList_SEGMENT_MAXDL_allIsRight 的第8个元素.(rddList_SEGMENT_MAXDL_allIsRight(7).count == 230)

    spark-shell中的语句为:

    val curApproach = APPROACH_SEGMENT
    val new_orgRdd = rddList_SEGMENT_MAXDL_allIsRight(7)
    val new_specialRecordRddList_MAXDL2Percent_of_ELEC_TYPE_CODE = splitRddBy_ELEC_TYPE_CODE(new_orgRdd, elec_type_code_list)
    val (userCountListList, userCountListList_ELEC_TYPE_CODE) = executerOf_statistic(new_orgRdd, new_specialRecordRddList_MAXDL2Percent_of_ELEC_TYPE_CODE, elec_type_code_list, curApproach)




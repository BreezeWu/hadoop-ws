# -----------------------------------------------------------------------------
# 读取spark分析处理后的数据,供画图程序使用
# -----------------------------------------------------------------------------
# 运行方法: 在R环境中,使用下面语句
#   	source("~/workspace_github/hadoop-ws/rstudio-ws/Visualizing-of-StationGrid-2014/read-data-of-SampleData.R")
#
# -----------------------------------------------------------------------------

# *****************************************************************************
# 也可以调整为每次手动选择文件
#	mydata = read.table(file.choose(), header=FALSE, sep=",")
# *****************************************************************************

# FilePath
# dataSetID <- "s01"  # s98
rootFilePathOfIn <- stringr::str_c("input_SampleData_",dataSetID, "/")

# 文件名
filesVector_s01 <- c(
  ""
)
filesVector_s98 <- c(
  "s98_L2k20_BadF2ExcludeF3_Ladder_sampledata_200_ID0_19137.csv",
  "s98_L2k20_BadF2ExcludeF3_Ladder_sampledata_200_ID10_10634.csv",
  "s98_L2k20_BadF2ExcludeF3_Ladder_sampledata_200_ID11_14808.csv",
  "s98_L2k20_BadF2ExcludeF3_Ladder_sampledata_200_ID1_15114.csv",
  "s98_L2k20_BadF2ExcludeF3_Ladder_sampledata_200_ID12_13082.csv",
  "s98_L2k20_BadF2ExcludeF3_Ladder_sampledata_200_ID13_11773.csv",
  "s98_L2k20_BadF2ExcludeF3_Ladder_sampledata_200_ID14_12366.csv",
  "s98_L2k20_BadF2ExcludeF3_Ladder_sampledata_200_ID15_14949.csv",
  "s98_L2k20_BadF2ExcludeF3_Ladder_sampledata_200_ID16_63021.csv",
  "s98_L2k20_BadF2ExcludeF3_Ladder_sampledata_200_ID17_10699.csv",
  "s98_L2k20_BadF2ExcludeF3_Ladder_sampledata_200_ID18_14156.csv",
  "s98_L2k20_BadF2ExcludeF3_Ladder_sampledata_200_ID19_28838.csv",
  "s98_L2k20_BadF2ExcludeF3_Ladder_sampledata_200_ID2_8661.csv",
  "s98_L2k20_BadF2ExcludeF3_Ladder_sampledata_200_ID3_25118.csv",
  "s98_L2k20_BadF2ExcludeF3_Ladder_sampledata_200_ID4_10378.csv",
  "s98_L2k20_BadF2ExcludeF3_Ladder_sampledata_200_ID5_11625.csv",
  "s98_L2k20_BadF2ExcludeF3_Ladder_sampledata_200_ID6_9448.csv",
  "s98_L2k20_BadF2ExcludeF3_Ladder_sampledata_200_ID7_26647.csv",
  "s98_L2k20_BadF2ExcludeF3_Ladder_sampledata_200_ID8_14951.csv",
  "s98_L2k20_BadF2ExcludeF3_Ladder_sampledata_200_ID9_8467.csv",
  "s98_L2k20_BadF2ExcludeF3_NotTsNotLadder_sampledata_200_ID0_4930.csv",
  "s98_L2k20_BadF2ExcludeF3_NotTsNotLadder_sampledata_200_ID10_1511.csv",
  "s98_L2k20_BadF2ExcludeF3_NotTsNotLadder_sampledata_200_ID11_1823.csv",
  "s98_L2k20_BadF2ExcludeF3_NotTsNotLadder_sampledata_200_ID1_1.csv",
  "s98_L2k20_BadF2ExcludeF3_NotTsNotLadder_sampledata_200_ID12_1441.csv",
  "s98_L2k20_BadF2ExcludeF3_NotTsNotLadder_sampledata_200_ID13_2310.csv",
  "s98_L2k20_BadF2ExcludeF3_NotTsNotLadder_sampledata_200_ID14_2089.csv",
  "s98_L2k20_BadF2ExcludeF3_NotTsNotLadder_sampledata_200_ID15_2452.csv",
  "s98_L2k20_BadF2ExcludeF3_NotTsNotLadder_sampledata_200_ID16_2567.csv",
  "s98_L2k20_BadF2ExcludeF3_NotTsNotLadder_sampledata_200_ID17_2435.csv",
  "s98_L2k20_BadF2ExcludeF3_NotTsNotLadder_sampledata_200_ID18_2550.csv",
  "s98_L2k20_BadF2ExcludeF3_NotTsNotLadder_sampledata_200_ID19_1829.csv",
  "s98_L2k20_BadF2ExcludeF3_NotTsNotLadder_sampledata_200_ID2_2979.csv",
  "s98_L2k20_BadF2ExcludeF3_NotTsNotLadder_sampledata_200_ID3_2939.csv",
  "s98_L2k20_BadF2ExcludeF3_NotTsNotLadder_sampledata_200_ID4_2010.csv",
  "s98_L2k20_BadF2ExcludeF3_NotTsNotLadder_sampledata_200_ID5_2.csv",
  "s98_L2k20_BadF2ExcludeF3_NotTsNotLadder_sampledata_200_ID6_13271.csv",
  "s98_L2k20_BadF2ExcludeF3_NotTsNotLadder_sampledata_200_ID7_1758.csv",
  "s98_L2k20_BadF2ExcludeF3_NotTsNotLadder_sampledata_200_ID8_1650.csv",
  "s98_L2k20_BadF2ExcludeF3_NotTsNotLadder_sampledata_200_ID9_2336.csv",
  "s98_L2k20_BadF2ExcludeF3_Ts_sampledata_200_ID0_34.csv",
  "s98_L2k20_BadF2ExcludeF3_Ts_sampledata_200_ID10_55.csv",
  "s98_L2k20_BadF2ExcludeF3_Ts_sampledata_200_ID11_131.csv",
  "s98_L2k20_BadF2ExcludeF3_Ts_sampledata_200_ID12_25.csv",
  "s98_L2k20_BadF2ExcludeF3_Ts_sampledata_200_ID13_41.csv",
  "s98_L2k20_BadF2ExcludeF3_Ts_sampledata_200_ID1_39.csv",
  "s98_L2k20_BadF2ExcludeF3_Ts_sampledata_200_ID14_31.csv",
  "s98_L2k20_BadF2ExcludeF3_Ts_sampledata_200_ID15_29.csv",
  "s98_L2k20_BadF2ExcludeF3_Ts_sampledata_200_ID16_35.csv",
  "s98_L2k20_BadF2ExcludeF3_Ts_sampledata_200_ID17_39.csv",
  "s98_L2k20_BadF2ExcludeF3_Ts_sampledata_200_ID18_75.csv",
  "s98_L2k20_BadF2ExcludeF3_Ts_sampledata_200_ID19_43.csv",
  "s98_L2k20_BadF2ExcludeF3_Ts_sampledata_200_ID2_34.csv",
  "s98_L2k20_BadF2ExcludeF3_Ts_sampledata_200_ID3_60.csv",
  "s98_L2k20_BadF2ExcludeF3_Ts_sampledata_200_ID4_36.csv",
  "s98_L2k20_BadF2ExcludeF3_Ts_sampledata_200_ID5_50.csv",
  "s98_L2k20_BadF2ExcludeF3_Ts_sampledata_200_ID6_55.csv",
  "s98_L2k20_BadF2ExcludeF3_Ts_sampledata_200_ID7_73.csv",
  "s98_L2k20_BadF2ExcludeF3_Ts_sampledata_200_ID8_34.csv",
  "s98_L2k20_BadF2ExcludeF3_Ts_sampledata_200_ID9_39.csv",
  "s98_L2k20_BadF3_Ladder_sampledata_200_ID0_169262.csv",
  "s98_L2k20_BadF3_Ladder_sampledata_200_ID10_85331.csv",
  "s98_L2k20_BadF3_Ladder_sampledata_200_ID11_94978.csv",
  "s98_L2k20_BadF3_Ladder_sampledata_200_ID12_57081.csv",
  "s98_L2k20_BadF3_Ladder_sampledata_200_ID1_3030150.csv",
  "s98_L2k20_BadF3_Ladder_sampledata_200_ID13_38949.csv",
  "s98_L2k20_BadF3_Ladder_sampledata_200_ID14_62346.csv",
  "s98_L2k20_BadF3_Ladder_sampledata_200_ID15_77420.csv",
  "s98_L2k20_BadF3_Ladder_sampledata_200_ID16_59271.csv",
  "s98_L2k20_BadF3_Ladder_sampledata_200_ID17_65715.csv",
  "s98_L2k20_BadF3_Ladder_sampledata_200_ID18_31937.csv",
  "s98_L2k20_BadF3_Ladder_sampledata_200_ID19_91198.csv",
  "s98_L2k20_BadF3_Ladder_sampledata_200_ID2_76383.csv",
  "s98_L2k20_BadF3_Ladder_sampledata_200_ID3_1.csv",
  "s98_L2k20_BadF3_Ladder_sampledata_200_ID4_72477.csv",
  "s98_L2k20_BadF3_Ladder_sampledata_200_ID5_41081.csv",
  "s98_L2k20_BadF3_Ladder_sampledata_200_ID6_105651.csv",
  "s98_L2k20_BadF3_Ladder_sampledata_200_ID7_33987.csv",
  "s98_L2k20_BadF3_Ladder_sampledata_200_ID8_32729.csv",
  "s98_L2k20_BadF3_Ladder_sampledata_200_ID9_59320.csv",
  "s98_L2k20_BadF3_NotTsNotLadder_sampledata_200_ID0_32691.csv",
  "s98_L2k20_BadF3_NotTsNotLadder_sampledata_200_ID10_1.csv",
  "s98_L2k20_BadF3_NotTsNotLadder_sampledata_200_ID11_1.csv",
  "s98_L2k20_BadF3_NotTsNotLadder_sampledata_200_ID12_1.csv",
  "s98_L2k20_BadF3_NotTsNotLadder_sampledata_200_ID13_19095.csv",
  "s98_L2k20_BadF3_NotTsNotLadder_sampledata_200_ID14_16828.csv",
  "s98_L2k20_BadF3_NotTsNotLadder_sampledata_200_ID1_516000.csv",
  "s98_L2k20_BadF3_NotTsNotLadder_sampledata_200_ID15_1.csv",
  "s98_L2k20_BadF3_NotTsNotLadder_sampledata_200_ID16_11014.csv",
  "s98_L2k20_BadF3_NotTsNotLadder_sampledata_200_ID17_20541.csv",
  "s98_L2k20_BadF3_NotTsNotLadder_sampledata_200_ID18_20327.csv",
  "s98_L2k20_BadF3_NotTsNotLadder_sampledata_200_ID19_20704.csv",
  "s98_L2k20_BadF3_NotTsNotLadder_sampledata_200_ID2_18651.csv",
  "s98_L2k20_BadF3_NotTsNotLadder_sampledata_200_ID3_1.csv",
  "s98_L2k20_BadF3_NotTsNotLadder_sampledata_200_ID4_24185.csv",
  "s98_L2k20_BadF3_NotTsNotLadder_sampledata_200_ID5_1.csv",
  "s98_L2k20_BadF3_NotTsNotLadder_sampledata_200_ID6_1.csv",
  "s98_L2k20_BadF3_NotTsNotLadder_sampledata_200_ID7_28122.csv",
  "s98_L2k20_BadF3_NotTsNotLadder_sampledata_200_ID8_19197.csv",
  "s98_L2k20_BadF3_NotTsNotLadder_sampledata_200_ID9_19683.csv",
  "s98_L2k20_BadF3_Ts_sampledata_200_ID0_516.csv",
  "s98_L2k20_BadF3_Ts_sampledata_200_ID10_226.csv",
  "s98_L2k20_BadF3_Ts_sampledata_200_ID11_1098.csv",
  "s98_L2k20_BadF3_Ts_sampledata_200_ID1_11217.csv",
  "s98_L2k20_BadF3_Ts_sampledata_200_ID12_329.csv",
  "s98_L2k20_BadF3_Ts_sampledata_200_ID13_327.csv",
  "s98_L2k20_BadF3_Ts_sampledata_200_ID14_236.csv",
  "s98_L2k20_BadF3_Ts_sampledata_200_ID15_398.csv",
  "s98_L2k20_BadF3_Ts_sampledata_200_ID16_336.csv",
  "s98_L2k20_BadF3_Ts_sampledata_200_ID17_245.csv",
  "s98_L2k20_BadF3_Ts_sampledata_200_ID18_423.csv",
  "s98_L2k20_BadF3_Ts_sampledata_200_ID19_454.csv",
  "s98_L2k20_BadF3_Ts_sampledata_200_ID2_367.csv",
  "s98_L2k20_BadF3_Ts_sampledata_200_ID3_270.csv",
  "s98_L2k20_BadF3_Ts_sampledata_200_ID4_412.csv",
  "s98_L2k20_BadF3_Ts_sampledata_200_ID5_369.csv",
  "s98_L2k20_BadF3_Ts_sampledata_200_ID6_331.csv",
  "s98_L2k20_BadF3_Ts_sampledata_200_ID7_287.csv",
  "s98_L2k20_BadF3_Ts_sampledata_200_ID8_502.csv",
  "s98_L2k20_BadF3_Ts_sampledata_200_ID9_462.csv",
  "s98_L2k20_GoodM1_Ladder_sampledata_200_ID0_1395736.csv",
  "s98_L2k20_GoodM1_Ladder_sampledata_200_ID10_1.csv",
  "s98_L2k20_GoodM1_Ladder_sampledata_200_ID11_1176050.csv",
  "s98_L2k20_GoodM1_Ladder_sampledata_200_ID1_1.csv",
  "s98_L2k20_GoodM1_Ladder_sampledata_200_ID12_277410.csv",
  "s98_L2k20_GoodM1_Ladder_sampledata_200_ID13_306176.csv",
  "s98_L2k20_GoodM1_Ladder_sampledata_200_ID14_546575.csv",
  "s98_L2k20_GoodM1_Ladder_sampledata_200_ID15_613546.csv",
  "s98_L2k20_GoodM1_Ladder_sampledata_200_ID16_455393.csv",
  "s98_L2k20_GoodM1_Ladder_sampledata_200_ID17_393788.csv",
  "s98_L2k20_GoodM1_Ladder_sampledata_200_ID18_476252.csv",
  "s98_L2k20_GoodM1_Ladder_sampledata_200_ID19_822724.csv",
  "s98_L2k20_GoodM1_Ladder_sampledata_200_ID2_239934.csv",
  "s98_L2k20_GoodM1_Ladder_sampledata_200_ID3_343434.csv",
  "s98_L2k20_GoodM1_Ladder_sampledata_200_ID4_1071812.csv",
  "s98_L2k20_GoodM1_Ladder_sampledata_200_ID5_651607.csv",
  "s98_L2k20_GoodM1_Ladder_sampledata_200_ID6_286744.csv",
  "s98_L2k20_GoodM1_Ladder_sampledata_200_ID7_1335612.csv",
  "s98_L2k20_GoodM1_Ladder_sampledata_200_ID8_520521.csv",
  "s98_L2k20_GoodM1_Ladder_sampledata_200_ID9_390644.csv",
  "s98_L2k20_GoodM1_NotTsNotLadder_sampledata_200_ID0_112027.csv",
  "s98_L2k20_GoodM1_NotTsNotLadder_sampledata_200_ID10_1.csv",
  "s98_L2k20_GoodM1_NotTsNotLadder_sampledata_200_ID11_49958.csv",
  "s98_L2k20_GoodM1_NotTsNotLadder_sampledata_200_ID1_23676.csv",
  "s98_L2k20_GoodM1_NotTsNotLadder_sampledata_200_ID12_45232.csv",
  "s98_L2k20_GoodM1_NotTsNotLadder_sampledata_200_ID13_29921.csv",
  "s98_L2k20_GoodM1_NotTsNotLadder_sampledata_200_ID14_81588.csv",
  "s98_L2k20_GoodM1_NotTsNotLadder_sampledata_200_ID15_67321.csv",
  "s98_L2k20_GoodM1_NotTsNotLadder_sampledata_200_ID16_29837.csv",
  "s98_L2k20_GoodM1_NotTsNotLadder_sampledata_200_ID17_34152.csv",
  "s98_L2k20_GoodM1_NotTsNotLadder_sampledata_200_ID18_109142.csv",
  "s98_L2k20_GoodM1_NotTsNotLadder_sampledata_200_ID19_41937.csv",
  "s98_L2k20_GoodM1_NotTsNotLadder_sampledata_200_ID2_28303.csv",
  "s98_L2k20_GoodM1_NotTsNotLadder_sampledata_200_ID3_71167.csv",
  "s98_L2k20_GoodM1_NotTsNotLadder_sampledata_200_ID4_52691.csv",
  "s98_L2k20_GoodM1_NotTsNotLadder_sampledata_200_ID5_1.csv",
  "s98_L2k20_GoodM1_NotTsNotLadder_sampledata_200_ID6_43523.csv",
  "s98_L2k20_GoodM1_NotTsNotLadder_sampledata_200_ID7_107836.csv",
  "s98_L2k20_GoodM1_NotTsNotLadder_sampledata_200_ID8_32846.csv",
  "s98_L2k20_GoodM1_NotTsNotLadder_sampledata_200_ID9_58930.csv",
  "s98_L2k20_GoodM1_Ts_sampledata_200_ID0_3816.csv",
  "s98_L2k20_GoodM1_Ts_sampledata_200_ID10_2231.csv",
  "s98_L2k20_GoodM1_Ts_sampledata_200_ID11_1226.csv",
  "s98_L2k20_GoodM1_Ts_sampledata_200_ID12_1255.csv",
  "s98_L2k20_GoodM1_Ts_sampledata_200_ID13_1303.csv",
  "s98_L2k20_GoodM1_Ts_sampledata_200_ID1_3.csv",
  "s98_L2k20_GoodM1_Ts_sampledata_200_ID14_1368.csv",
  "s98_L2k20_GoodM1_Ts_sampledata_200_ID15_1599.csv",
  "s98_L2k20_GoodM1_Ts_sampledata_200_ID16_1179.csv",
  "s98_L2k20_GoodM1_Ts_sampledata_200_ID17_1297.csv",
  "s98_L2k20_GoodM1_Ts_sampledata_200_ID18_1712.csv",
  "s98_L2k20_GoodM1_Ts_sampledata_200_ID19_2833.csv",
  "s98_L2k20_GoodM1_Ts_sampledata_200_ID2_3657.csv",
  "s98_L2k20_GoodM1_Ts_sampledata_200_ID3_1780.csv",
  "s98_L2k20_GoodM1_Ts_sampledata_200_ID4_4579.csv",
  "s98_L2k20_GoodM1_Ts_sampledata_200_ID5_1698.csv",
  "s98_L2k20_GoodM1_Ts_sampledata_200_ID6_938.csv",
  "s98_L2k20_GoodM1_Ts_sampledata_200_ID7_1398.csv",
  "s98_L2k20_GoodM1_Ts_sampledata_200_ID8_777.csv",
  "s98_L2k20_GoodM1_Ts_sampledata_200_ID9_2137.csv",
  "s98_L2k20_GoodM2_Ladder_sampledata_200_ID0_569419.csv",
  "s98_L2k20_GoodM2_Ladder_sampledata_200_ID10_51784.csv",
  "s98_L2k20_GoodM2_Ladder_sampledata_200_ID11_28082.csv",
  "s98_L2k20_GoodM2_Ladder_sampledata_200_ID1_20159.csv",
  "s98_L2k20_GoodM2_Ladder_sampledata_200_ID12_374051.csv",
  "s98_L2k20_GoodM2_Ladder_sampledata_200_ID13_57583.csv",
  "s98_L2k20_GoodM2_Ladder_sampledata_200_ID14_77882.csv",
  "s98_L2k20_GoodM2_Ladder_sampledata_200_ID15_84541.csv",
  "s98_L2k20_GoodM2_Ladder_sampledata_200_ID16_94882.csv",
  "s98_L2k20_GoodM2_Ladder_sampledata_200_ID17_212218.csv",
  "s98_L2k20_GoodM2_Ladder_sampledata_200_ID18_35711.csv",
  "s98_L2k20_GoodM2_Ladder_sampledata_200_ID19_30342.csv",
  "s98_L2k20_GoodM2_Ladder_sampledata_200_ID2_64437.csv",
  "s98_L2k20_GoodM2_Ladder_sampledata_200_ID3_35492.csv",
  "s98_L2k20_GoodM2_Ladder_sampledata_200_ID4_20577.csv",
  "s98_L2k20_GoodM2_Ladder_sampledata_200_ID5_37095.csv",
  "s98_L2k20_GoodM2_Ladder_sampledata_200_ID6_79806.csv",
  "s98_L2k20_GoodM2_Ladder_sampledata_200_ID7_119320.csv",
  "s98_L2k20_GoodM2_Ladder_sampledata_200_ID8_73962.csv",
  "s98_L2k20_GoodM2_Ladder_sampledata_200_ID9_23525.csv",
  "s98_L2k20_GoodM2_NotTsNotLadder_sampledata_200_ID0_4087.csv",
  "s98_L2k20_GoodM2_NotTsNotLadder_sampledata_200_ID10_3134.csv",
  "s98_L2k20_GoodM2_NotTsNotLadder_sampledata_200_ID11_1.csv",
  "s98_L2k20_GoodM2_NotTsNotLadder_sampledata_200_ID1_1.csv",
  "s98_L2k20_GoodM2_NotTsNotLadder_sampledata_200_ID12_4756.csv",
  "s98_L2k20_GoodM2_NotTsNotLadder_sampledata_200_ID13_4291.csv",
  "s98_L2k20_GoodM2_NotTsNotLadder_sampledata_200_ID14_4100.csv",
  "s98_L2k20_GoodM2_NotTsNotLadder_sampledata_200_ID15_2948.csv",
  "s98_L2k20_GoodM2_NotTsNotLadder_sampledata_200_ID16_3979.csv",
  "s98_L2k20_GoodM2_NotTsNotLadder_sampledata_200_ID17_2672.csv",
  "s98_L2k20_GoodM2_NotTsNotLadder_sampledata_200_ID18_3714.csv",
  "s98_L2k20_GoodM2_NotTsNotLadder_sampledata_200_ID19_4508.csv",
  "s98_L2k20_GoodM2_NotTsNotLadder_sampledata_200_ID2_10311.csv",
  "s98_L2k20_GoodM2_NotTsNotLadder_sampledata_200_ID3_3006.csv",
  "s98_L2k20_GoodM2_NotTsNotLadder_sampledata_200_ID4_5064.csv",
  "s98_L2k20_GoodM2_NotTsNotLadder_sampledata_200_ID5_9884.csv",
  "s98_L2k20_GoodM2_NotTsNotLadder_sampledata_200_ID6_3102.csv",
  "s98_L2k20_GoodM2_NotTsNotLadder_sampledata_200_ID7_6075.csv",
  "s98_L2k20_GoodM2_NotTsNotLadder_sampledata_200_ID8_5091.csv",
  "s98_L2k20_GoodM2_NotTsNotLadder_sampledata_200_ID9_3938.csv",
  "s98_L2k20_GoodM2_Ts_sampledata_200_ID0_38.csv",
  "s98_L2k20_GoodM2_Ts_sampledata_200_ID10_15.csv",
  "s98_L2k20_GoodM2_Ts_sampledata_200_ID11_14.csv",
  "s98_L2k20_GoodM2_Ts_sampledata_200_ID1_13.csv",
  "s98_L2k20_GoodM2_Ts_sampledata_200_ID12_26.csv",
  "s98_L2k20_GoodM2_Ts_sampledata_200_ID13_88.csv",
  "s98_L2k20_GoodM2_Ts_sampledata_200_ID14_28.csv",
  "s98_L2k20_GoodM2_Ts_sampledata_200_ID15_25.csv",
  "s98_L2k20_GoodM2_Ts_sampledata_200_ID16_24.csv",
  "s98_L2k20_GoodM2_Ts_sampledata_200_ID17_40.csv",
  "s98_L2k20_GoodM2_Ts_sampledata_200_ID18_34.csv",
  "s98_L2k20_GoodM2_Ts_sampledata_200_ID19_25.csv",
  "s98_L2k20_GoodM2_Ts_sampledata_200_ID2_51.csv",
  "s98_L2k20_GoodM2_Ts_sampledata_200_ID3_41.csv",
  "s98_L2k20_GoodM2_Ts_sampledata_200_ID4_12.csv",
  "s98_L2k20_GoodM2_Ts_sampledata_200_ID5_53.csv",
  "s98_L2k20_GoodM2_Ts_sampledata_200_ID6_37.csv",
  "s98_L2k20_GoodM2_Ts_sampledata_200_ID7_38.csv",
  "s98_L2k20_GoodM2_Ts_sampledata_200_ID8_11.csv",
  "s98_L2k20_GoodM2_Ts_sampledata_200_ID9_15.csv"
)

if ("s01" == dataSetID) filesVector <- filesVector_s01
if ("s98" == dataSetID) filesVector <- filesVector_s98

# *****************************************************************************
# 本次要读取的文件名
# *****************************************************************************
filenames <- paste(rootFilePathOfIn, filesVector, sep="")

# *****************************************************************************
# 函数定义
# *****************************************************************************
# -----------------------------------------------------------------------------
# 加载 SampleData 的函数
loadSampleData <- function(filename) {
  # 读取文件
  mysampledata = read.table(filename, header=FALSE, sep=",")  # read table file ,首行无列名(header=FALSE)
  #str(mysampledata)
  
  # -----------------------------------------------------------------------------
  # 取前面 3+1+12 列,即从 201301~201312这12个月的月用点量
  vpm <- mysampledata[, c(1:(4+12))]
  
  # 为vpm设置变量标签
  newcolnames <- c("cons_id", "cons_no", "maxValue", "sep", "201301", "201302", "201303", "201304", "201305", "201306", "201307", "201308", "201309", "201310", "201311", "201312")
  names(vpm) <- c(newcolnames)	# names(vpm.clusterID) <- c(newcolnames,"clusterID")
  rm(newcolnames)
  
  # -----------------------------------------------------------------------------
  # 横表变纵表
  library(reshape2)
  # 'pointsNum'是没有必要的,但为了保留它作为单独的一列
  # vpm.v <- melt(vpm,  id = c("cons_id", "cons_no", "maxValue", "sep"), variable_name = "ym")	# colnames/ym -> value
  # reshape2 中 variable_name 无效
  vpm.v <- melt(vpm,  id = c("cons_id", "cons_no", "maxValue", "sep"))
  
  # 为vpm.v设置变量标签
  newcolnames <- c("cons_id", "cons_no", "maxValue", "sep", "ym", "value")
  names(vpm.v) <- c(newcolnames)
  rm(newcolnames)
  
  # 画图的列
  #vpm.v$ym <- as.factor(vpm.v$colnames)
  
  # -----------------------------------------------------------------------------
  #  行列转换
  #	vpm.t	从vpm进行转换
  #vpm.t <- t(vpm)			# 这种方法,值变为了 character vector
  vpm.t <- as.data.frame(t(vpm))	
  
  # 将 行名 成为新列 ym
  #vpm.t <- data.frame(vpm.t, factor(rownames(vpm)))	# 不需要因子化,所以使用下面的语句即可
  
  #vpm.t <- data.frame(vpm.t, rownames(vpm.t))	# 最后附加的列明是rownames.vpm.t. 所以改为下面两句
  ym <- rownames(vpm.t)
  vpm.t <- data.frame(vpm.t, ym)
  
  # 返回值
  mylist <- list(vpm, vpm.v, vpm.t)
  return (mylist)
}

# *****************************************************************************
# 加载数据到变量中
# *****************************************************************************
library(foreach)

datasets <- foreach(name=filenames) %do% loadSampleData(name)

# 测试
#name <- filenames[1]
#filename <- name

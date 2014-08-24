# -----------------------------------------------------------------------------
# ��ȡspark���������������,����ͼ����ʹ��
# -----------------------------------------------------------------------------
# ���з���: ��R������,ʹ���������
# 		source("J:/home/hadoop/workspace_github/hadoop-ws/r-ws/draw-graphys-ggplot2/read-data-of-kmeans-v1-for-standalone-L2-sample.R")
# �õ��������ݶ���
#	1. ���ݼ� GoodM1
#	2. ���ݼ� GoodM2
#	3. ���ݼ� BadF2ExcludeF3
#	4. ���ݼ� BadF3
#
#  �ر�ע�⣺
#  loadSampleData	:	���� SampleData �ĺ���, ��ֻ��������������
# -----------------------------------------------------------------------------

# *****************************************************************************
# Ҳ���Ե���Ϊÿ���ֶ�ѡ���ļ�
#	mydata = read.table(file.choose(), header=FALSE, sep=",")
# *****************************************************************************

# FilePath
dataSetID <- "s98_L2k20"
rootFilePathOfIn <- "F:/����Ŀ¼2014/ְ��֮�����ھ�/�����ھ�_����.����/DM֮����.����/����.201408/��������-�ڶ������/"

# ��: ���ݼ�+����/˫��
#dimRows <- c("s98_GoodM1", "s98_GoodM2", "s98_BadF2ExcludeF3", "s98_BadF3")
dimCols <- c("sample1", "sample2", "sample3", "sample4")	# , "sample5"
# �ļ���
filesVector_s98 <- c(
	#s98_GoodM1
		# Ladder
    "s98_L2k20_GoodM1_Ladder_sampledata_200_c7.csv",
    "s98_L2k20_GoodM1_Ladder_sampledata_200_c11.csv",
    "s98_L2k20_GoodM1_Ladder_sampledata_200_c12.csv",
    "s98_L2k20_GoodM1_Ladder_sampledata_200_c19.csv",
    # Ts
    "s98_L2k20_GoodM1_Ts_sampledata_c0.csv",
    "s98_L2k20_GoodM1_Ts_sampledata_c13.csv",
    "s98_L2k20_GoodM1_Ts_sampledata_c18.csv",
    "s98_L2k20_GoodM1_Ts_sampledata_c19.csv",
    # NotTsNotLadder
    "s98_L2k20_GoodM1_NotTsNotLadder_sampledata_c0.csv",
    "s98_L2k20_GoodM1_NotTsNotLadder_sampledata_c2.csv",
    "s98_L2k20_GoodM1_NotTsNotLadder_sampledata_c9.csv",
    ".csv",
    
	#s98_GoodM2
		# Ladder
    "s98_L2k20_GoodM2_Ladder_sampledata_c1.csv",
    "s98_L2k20_GoodM2_Ladder_sampledata_c5.csv",
    "s98_L2k20_GoodM2_Ladder_sampledata_c17.csv",
    ".csv",
    # Ts
    "s98_L2k20_GoodM2_Ts_sampledata_c5.csv",
    ".csv",
    ".csv",
    ".csv",
    # NotTsNotLadder
    "s98_L2k20_GoodM2_NotTsNotLadder_sampledata_c7.csv",
    "s98_L2k20_GoodM2_NotTsNotLadder_sampledata_c19.csv",
    ".csv",
    ".csv",
    
	#BadF2ExcludeF3
		# Ladder
    "s98_L2k20_BadF2ExcludeF3_Ladder_sampledata_c0.csv",
    "s98_L2k20_BadF2ExcludeF3_Ladder_sampledata_c11.csv",
    "s98_L2k20_BadF2ExcludeF3_Ladder_sampledata_c12.csv",
    "s98_L2k20_BadF2ExcludeF3_Ladder_sampledata_c16.csv",
    # Ts
    "s98_L2k20_BadF2ExcludeF3_Ts_sampledata_c2.csv",
    "s98_L2k20_BadF2ExcludeF3_Ts_sampledata_c4.csv",
    "s98_L2k20_BadF2ExcludeF3_Ts_sampledata_c11.csv",
    "s98_L2k20_BadF2ExcludeF3_Ts_sampledata_c13.csv",
    # NotTsNotLadder
    "s98_L2k20_BadF2ExcludeF3_NotTsNotLadder_sampledata_c0.csv",
    "s98_L2k20_BadF2ExcludeF3_NotTsNotLadder_sampledata_c3.csv",
    "s98_L2k20_BadF2ExcludeF3_NotTsNotLadder_sampledata_c7.csv",
    "s98_L2k20_BadF2ExcludeF3_NotTsNotLadder_sampledata_c13.csv",
    
	#BadF3
		# Ladder
    "s98_L2k20_BadF3_Ladder_sampledata_c2.csv",
    "s98_L2k20_BadF3_Ladder_sampledata_c3.csv",
    "s98_L2k20_BadF3_Ladder_sampledata_c6.csv",
    "s98_L2k20_BadF3_Ladder_sampledata_c15.csv",
    # Ts
    "s98_L2k20_BadF3_Ts_sampledata_c0.csv",
    "s98_L2k20_BadF3_Ts_sampledata_c8.csv",
    "s98_L2k20_BadF3_Ts_sampledata_c16.csv",
    "s98_L2k20_BadF3_Ts_sampledata_c17.csv",
    # NotTsNotLadder
    "s98_L2k20_BadF3_NotTsNotLadder_sampledata_c0.csv",
    "s98_L2k20_BadF3_NotTsNotLadder_sampledata_c1.csv",
    "s98_L2k20_BadF3_NotTsNotLadder_sampledata_c12.csv",
    "s98_L2k20_BadF3_NotTsNotLadder_sampledata_c17.csv"
	)

filesVector <- filesVector_s98	# filesVector_s98_GoodM1

# ��������
dimRows <- c("s98_GoodM1_Ladder", "s98_GoodM1_Ts", "s98_GoodM1_NotTsNotLadder",
						"s98_GoodM2_Ladder", "s98_GoodM2_Ts", "s98_GoodM2_NotTsNotLadder", 
						"s98_BadF2ExcludeF3_Ladder", "s98_BadF2ExcludeF3_Ts", "s98_BadF2ExcludeF3_NotTsNotLadder", 
						"s98_BadF3_Ladder", "s98_BadF3_Ts", "s98_BadF3_NotTsNotLadder"
						)
filesMatrix <- matrix(filesVector, byrow=TRUE, nrow=length(dimRows), ncol=length(dimCols),dimnames=list(dimRows, dimCols))

#str(filesMatrix)

# *****************************************************************************
# ��������
# *****************************************************************************
# -----------------------------------------------------------------------------
# ���� SampleData �ĺ���
loadSampleData <- function(filename) {
	# ��ȡ�ļ�
	mysampledata = read.table(filename, header=FALSE, sep=",")  # read table file ,����������(header=FALSE)
	#str(mysampledata)

	# -----------------------------------------------------------------------------
	# vpm vpm201301,vpm201302,vpm201303,vpm201304,vpm201305,vpm201306,vpm201307,vpm201308,vpm201309,vpm201310,vpm201311,vpm201312,vpm201401,vpm201402,vpm201403,vpm201404,vpm201405,vpm201406
	# ȡǰ�� 2+12 ��,���� 201301~201312��12���µ����õ���
	vpm <- mysampledata[, c(1:(3+12))]
	
	# Ϊvpm���ñ�����ǩ
	newcolnames <- c("cons_id", "cons_no", "sep", "201301", "201302", "201303", "201304", "201305", "201306", "201307", "201308", "201309", "201310", "201311", "201312")
	names(vpm) <- c(newcolnames)	# names(vpm.clusterID) <- c(newcolnames,"clusterID")
	rm(newcolnames)
	
	# -----------------------------------------------------------------------------
	# ������ݱ�
	library(reshape2)
	# 'pointsNum'��û�б�Ҫ��,��Ϊ�˱�������Ϊ������һ��
	# vpm.v <- melt(vpm,  id = c("cons_id", "cons_no", "sep"), variable_name = "ym")	# colnames/ym -> value
	# reshape2 �� variable_name ��Ч
	vpm.v <- melt(vpm,  id = c("cons_id", "cons_no", "sep"))

	# Ϊvpm.v���ñ�����ǩ
	newcolnames <- c("cons_id", "cons_no", "sep", "ym", "value")
	names(vpm.v) <- c(newcolnames)
	rm(newcolnames)
	
	# ��ͼ����
	#vpm.v$ym <- as.factor(vpm.v$colnames)
	
	# -----------------------------------------------------------------------------
	#  ����ת��
	#	vpm.t	��vpm����ת��
	#vpm.t <- t(vpm)			# ���ַ���,ֵ��Ϊ�� character vector
	vpm.t <- as.data.frame(t(vpm))	
	
	# �� ���� ��Ϊ���� ym
	#vpm.t <- data.frame(vpm.t, factor(rownames(vpm)))	# ����Ҫ���ӻ�,����ʹ���������伴��
	
	#vpm.t <- data.frame(vpm.t, rownames(vpm.t))	# ��󸽼ӵ�������rownames.vpm.t. ���Ը�Ϊ��������
	ym <- rownames(vpm.t)
	vpm.t <- data.frame(vpm.t, ym)
	
	# ����ֵ
	mylist <- list(vpm, vpm.v, vpm.t)
	return (mylist)
}

# *****************************************************************************
# �ļ���
# *****************************************************************************
# ����Ҫ��ȡ���ļ���
# GoodM1
file_GoodM1_clusterSpecial_Ladder_s1 <- stringr::str_c(rootFilePathOfIn, filesMatrix[1,1])
file_GoodM1_clusterSpecial_Ladder_s2 <- stringr::str_c(rootFilePathOfIn, filesMatrix[1,2])
file_GoodM1_clusterSpecial_Ladder_s3 <- stringr::str_c(rootFilePathOfIn, filesMatrix[1,3])
file_GoodM1_clusterSpecial_Ladder_s4 <- stringr::str_c(rootFilePathOfIn, filesMatrix[1,4])

file_GoodM1_clusterSpecial_Ts_s1 <- stringr::str_c(rootFilePathOfIn, filesMatrix[2,1])
file_GoodM1_clusterSpecial_Ts_s2 <- stringr::str_c(rootFilePathOfIn, filesMatrix[2,2])
file_GoodM1_clusterSpecial_Ts_s3 <- stringr::str_c(rootFilePathOfIn, filesMatrix[2,3])
file_GoodM1_clusterSpecial_Ts_s4 <- stringr::str_c(rootFilePathOfIn, filesMatrix[2,4])

file_GoodM1_clusterSpecial_NotTsNotLadder_s1 <- stringr::str_c(rootFilePathOfIn, filesMatrix[3,1])
file_GoodM1_clusterSpecial_NotTsNotLadder_s2 <- stringr::str_c(rootFilePathOfIn, filesMatrix[3,2])
file_GoodM1_clusterSpecial_NotTsNotLadder_s3 <- stringr::str_c(rootFilePathOfIn, filesMatrix[3,3])

# GoodM2
file_GoodM2_clusterSpecial_Ladder_s1 <- stringr::str_c(rootFilePathOfIn, filesMatrix[4,1])
file_GoodM2_clusterSpecial_Ladder_s2 <- stringr::str_c(rootFilePathOfIn, filesMatrix[4,2])
file_GoodM2_clusterSpecial_Ladder_s3 <- stringr::str_c(rootFilePathOfIn, filesMatrix[4,3])

file_GoodM2_clusterSpecial_Ts_s1 <- stringr::str_c(rootFilePathOfIn, filesMatrix[5,1])

file_GoodM2_clusterSpecial_NotTsNotLadder_c7 <- stringr::str_c(rootFilePathOfIn, filesMatrix[6,1])
file_GoodM2_clusterSpecial_NotTsNotLadder_c19 <- stringr::str_c(rootFilePathOfIn, filesMatrix[6,2])

# BadF2ExcludeF3
file_BadF2ExcludeF3_clusterSpecial_Ladder_c0 <- stringr::str_c(rootFilePathOfIn, filesMatrix[7,1])
file_BadF2ExcludeF3_clusterSpecial_Ladder_c11 <- stringr::str_c(rootFilePathOfIn, filesMatrix[7,2])
file_BadF2ExcludeF3_clusterSpecial_Ladder_c12 <- stringr::str_c(rootFilePathOfIn, filesMatrix[7,3])
file_BadF2ExcludeF3_clusterSpecial_Ladder_c16 <- stringr::str_c(rootFilePathOfIn, filesMatrix[7,4])

file_BadF2ExcludeF3_clusterSpecial_Ts_c2 <- stringr::str_c(rootFilePathOfIn, filesMatrix[8,1])
file_BadF2ExcludeF3_clusterSpecial_Ts_c4 <- stringr::str_c(rootFilePathOfIn, filesMatrix[8,2])
file_BadF2ExcludeF3_clusterSpecial_Ts_c11 <- stringr::str_c(rootFilePathOfIn, filesMatrix[8,3])
file_BadF2ExcludeF3_clusterSpecial_Ts_c13 <- stringr::str_c(rootFilePathOfIn, filesMatrix[8,4])

file_BadF2ExcludeF3_clusterSpecial_NotTsNotLadder_c0 <- stringr::str_c(rootFilePathOfIn, filesMatrix[9,1])
file_BadF2ExcludeF3_clusterSpecial_NotTsNotLadder_c3 <- stringr::str_c(rootFilePathOfIn, filesMatrix[9,2])
file_BadF2ExcludeF3_clusterSpecial_NotTsNotLadder_c7 <- stringr::str_c(rootFilePathOfIn, filesMatrix[9,3])
file_BadF2ExcludeF3_clusterSpecial_NotTsNotLadder_c13 <- stringr::str_c(rootFilePathOfIn, filesMatrix[9,4])

# BadF3
file_BadF3_clusterSpecial_Ladder_c2 <- stringr::str_c(rootFilePathOfIn, filesMatrix[10,1])
file_BadF3_clusterSpecial_Ladder_c3 <- stringr::str_c(rootFilePathOfIn, filesMatrix[10,2])
file_BadF3_clusterSpecial_Ladder_c6 <- stringr::str_c(rootFilePathOfIn, filesMatrix[10,3])
file_BadF3_clusterSpecial_Ladder_c15 <- stringr::str_c(rootFilePathOfIn, filesMatrix[10,4])

file_BadF3_clusterSpecial_Ts_c0 <- stringr::str_c(rootFilePathOfIn, filesMatrix[11,1])
file_BadF3_clusterSpecial_Ts_c8 <- stringr::str_c(rootFilePathOfIn, filesMatrix[11,2])
file_BadF3_clusterSpecial_Ts_c16 <- stringr::str_c(rootFilePathOfIn, filesMatrix[11,3])
file_BadF3_clusterSpecial_Ts_c17 <- stringr::str_c(rootFilePathOfIn, filesMatrix[11,4])

file_BadF3_clusterSpecial_NotTsNotLadder_c0 <- stringr::str_c(rootFilePathOfIn, filesMatrix[12,1])
file_BadF3_clusterSpecial_NotTsNotLadder_c1 <- stringr::str_c(rootFilePathOfIn, filesMatrix[12,2])
file_BadF3_clusterSpecial_NotTsNotLadder_c12 <- stringr::str_c(rootFilePathOfIn, filesMatrix[12,3])
file_BadF3_clusterSpecial_NotTsNotLadder_c17 <- stringr::str_c(rootFilePathOfIn, filesMatrix[12,4])

# *****************************************************************************
# �������ݵ�������
# *****************************************************************************
# GoodM1
fileData_GoodM1_clusterSpecial_Ladder_s1 <- loadSampleData(file_GoodM1_clusterSpecial_Ladder_s1)
fileData_GoodM1_clusterSpecial_Ladder_s2 <- loadSampleData(file_GoodM1_clusterSpecial_Ladder_s2)
fileData_GoodM1_clusterSpecial_Ladder_s3 <- loadSampleData(file_GoodM1_clusterSpecial_Ladder_s3)
fileData_GoodM1_clusterSpecial_Ladder_s4 <- loadSampleData(file_GoodM1_clusterSpecial_Ladder_s4)

fileData_GoodM1_clusterSpecial_Ts_s1 <- loadSampleData(file_GoodM1_clusterSpecial_Ts_s1)
fileData_GoodM1_clusterSpecial_Ts_s2 <- loadSampleData(file_GoodM1_clusterSpecial_Ts_s2)
fileData_GoodM1_clusterSpecial_Ts_s3 <- loadSampleData(file_GoodM1_clusterSpecial_Ts_s3)
fileData_GoodM1_clusterSpecial_Ts_s4 <- loadSampleData(file_GoodM1_clusterSpecial_Ts_s4)

fileData_GoodM1_clusterSpecial_NotTsNotLadder_s1 <- loadSampleData(file_GoodM1_clusterSpecial_NotTsNotLadder_s1)
fileData_GoodM1_clusterSpecial_NotTsNotLadder_s2 <- loadSampleData(file_GoodM1_clusterSpecial_NotTsNotLadder_s2)
fileData_GoodM1_clusterSpecial_NotTsNotLadder_s3 <- loadSampleData(file_GoodM1_clusterSpecial_NotTsNotLadder_s3)

# GoodM2
fileData_GoodM2_clusterSpecial_Ladder_s1 <- loadSampleData(file_GoodM2_clusterSpecial_Ladder_s1)
fileData_GoodM2_clusterSpecial_Ladder_s2 <- loadSampleData(file_GoodM2_clusterSpecial_Ladder_s2)
fileData_GoodM2_clusterSpecial_Ladder_s3 <- loadSampleData(file_GoodM2_clusterSpecial_Ladder_s3)

fileData_GoodM2_clusterSpecial_Ts_s1 <- loadSampleData(file_GoodM2_clusterSpecial_Ts_s1)

fileData_GoodM2_clusterSpecial_NotTsNotLadder_c7 <- loadSampleData(file_GoodM2_clusterSpecial_NotTsNotLadder_c7)
fileData_GoodM2_clusterSpecial_NotTsNotLadder_c19 <- loadSampleData(file_GoodM2_clusterSpecial_NotTsNotLadder_c19)

# BadF2ExcludeF3
fileData_BadF2ExcludeF3_clusterSpecial_Ladder_c0 <- loadSampleData(file_BadF2ExcludeF3_clusterSpecial_Ladder_c0)
fileData_BadF2ExcludeF3_clusterSpecial_Ladder_c11 <- loadSampleData(file_BadF2ExcludeF3_clusterSpecial_Ladder_c11)
fileData_BadF2ExcludeF3_clusterSpecial_Ladder_c12 <- loadSampleData(file_BadF2ExcludeF3_clusterSpecial_Ladder_c12)
fileData_BadF2ExcludeF3_clusterSpecial_Ladder_c16 <- loadSampleData(file_BadF2ExcludeF3_clusterSpecial_Ladder_c16)

fileData_BadF2ExcludeF3_clusterSpecial_Ts_c2 <- loadSampleData(file_BadF2ExcludeF3_clusterSpecial_Ts_c2)
fileData_BadF2ExcludeF3_clusterSpecial_Ts_c4 <- loadSampleData(file_BadF2ExcludeF3_clusterSpecial_Ts_c4)
fileData_BadF2ExcludeF3_clusterSpecial_Ts_c11 <- loadSampleData(file_BadF2ExcludeF3_clusterSpecial_Ts_c11)
fileData_BadF2ExcludeF3_clusterSpecial_Ts_c13 <- loadSampleData(file_BadF2ExcludeF3_clusterSpecial_Ts_c13)

fileData_BadF2ExcludeF3_clusterSpecial_NotTsNotLadder_c0 <- loadSampleData(file_BadF2ExcludeF3_clusterSpecial_NotTsNotLadder_c0)
fileData_BadF2ExcludeF3_clusterSpecial_NotTsNotLadder_c3 <- loadSampleData(file_BadF2ExcludeF3_clusterSpecial_NotTsNotLadder_c3)
fileData_BadF2ExcludeF3_clusterSpecial_NotTsNotLadder_c7 <- loadSampleData(file_BadF2ExcludeF3_clusterSpecial_NotTsNotLadder_c7)
fileData_BadF2ExcludeF3_clusterSpecial_NotTsNotLadder_c13 <- loadSampleData(file_BadF2ExcludeF3_clusterSpecial_NotTsNotLadder_c13)

# BadF3
fileData_BadF3_clusterSpecial_Ladder_c2 <- loadSampleData(file_BadF3_clusterSpecial_Ladder_c2)
fileData_BadF3_clusterSpecial_Ladder_c3 <- loadSampleData(file_BadF3_clusterSpecial_Ladder_c3)
fileData_BadF3_clusterSpecial_Ladder_c6 <- loadSampleData(file_BadF3_clusterSpecial_Ladder_c6)
fileData_BadF3_clusterSpecial_Ladder_c15 <- loadSampleData(file_BadF3_clusterSpecial_Ladder_c15)

fileData_BadF3_clusterSpecial_Ts_c0 <- loadSampleData(file_BadF3_clusterSpecial_Ts_c0)
fileData_BadF3_clusterSpecial_Ts_c8 <- loadSampleData(file_BadF3_clusterSpecial_Ts_c8)
fileData_BadF3_clusterSpecial_Ts_c16 <- loadSampleData(file_BadF3_clusterSpecial_Ts_c16)
fileData_BadF3_clusterSpecial_Ts_c17 <- loadSampleData(file_BadF3_clusterSpecial_Ts_c17)

fileData_BadF3_clusterSpecial_NotTsNotLadder_c0 <- loadSampleData(file_BadF3_clusterSpecial_NotTsNotLadder_c0)
fileData_BadF3_clusterSpecial_NotTsNotLadder_c1 <- loadSampleData(file_BadF3_clusterSpecial_NotTsNotLadder_c1)
fileData_BadF3_clusterSpecial_NotTsNotLadder_c12 <- loadSampleData(file_BadF3_clusterSpecial_NotTsNotLadder_c12)
fileData_BadF3_clusterSpecial_NotTsNotLadder_c17 <- loadSampleData(file_BadF3_clusterSpecial_NotTsNotLadder_c17)

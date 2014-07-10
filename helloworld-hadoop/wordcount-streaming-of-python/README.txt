# 添加执行权限
chmod u+x *.py

# 执行: local
#cat data | map | sort | reduce
cat words-data | mapper.py | sort | reducer.py

echo "foo foo quux labs foo bar quux" | mapper.py | sort -k1,1 | reducer.py

# 执行: hadoop
# input,output是hdfs路径;而-file是本地路径
hadoop jar ${HADOOP_HOME}/share/hadoop/tools/lib/hadoop-streaming-2.2.0.jar \
  -input input/words-data/* \
  -output output/python/wordcount  \
  -file mapper.py  \
  -mapper mapper.py \
  -file reducer.py \
  -reducer  reducer.py

# 查看结果
hdfs dfs -cat output/python/wordcount/* | less

# 其他: 执行时指定参数,如reducer数量为10
-D mapred.reduce.tasks=16

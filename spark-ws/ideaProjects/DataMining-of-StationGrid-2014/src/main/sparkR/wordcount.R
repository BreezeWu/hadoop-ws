# export MASTER=local; sparkR

file <- "hdfs:///user/hadoop/input/README.md"   # failed!
    # java.io.IOException: Incomplete HDFS URI, no host: hdfs:/user/hadoop/input/README.md
file <- "file:///home/hadoop/workspace_github/SparkR-pkg/README.md" # ok!
file <- "/home/hadoop/workspace_github/SparkR-pkg/README.md"    # ok!

lines <- textFile(sc,file) # textFile(sc,args[[2]])
words <- flatMap(lines,
    function(line) {
        strsplit(line," ")[[1]]
     })
wordCount <- lapply(words,
    function(word) {
        list(word, 1L)
    })
counts <- reduceByKey(wordCount, "+",2L)
output <- collect(counts)
output

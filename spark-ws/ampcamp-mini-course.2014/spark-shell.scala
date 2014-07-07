sc

//val pagecounts = sc.textFile("/opt/spark/spark-1.0.0-bin-hadoop2/README.md")
val pagecounts = sc.textFile("wiki/pagecounts")
pagecounts.take(10)

// ----------------------------------------------
//  traversing the array to print each record on its own line.
pagecounts.take(10).foreach(println)
// Let’s see how many records in total are in this data set
pagecounts.count

// ----------------------------------------------
//Let’s derive an RDD containing only English pages from pagecounts. 
//	This can be done by applying a filter function to pagecounts. 
//	For each record, we can split it by the field delimiter (i.e. a space) 
//	and get the second field-– and then compare it with the string “en”.
val enPages = pagecounts.filter(_.split(" ")(1) == "en").cache
// how many recorts
enPages.count

pagecounts.count

// ----------------------------------------------
// Let’s try something fancier. 
//	Generate a histogram of total page views on Wikipedia English pages for the date range represented in our dataset (May 5 to May 7, 2009).
val enTuples = enPages.map(line => line.split(" "))
val enKeyValuePairs = enTuples.map(line => (line(0).substring(0, 8), line(3).toInt))

// we shuffle the data and group all values of the same key together.
// Finally we sum up the values for each key
enKeyValuePairs.reduceByKey(_+_, 1).collect

// 或者
val groupPages = enKeyValuePairs.reduceByKey(_+_, 1)
// The collect method at the end converts the result from an RDD to an array
val groupPages_counts = groupPages.collect

// ----------------------
// We can combine the previous three commands into one
enPages.map(line => line.split(" ")).map(line => (line(0).substring(0, 8), line(3).toInt)).reduceByKey(_+_, 1).collect

// ----------------------------------------------




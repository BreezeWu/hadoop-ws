import scala.io.Source

val lines = Source.fromFile("/opt/spark/spark-1.0.0-bin-hadoop2/README.md").getLines.toArray

// mutable
val counts = new collection.mutable.HashMap[String, Int].withDefaultValue(0)
lines.flatMap(line => line.split(" ")).foreach(word => counts(word) += 1)
counts

// immutable :  a purely functional solution
val emptyCounts = Map[String,Int]().withDefaultValue(0)
val words = lines.flatMap(line => line.split(" ")) // val words = lines.map(x=>x.split(" ")).flatten
val counts = words.foldLeft(emptyCounts)(
	{(currentCounts: Map[String,Int], word: String) => currentCounts.updated(word, currentCounts(word) + 1)}
)

counts




package scala
abstract class Int extends AnyVal {
	def toLong: Long
	def toFloat: Float
	def toDouble: Double

	def + (that: Double): Double
	def + (that: Float): Float
	def + (that: Long): Long
	def + (that: Int): Int // analogous for -, *, /, %

	def << (cnt: Int): Int // analogous for >>, >>>

	def & (that: Long): Long
	def & (that: Int): Int // analogous for |, ^

	def == (that: Double): Boolean
	def == (that: Float): Boolean
	def == (that: Long): Boolean // analogous for !=, <, >, <=, >=
}



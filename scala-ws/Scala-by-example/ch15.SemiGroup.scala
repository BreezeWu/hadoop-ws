// ----------------------------------------------------------------------------
abstract class SemiGroup[A] {
	def add(x: A, y: A): A
}

abstract class Monoid[A] extends SemiGroup[A] {
	def unit: A
}

// Here are two implementations of monoids
object stringMonoid extends Monoid[String] {
	def add(x: String, y: String): String = x.concat(y)
	def unit: String = ""
}

object intMonoid extends Monoid[Int] {
	def add(x: Int, y: Int): Int = x + y
	def unit: Int = 0
}
// ----------------------------------------------------------------------------
// A sum method, which works over arbitrary monoids, 
// can be written in plain Scala as follows.
def sum[A](xs: List[A])(m: Monoid[A]): A = {
	if (xs.isEmpty) m.unit
	else m.add(xs.head, sum(xs.tail)(m))
}

// This sum method can be called as follows:
sum(List("a", "bc", "def"))(stringMonoid)
sum(List(1, 2, 3))(intMonoid)
// ----------------------------------------------------------------------------
// 
def sum[A](xs: List[A])(implicit m: Monoid[A]): A = {
	if (xs.isEmpty) m.unit
	else m.add(xs.head, sum(xs.tail))
}
// ----------------------------------------------------------------------------
// ----------------------------------------------------------------------------
// ----------------------------------------------------------------------------
// ----------------------------------------------------------------------------
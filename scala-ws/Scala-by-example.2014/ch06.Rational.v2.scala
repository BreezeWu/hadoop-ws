// ----------------------------------------------------------------------------
class Rational(n: Int, d: Int) {
	private def gcd(x: Int, y: Int): Int = {
		if (x == 0) y
		else if (x < 0) gcd(-x, y)
		else if (y < 0) -gcd(x, -y)
		else gcd(y % x, x)
	}
	private val g = gcd(n, d)

	val numer: Int = n/g
	val denom: Int = d/g
	def +(that: Rational) =
		new Rational(numer * that.denom + that.numer * denom,denom * that.denom)

	def -(that: Rational) =
		new Rational(numer * that.denom - that.numer * denom,denom * that.denom)

	def *(that: Rational) = new Rational(numer * that.numer, denom * that.denom)

	def /(that: Rational) = new Rational(numer * that.denom, denom * that.numer)

  override def toString = "" + numer + "/" + denom

  def square = new Rational(numer*numer, denom*denom)
}

// 应用class
var i = 1
var x = new Rational(0, 1)
while (i <= 10) {
	x += new Rational(1, i)
	i += 1
}
println("" + x.numer + "/" + x.denom)

// 访问成员numer与无参方法square是无区别的
val r1 = new Rational(49,700)
r1.numer
r1.denom

r1.square
r1.square()  // 错误! 不能带括号!

val r1m = r1*new Rational(2,3)

// ----------------------------------------------------------------------------
// 抽象类
abstract class IntSet {
	def incl(x: Int): IntSet
	def contains(x: Int): Boolean
	//def excl(x: Int): IntSet
	//def isEmpty: Boolean
}

// 实现抽象类
class EmptySet extends IntSet {
	def contains(x: Int): Boolean = false
	def incl(x: Int): IntSet = new NonEmptySet(x, new EmptySet, new EmptySet)
	def isEmpty:Boolean = true
	def excl(x: Int): IntSet = new EmptySet
}

// 交互式下, :paste
class NonEmptySet(elem: Int, left: IntSet, right: IntSet) extends IntSet {
	def contains(x: Int): Boolean = {
		if (x < elem) left contains x
		else if (x > elem) right contains x
		else true
	}

	def incl(x: Int): IntSet = {
		if (x < elem) new NonEmptySet(elem, left incl x, right)
		else if (x > elem) new NonEmptySet(elem, left, right incl x)
		else this
	}

	/*
	def isEmpty:Boolean = {	
		if elem
	}
	*/
}

// 应用
(new EmptySet).contains(7)

new NonEmptySet(7, new EmptySet, new EmptySet).contains(1)
new NonEmptySet(7, new EmptySet, new EmptySet).contains(7)
// ----------------------------------------------------------------------------
// traits : 能含有实现的interface
trait IntSet {
	def incl(x: Int): IntSet
	def contains(x: Int): Boolean
}
// ----------------------------------------------------------------------------
// Boolean对象类,参见 ch06.Boolean.scala

// ----------------------------------------------------------------------------
// ----------------------------------------------------------------------------
// ----------------------------------------------------------------------------
// ----------------------------------------------------------------------------
// ----------------------------------------------------------------------------
// ----------------------------------------------------------------------------
// ----------------------------------------------------------------------------
// ----------------------------------------------------------------------------

// ----------------------------------------------------------------------------
// 抽象类
abstract class Set[A] {
	def incl(x: A): Set[A]
	def contains(x: A): Boolean
}

// ---------------------------
// 有 parameter declaration : 可以正常编译
class EmptySet[A <: Ordered[A]] extends Set[A] {
	def contains(x: A): Boolean = false
	def incl(x: A): Set[A] = new NonEmptySet(x, new EmptySet[A], new EmptySet[A])
}

class NonEmptySet[A <: Ordered[A]] (elem: A, left: Set[A], right: Set[A]) extends Set[A] {
	def contains(x: A): Boolean =
		if (x < elem) left contains x
		else if (x > elem) right contains x
		else true

	def incl(x: A): Set[A] =
		if (x < elem) new NonEmptySet(elem, left incl x, right)
		else if (x > elem) new NonEmptySet(elem, left, right incl x)
		else this
}

// 应用
case class Num(value: Double) extends Ordered[Num] {
	def compare(that: Num): Int =
	if (this.value < that.value) -1
	else if (this.value > that.value) 1
	else 0
}

val s = new EmptySet[Num].incl(Num(1.0)).incl(Num(2.0))
s.contains(Num(1.5))

//但下面语句是错误的
val s = new EmptySet[java.io.File]
//^ java.io.File does not conformto type parameter bound Ordered[java.io.File].
// ---------------------------
// 无 parameter declaration
/*
 下面语句无法通过编译,会提示""
<console>:34: error: value < is not a member of type parameter A
       if (x < elem) left contains x
             ^
<console>:36: error: value > is not a member of type parameter A
       else if (x > elem) right contains x
                  ^
<console>:43: error: value < is not a member of type parameter A
       if (x < elem) new NonEmptySet(elem, left incl x, right)
             ^
<console>:45: error: value > is not a member of type parameter A
       else if (x > elem) new NonEmptySet(elem, left, right incl x)
                  ^
*/ 
class EmptySet[A] extends Set[A] {
	def contains(x: A): Boolean = false
	def incl(x: A): Set[A] = new NonEmptySet(x, new EmptySet[A], new EmptySet[A])
}

class NonEmptySet[A] (elem: A, left: Set[A], right: Set[A]) extends Set[A] {
	def contains(x: A): Boolean =
		if (x < elem) left contains x
		else if (x > elem) right contains x
		else true

	def incl(x: A): Set[A] =
		if (x < elem) new NonEmptySet(elem, left incl x, right)
		else if (x > elem) new NonEmptySet(elem, left, right incl x)
		else this
}
// ----------------------------------------------------------------------------


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

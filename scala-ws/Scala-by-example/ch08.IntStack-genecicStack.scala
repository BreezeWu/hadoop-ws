// ----------------------------------------------------------------------------
// IntStack
abstract class IntStack {
	def push(x: Int): IntStack = new IntNonEmptyStack(x, this)
	def isEmpty: Boolean
	def top: Int
	def pop: IntStack
}

class IntEmptyStack extends IntStack {
	def isEmpty = true
	def top = error("EmptyStack.top")
	def pop = error("EmptyStack.pop")
}

class IntNonEmptyStack(elem: Int, rest: IntStack) extends IntStack {
	def isEmpty = false
	def top = elem
	def pop = rest
}

// ----------------------------------------------------------------------------
// Generic Stack
abstract class Stack[A] {
	def push(x: A): Stack[A] = new NonEmptyStack[A](x, this)
	def isEmpty: Boolean
	def top: A
	def pop: Stack[A]
}

class EmptyStack[A] extends Stack[A] {
	def isEmpty = true
	def top = error("EmptyStack.top")
	def pop = error("EmptyStack.pop")
}

class NonEmptyStack[A](elem: A, rest: Stack[A]) extends Stack[A] {
	def isEmpty = false
	def top = elem
	def pop = rest
}

// 应用
val x = new EmptyStack[Int]
val y = x.push(1).push(2)
println(y.pop.top)

val z = x.push(2).push(3).push(4).push(10)
println(z.pop.top)
// ----------------------------------------------------------------------------
// 
def isPrefix[A](p: Stack[A], s: Stack[A]): Boolean = {
	p.isEmpty || p.top == s.top && isPrefix[A](p.pop, s.pop)
}

// 应用
val s1 = new EmptyStack[String].push("abc")
val s2 = new EmptyStack[String].push("abx").push(s1.top)
println(isPrefix[String](s1, s2))

// ----------------------------------------------------------------------------
// ----------------------------------------------------------------------------
// ----------------------------------------------------------------------------

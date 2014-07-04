abstract class Stack[+A] {
	def push[B >: A](x: B): Stack[B] = new NonEmptyStack(x, this)
	def isEmpty: Boolean
	def top: A
	def pop: Stack[A]
}
object EmptyStack extends Stack[Nothing] {
	def isEmpty = true
	def top = error("EmptyStack.top")
	def pop = error("EmptyStack.pop")
}
class NonEmptyStack[+A](elem: A, rest: Stack[A]) extends Stack[A] {
	def isEmpty = false
	def top = elem
	def pop = rest
}



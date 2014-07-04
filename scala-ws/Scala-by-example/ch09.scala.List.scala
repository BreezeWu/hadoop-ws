package scala
abstract class List[+A] {
	def isEmpty: Boolean = this match {
		case Nil => true
		case x :: xs => false
	}
	def head: A = this match {
		case Nil => error("Nil.head")
		case x :: xs => x
	}
	def tail: List[A] = this match {
		case Nil => error("Nil.tail")
		case x :: xs => xs
	}

	def length: Int = this match {
		case Nil => 0
		case x :: xs => 1 + xs.length
	}

	// ------------
	def last: A = this match {
		case Nil => error("Nil.last")
		case x :: Nil => x
		case x :: xs => xs.last
	}

	// ------------
	def take(n: Int): List[A] =
		if (n == 0 || isEmpty) Nil else head :: tail.take(n-1)

	def drop(n: Int): List[A] =
		if (n == 0 || isEmpty) this else tail.drop(n-1)

	def split(n: Int): (List[A], List[A]) = (take(n), drop(n))

	//
	def apply(n: Int): A = drop(n).head

	def zip[B](that: List[B]): List[(a,b)] =
		if (this.isEmpty || that.isEmpty) Nil
		else (this.head, that.head) :: (this.tail zip that.tail)

	def ::[B >: A](x: B): List[B] = new scala.::(x, this)

	def :::[B >: A](prefix: List[B]): List[B] = prefix match {
		case Nil => this
		case p :: ps => this.:::(ps).::(p)
	}

	def reverse[A](xs: List[A]): List[A] = xs match {
		case Nil => Nil
		case x :: xs => reverse(xs) ::: List(x)
	}
}



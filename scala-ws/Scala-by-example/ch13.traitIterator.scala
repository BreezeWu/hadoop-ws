trait Iterator[+A] {
	// 需要子类定义其实现
	def hasNext: Boolean
	def next: A
	
	// --------------------------------------------------------------------------
	// Iterator Methods
	
	def append[B >: A](that: Iterator[B]): Iterator[B] = new Iterator[B] {
		def hasNext = Iterator.this.hasNext || that.hasNext
		def next = if (Iterator.this.hasNext) Iterator.this.next else that.next
	}
	
	// --------------------------------------------------------------------------
	def map[B](f: A => B): Iterator[B] = new Iterator[B] {
		def hasNext = Iterator.this.hasNext
		def next = f(Iterator.this.next)
	}

	def flatMap[B](f: A => Iterator[B]): Iterator[B] = new Iterator[B] {
		private var cur: Iterator[B] = Iterator.empty
		
		def hasNext: Boolean =
			if (cur.hasNext) true
			else if (Iterator.this.hasNext) { cur = f(Iterator.this.next); hasNext }
			else false
			
		def next: B =
			if (cur.hasNext) cur.next
			else if (Iterator.this.hasNext) { cur = f(Iterator.this.next); next }
			else error("next on empty iterator")
	}

	def foreach(f: A => Unit): Unit =
		while (hasNext) { f(next) }

	// filter 依赖于 BufferedIterator
	trait BufferedIterator[+A] extends Iterator[A] {
		def head: A
	}
	
	def filter(p: A => Boolean) = new BufferedIterator[A] {
		private val source =
			Iterator.this.buffered
		
		private def skip =
			{ while (source.hasNext && !p(source.head)) { source.next } }
			
		def hasNext: Boolean =
			{ skip; source.hasNext }
		
		def next: A =
			{ skip; source.next }
			
		def head: A =
			{ skip; source.head }
	}

/*
	Since map, flatMap, filter, and foreach exist for iterators, 
	it follows that forcomprehensions and for-loops can also be used on iterators.
	
	for (i <- Iterator.range(1, 100))
		println(i*i)
*/
	// --------------------------------------------------------------------------
	def zip[B](that: Iterator[B]) = new Iterator[(A, B)] {
		def hasNext = Iterator.this.hasNext && that.hasNext
		def next = (Iterator.this.next, that.next)
	}
}

// --------------------------------------------------------------------------
// 具体Iterator
object Iterator {
	object empty extends Iterator[Nothing] {
		def hasNext = false
		def next = error("next on empty iterator")
	}
	
	// A more interesting iterator enumerates all elements of an array. 
	// This iterator is constructed by the fromArray method
	def fromArray[A](xs: Array[A]) = new Iterator[A] {
		private var i = 0
		
		def hasNext: Boolean = i < xs.length
		
		def next: A =
			if (i < xs.length) { val x = xs(i); i += 1; x }
			else error("next on empty iterator")
	}

	// The Iterator.range function returns an iterator 
	// which traverses a given interval of integer values.
	def range(start: Int, end: Int) = new Iterator[Int] {
		private var current = start
		
		def hasNext = current < end
		
		def next = {
			val r = current
			if (current < end) current += 1
			else error("end of iterator")
			
			r
		}
	}

	//  to define iterators that go on forever. 
	// For instance, the following iterator returns successive integers from some start value 1

	def from(start: Int) = new Iterator[Int] {
		private var last = start - 1
		
		def hasNext = true
		def next = { last += 1; last }
	}
}

// --------------------------------------------------------------------------
// Using Iterators
val xs = Array.range(1,20)

//First, to print all elements of an array xs: Array[Int], one can write:
Iterator.fromArray(xs) foreach (x => println(x))

//Or, using a for-comprehension:
for (x <- Iterator.fromArray(xs))
	println(x)

// -----------------------
// the problem of finding the indices of all the elements in an array of doubles greater than some limit. 
// The indices should be returned as an iterator. 
val limit = 7

import Iterator._
fromArray(xs)
.zip(from(0))
.filter(case (x, i) => x > limit)
.map(case (x, i) => i)

fromArray(xs).zip(from(0)).filter(case (x, i) => x > limit).map(case (x, i) => i)


//Or, using a for-comprehension:
import Iterator._
for ((x, i) <- fromArray(xs) zip from(0); x > limit) yield i
// ----------------------------------------------------------------------------
// the imperative way to compute the sum of all prime numbers in an interval

def isPrime_1(n: Int) = {
	List.range(2, n) forall (x => n % x != 0)
	/*
	var i = 2
	for (i<= n) {
		var j = i
		for (j <= n)
		{
		}
	}
	*/
	/*
def whileLoop(condition: => Boolean)(command: => Unit) {
	if (condition) {
	command; whileLoop(condition)(command)
	} else ()
}
	*/
}
	

def sumPrimes_1(start: Int, end: Int): Int = {
	var i = start
	var acc = 0
	
	while (i < end) {
		if (isPrime_1(i)) acc += i
		i += 1
	}
	
	acc
}

// ----------------------------------------------------------------------------
// A more functional way is to represent the list of values of variable i directly as
// range(start, end). Then the function can be rewritten as follows.

def isPrime_2(n: Int) =
	List.range(2, n) forall (x => n % x != 0)
	
// scala.List
def range(from: Int, end: Int): List[Int] =
	if (from >= end) Nil else from :: range(from + 1, end)
def sum(xs: List[Int]) = (xs foldLeft 0) {(x, y) => x + y}
def product(xs: List[Int]) = (xs foldLeft 1) {(x, y) => x*y}

import scala.List
def sumPrimes_2(start: Int, end: Int) =
	sum(List.range(start, end) filter isPrime_2)


// ----------------------------------------------------------------------------
// 改进funtional way
// However, we can obtain efficient execution for examples like these by a trick:
// Avoid computing the tail of a sequence unless that tail is actually necessary for the computation.

import scala.Stream
// We define a new class for such sequences, which is called Stream.
def sum_stream(xs: Stream[Int]) = (xs foldLeft 0) {(x, y) => x + y}
def isPrime_stream(n: Int) =
	Stream.range(2, n) forall (x => n % x != 0)

import scala.Stream
def sumPrimes_3(start: Int, end: Int) =
	sum_stream(Stream.range(start, end) filter isPrime_stream)
	
// 应用 imperative
sumPrimes_1(1,10)
sumPrimes_1(1000,10000)
sumPrimes_1(1000,100000) 

// 应用 functional, List
sumPrimes_2(1,10)
sumPrimes_2(1000,10000)  // Here, the list of all numbers between 1000 and 10000 is constructed. 
												 // But most of that list is never inspected!
//List.range(1000, 10000) filter isPrime at 1
sumPrimes_2(1000,100000) 

// 应用 functional, Stream
sumPrimes_3(1,10)
sumPrimes_3(1000,10000)
sumPrimes_3(1000,100000) 
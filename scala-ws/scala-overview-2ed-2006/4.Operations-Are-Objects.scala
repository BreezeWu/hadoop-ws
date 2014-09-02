// ----------------------------------------------------------------------------
// 4 Operations Are Objects

// Scala is a functional language in the sense that every function is a value. It provides a lightweight syntax for the definition of anonymous and curried functions, and it also supports nested functions.

// ----------------------------------------------------------------------------
// 4.1 Methods are Functional Values

//  function exists
def exists[T](xs: Array[T], p: T => Boolean) = {
    var i: Int = 0
    while (i < xs.length && !p(xs(i))) i = i + 1
        i < xs.length
}
// The type of p is the function type T => boolean, which has as values all functions with domain T and range boolean. 
// Function parameters can be applied just as normal functions; an example is the application of p in the condition of the while-loop. Functions which take functions as arguments, or return them as results, are called *higher-order functions*.

// 测试代码
val arr = Array(1,2,3,4,5,6,7,8,9)
val result1 = exists(arr, (x:Int) => (x) == 1)
val result2 = exists(arr, (x:Int) => (x) == 11)

//  function forall --- 有名字(not_p)
def forall[T](xs: Array[T], p: T => Boolean) = {
    def not_p(x: T) = !p(x)
    !exists(xs, not_p)
}

// 测试代码
val result3=forall(arr, (x:Int) => (x) == 1)
val result5=forall(arr, (x:Int) => (x) == 11)

val arr2 = Array(11,11,11,11)
val result6=forall(arr2, (x:Int) => (x) == 11)
val result6=forall(arr2, (x:Int) => (x) != 11)

val arr1 = Array(1,2,3,4,5,6,7,8,9)
val result6=forall(arr1, (x:Int) => (x) != 11)

//  function forall --- 无名字
// It is also possible to define a function without giving it a me; this is used in the following shorter version of forall:
def forall[T](xs: Array[T], p: T => Boolean) =
    !exists(xs, (x: T) => !p(x))
// Here, (x: T) => !p(x) defines an anonymous function which maps its parameter x of type T to !p(x).

//
def hasZeroRow(matrix: Array[Array[Int]]) =
    exists(matrix, (row: Array[Int]) => forall(row, 0 == ))
    
def hasZeroRow(matrix: Array[Array[Int]]) =
    exists(matrix, (row: Array[Int]) => forall(row, (item:Int) => 0 == item))
 // The expression forall(row, 0 ==) tests whether row consists only of zeros. Here, the == method of the number 0 is passed as argument corresponding to the predicate parameter p. This illustrates that methods can themselves be used as values in Scala; it is similar to the "delegates" concept in C#.
 
 // 测试代码
val arr1 = Array(1,2,3,4,5,6,7,8,9)
val arr2 = Array(11,12,13,14)
val arr3 = Array(0,0,0,0,0)
val arr4 = Array(0,0,0,1,0)
val matrix1 = Array(arr1, arr2, arr3)
val matrix2 = Array(arr1, arr2, arr4)

val result1 = hasZeroRow(matrix1)
val result1 = hasZeroRow(matrix2)

// ----------------------------------------------------------------------------
// 4.2 Functions are Objects

// If methods are values, and values are objects, it follows that methods themselves are objects. 
// In fact, the syntax of function types and values is just syntactic sugar for certain class types and class instances. 
// The function type S => T is equivalent to the parameterized class type scala.Function1[S, T], which is defined as follows in the standard Scala library:
package scala
abstract class Function1[-S, +T] {
    def apply(x: S): T
}

// In general, the n-ary function type, (T1, T2, ..., Tn) => T is interpreted as Functionn[T1, T2, ..., Tn, T]. Hence, functions are interpreted as objects with apply methods. For example, the anonymous "incrementer" function x: int => x + 1 would be expanded to an instance of Function1 as follows.
new Function1[Int, Int] {
    def apply(x: Int): Int = x + 1
}

// ----------------------------------------------------------------------------
// 4.3 Refining Functions
// Since function types are classes in Scala, they can be further refined in subclasses. An example are arrays, which are treated as special functions over the integer domain. 

// ----------------------------------------------------------------------------
// 4.4 Sequences

// the sqrts function
def sqrts(xs: List[Double]): List[Double] =
    xs filter (0 <=) map Math.sqrt
// Note that Math.sqrt comes from a Java class. Such methods can be passed to higher-order functions in the same way as methods defined in Scala.

// ----------------------------------------------------------------------------
// 4.5  For Comprehensions

// Scala offers special syntax to express combinations of certain higher-order functions more naturally. For comprehensions are a generalization of list comprehensions found in languages like Haskell. With a for comprehension the sqrts function can be written as follows:

// the sqrts function
def sqrts(xs: List[Double]): List[Double] =
    for (val x <- xs; 0 <= x) yield Math.sqrt(x)



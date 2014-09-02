// ----------------------------------------------------------------------------
// 9    Component Adaptation
// 1. Motivation
// 2. Implicit Parameters: The Basics   隐式参数,隐式对象,隐式Method,隐式类
// 3. Views
// 4. View Bounds

// 10 Related Work

// This unsatisfactory situation is commonly called the *external extensibility problem*. It has been argued that this problem holds back the development of software components to a mature industry where components are independently manufactured and deployed [28].
// Scala Introduces a new concept to solve the external extensibility problem: views allow one to augment a class with new members and supported traits.

// Views are a special case of implicit parameters which are themselves a useful tool for organizing rich functionality in systems. 
// Implicit parameters let one write generic code analogous to Haskell's type classes [12], or, in a C++ context, to Siek and Lumsdaine's "concepts" [43]. Unlike with type classes, the scope of an implicit parameter can be controlled, and competing implicit parameters can coexist in different parts of one program.

// ----------------------------------------------------------------------------
// 1. Motivation

abstract class SemiGroup[a] {
    def add(x: a, y: a): a
}

// Here's a subclass Monoid of SemiGroup which adds a unit element.
abstract class Monoid[a] extends SemiGroup[a] {
    def unit: a
}

// Here are two implementations of monoids:
object Monoids {
    object stringMonoid extends Monoid[String] {
        def add(x: String, y: String): String = x.concat(y)
        def unit: String = ""
    }
    object IntMonoid extends Monoid[Int] {
        def add(x: Int, y: Int): Int = x + y
        def unit: Int = 0
    }
}

// A sum method, which works over arbitrary monoids, can be written in plain Scala as follows.
def sum[a](xs: List[a])(m: Monoid[a]): a = 
    if (xs.isEmpty) m.unit else m.add(xs.head, sum(xs.tail)(m))

// One invokes this sum method by code such as:
sum(List("a", "bc", "def"))(Monoids.stringMonoid)
sum(List(1, 2, 3))(Monoids.IntMonoid)

// All this works, but it is not very nice. The problem is that the monoid implementations have to be passed into all code that uses them. 
// We would sometimes wish that the system could figure out the correct arguments automatically, similar to what is done when type arguments are inferred. This is what implicit parameters provide.

// ----------------------------------------------------------------------------
// 2. Implicit Parameters: The Basics

// The following slight rewrite of sum introduces m as an implicit parameter.
def sum[a](xs: List[a])(implicit m: Monoid[a]): a =
    if (xs.isEmpty) m.unit else m.add(xs.head, sum(xs.tail))
 
 // As can be seen from the example, it is possible to combine normal and implicit parameters. However, there may only be one implicit parameter list for a method or constructor, and it must come last.
 
 // implicit can also be used as a modifier for definitions and declarations. Examples:
implicit object stringMonoid extends Monoid[String] {
    def add(x: String, y: String): String = x.concat(y)
    def unit: String = ""
}
implicit object intMonoid extends Monoid[Int] {
    def add(x: Int, y: Int): Int = x + y
    def unit: Int = 0
}


// 上面这个必须添加 implicit,否则会提示下面错误
/*
scala> sum(List("a", "bc", "def"))
<console>:12: error: could not find implicit value for parameter m: Monoid[String]
              sum(List("a", "bc", "def"))
                 ^
*/

// 然后,调用是可以去掉后面的类型
sum(List("a", "bc", "def")) // (Monoids.stringMonoid)
sum(List(1, 2, 3))  // (Monoids.IntMonoid)
// The principal idea behind implicit parameters is that arguments for them can be left out from a method call. If the arguments corresponding to an implicit parameter section are missing, they are inferred by the Scala compiler.

// This discussion also shows that implicit parameters are inferred after any type arguments are inferred.

// 尝试sum Long
sum(List(1L, 2L, 3L))  // (Monoids.IntMonoid)
/*
scala> sum(List(1L, 2L, 3L))
<console>:16: error: could not find implicit value for parameter m: Monoid[Long]
              sum(List(1L, 2L, 3L))
                 ^
*/
// 然后定义
implicit object MyDM extends Monoid[Long] {
    def add(x: Long, y: Long): Long = {System.out.println("@MyDM"); x + y}
    def unit: Long = 0
}
// 再次尝试sum Long:  ok   所以,名字并不重要!重要的是后面的 "extends Monoid[Long] {"
sum(List(1L, 2L, 3L))
/*
scala> sum(List(1L, 2L, 3L))
@MyDM
@MyDM
@MyDM
res17: Long = 6
*/

// 若有两个MyDM呢?
implicit object MyDM2 extends Monoid[Long] {
    def add(x: Long, y: Long): Long = {System.out.println("@MyDM2"); x + y}
    def unit: Long = 0
}
// 再次尝试sum Long:  ok
sum(List(1L, 2L, 3L))
/*
scala> sum(List(1L, 2L, 3L))
<console>:18: error: ambiguous implicit values:
 both object MyDM of type MyDM.type
 and object MyDM2 of type MyDM2.type
 match expected type Monoid[Long]
              sum(List(1L, 2L, 3L))
                 ^
*/

// ----------------------------------------------
// 一个正确的 隐式转换函数
case class MyInt(i:Int)
implicit def MyInt2Ordered(x: MyInt): Ordered[MyInt] = {
    new Ordered[MyInt] {
        def compare(y: MyInt): Int =
        (x.i).compare(y.i)
        }
}
/*
def sort(xs: List[a])(implicit a2ord: a => Ordered[a]) = {
    
}*/

val myIntList = List(MyInt(1), MyInt(200), MyInt(100), MyInt(-100), MyInt(7), MyInt(10), MyInt(99))
myIntList.sorted
// myIntList.sorted(x:MyInt => MyInt2Ordered(x))  // 怎么明确指定一个转换函数呢?

implicit def int2ordered(x: Int): Ordered[Int] = {
    x.i
}
// This function is of course too good to be true. Indeed, if one tried to apply sort to an argument arg of a type that did not have another injection into the Ordered class, one would obtain an infinite expansion:
    sort(arg)(x => magic(x)(x => magic(x)(x => ... )))

// To prevent such infinite expansions, we require that every implicit method definition is contractive. Here a method definition is contractive if the type of every implicit parameter type is "properly contained" [35] in the type that is obtained by removing all implicit parameters from the method type and converting the rest to a function type.

// For instance, the type of list2ordered is
(List[a])(implicit a => Ordered[a]): Ordered[List[a]]
// This type is contractive, because the type of the implicit parameter, a => Ordered[a], is properly contained in the function type of the method without implicit parameters, List[a] => Ordered[List[a]]. 

// The type of magic is 
    (a)(implicit a => Ordered[a]): Ordered[a] .
// This type is not contractive, because the type of the implicit parameter, a => Ordered[a], is the same as the function type of the method without implicit parameters.

// ----------------------------------------------------------------------------
// 3. Views

// Views are implicit conversions between types. They are typically dened to add some new functionality to a pre  existing type. For instance, assume the following trait if simple generic sets:

trait Set[T] {
  def include(x: T): Set[T]
  def contains(x: T): Boolean
}

implicit def listToSet[T](xs: GenList[T]): Set[T] =
  new Set[T] {
    def include(x: T): Set[T] =
      xs prepend x
    def contains(x: T): boolean =
      !isEmpty && (xs.head == x || (xs.tail contains x))
  }

// Views are used frequently in the Scala library to upgrade Java's types to support new Scala traits. An example is Scala's trait Ordered which denes a set of comparison operations.

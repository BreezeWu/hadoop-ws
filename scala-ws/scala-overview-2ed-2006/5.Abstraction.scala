// ----------------------------------------------------------------------------
// 5 Abstraction

// 5.1 Functional Abstraction:  parameterization
// 5.2 Abstract Members:  abstract members.
// 5.3 Modeling Generics with Abstract Types

// There are two principal forms of abstraction in programming languages: parameterization and abstract members. 
// The first form is typically functional whereas the second form is typically object-oriented. 
// Traditionally, Java supported functional abstraction for values and object-oriented abstraction for operations. The new Java 5.0 with generics supports functional abstraction also for types.

// Scala supports both styles of abstraction uniformly for types as well as values. Both types and values can be parameters, and both can be abstract members. 
// The rest of this section presents both styles and reviews at the same time a large part of Scala's type system.

// ----------------------------------------------------------------------------
// 5.1 Functional Abstraction

// generic
// The following class defines cells of values that can be read and written.
class  GenCell[T](init:  T)  {
	private  var  value:  T  =  init
	def  get:  T  =  value
	def  set(x:  T):  Unit  =  {  value  =  x  }
}
// 
def  swap[T](x:  GenCell[T],  y:  GenCell[T]):  Unit  =  {
	val  t  =  x.get;  x.set(y.get);  y.set(t)
}

// The following program creates two cells of integers and then swaps their contents.
val  x:  GenCell[Int]  =  new  GenCell[Int](1)
val  y:  GenCell[Int]  =  new  GenCell[Int](2)
swap[Int](x,  y)

// Hence, one can equivalently propriate write the example above without any type arguments
val x = new GenCell(1)
val y = new GenCell(2)
swap(x, y)

// -------------------------------------
// Parameter bounds : subtyping
trait Ordered[T] {
    def < (x: T): Boolean
}

// The updateMax method can be defined in a generic way by using bounded polymorphism:
def updateMax[T <: Ordered[T]](c: GenCell[T], x: T) =
    if (c.get < x) c.set(x)
// Here, the type parameter clause [T <: Ordered[T]] introduces a bounded type parameter T. 
// It restricts the type arguments for T to those types T that are a subtype of Ordered[T]. Therefore, the < method of class Ordered can be applied to arguments of type T. The example shows that the bounded type parameter may itself appear as part of the bound, i.e. Scala supports F-bounded polymorphism

// 测试代码
case class MyInt(value:Int) extends Ordered[MyInt] {
    //def <(y:Int) = value < y
    def <(y:MyInt) = value < y.value
}

val myInt1 = new MyInt(10)
val myInt2 = new MyInt(11)
val myInt3 = new MyInt(9)
val x = new GenCell(myInt2)

x.get
updateMax(x,myInt2)  // updateMax[MyInt](x,myInt2)
x.get

updateMax(x,myInt3)  //updateMax[MyInt](x,myInt3)
x.get

// -------------------------------------
// Variance.
// Variance. The combination of subtyping and generics in a language raises the question how they interact. 

// If C is a type constructor and S is a subtype of T, does one also have that C[S] is a subtype of C[T]? 
// Type constructors with this property are called covariant. 
// The type constructor GenCell should clearly not be covariant; otherwise one could construct the following program which leads to a type error at run time.

val x: GenCell[String] = new GenCell[String]("abc")
val y: GenCell[Any] = x; // illegal!
/*
Note: String <: Any, but class GenCell is invariant in type T.
You may wish to define T as +T instead. (SLS 4.5)
       val y: GenCell[Any] = x; // illegal!
                             ^

*/
y.set(1)
val z: String = y.get

// It is the presence of a mutable variable in GenCell which makes covariance unsound. Indeed, a GenCell[String] is not a special instance of a GenCell[Any] since there are things one can do with a GenCell[Any] that one cannot do with a GenCell[String]; set it to an integer value, for instance.
// On the other hand, for immutable data structures, covariance of constructors is sound and very natural. For instance, an immutable list of integers can be naturally seen as a special case of a list of Any. 

// There are also cases where contravariance of parameters is desirable. An example are output channels Chan[T], with a write operation that takes a parameter of the type parameter T. Here one would like to have Chan[S] <: Chan[T] whenever T <: S.

// Scala allows to declare the variance of the type parameters of a class using plus or minus signs. 
// (1) A "+" in front of a parameter name indicates that the constructor is covariant in the parameter, 
// (2) a "−" indicates that it is contravariant,
// (3) and a missing prefix indicates that it is non-variant.

// For instance, the class GenList below defines a simple covariant list with methods isEmpty, head, and tail.
abstract class GenList[+T] {
    def isEmpty: Boolean
    def head: T
    def tail: GenList[T]
}

// Scala's type system ensures that variance annotations are sound by keeping track of the positions where a type parameter is used. 
// These positions are classified as covariant for the types of immutable fields and method results, and contravariant for method argument types and upper type parameter bounds. Type arguments to a non-variant type parameter are always in non-variant position. The position flips between contra- and co-variant inside a type argument that corresponds to a contravariant parameter. 
// The type system enforces that covariant (respectively, contravariant) type parameters are only used in covariant (contravariant) positions.

// Here are two implementations of the GenList class:
object Empty extends GenList[Nothing] {
    def isEmpty: Boolean = true
    def head: Nothing = throw new Error("Empty.head")
    def tail: GenList[Nothing] = throw new Error("Empty.tail")
}
class Cons[+T](x: T, xs: GenList[T])
    extends GenList[T] {
    def isEmpty: Boolean = false
    def head: T = x
    def tail: GenList[T] = xs
}

// ----------------------------------------------------------------------------
// 5.2 Abstract Members
abstract class AbsCell {
    type T
    val init: T
    private var value: T = init
    def get: T = value
    def set(x: T): Unit = { value = x }
}

// Instances of that class can be created by implementing these abstract members with concrete definitions. For instance:
val cell = new AbsCell { type T = Int; val init = 1 }
cell.set(cell.get * 2)

scala> val cell = new AbsCell { type T = Int; val init = 1 }
cell: AbsCell{type T = Int} = $anon$1@7a9c8b97

// The type of cell is AbsCell { type T = int }. 
// Here, the class type AbsCell is augmented by the refinement { type T = int }. 
// This makes the type alias cell.T = Int known to code accessing the cell value. Therefore, type specific operations such as the one below are legal.
cell.set(cell.get * 2)

def reset(c: AbsCell): Unit = c.set(c.init)
// 测试代码
cell.get
cell.set(cell.get * 2)
cell.get

// -----------------------------------
// path-dependent type
// c.T is an instance of a path-dependent type. In general, such a type has the form x1. . . . .xn.t, where n > 0, x1, . . . , xn denote immutable values and t is a type member of xn. 
// Path-dependent types are a novel concept of Scala; their theoretical foundation is provided by the νObj calculus

// Path-dependent types rely on the immutability of the prefix path. Here is an example where this immutability is violated.
var flip = false
def f(): AbsCell = {
    flip = !flip
    if (flip) new AbsCell { type T = Int; val init = 1 }
    else new AbsCell { type T = String; val init = "" }
}
f().set(f().get) // illegal!
// The type system does not admit this statement, because the computed type of f().get would be f().T. This type is not well-formed, since the method call f() is not a path.

// -----------------------------------
// Type selection and singleton types.

// The "#" operator denotes a type selection. 
// Note that this is conceptually different from a path dependent type p.Inner, where the path p denotes a value, not a type. Consequently, the type expression Outer # t is not well-formed if t is an abstract type defined in Outer.

// In fact, path dependent types in Scala can be expanded to type selections. 
// The path dependent type p.t is taken as a shorthand for p.type # t. Here, p.type is a singleton type, which represents just the object denoted by p. Singleton types by themselves are also useful for supporting chaining of method calls. For instance, consider a class C with a method incr which increments a protected integer field, and a subclass D of C which adds a decr method to decrement that field.

class C {
    protected var x = 0
    def incr: this.type = { x = x + 1; this }
    
    def get = x
}
class D extends C {
    def decr: this.type = { x = x - 1; this }
}
// Then we can chain calls to the incr and decr method, as in
val d = new D; d.incr.decr
// Without the singleton type this.type, this would not have been possible, since d.incr would be of type C, which does not have a decr member. In that sense, this.type is similar to (covariant uses of) Kim Bruce's mytype [9].

// -----------------------------------
// Family polymorphism and self types.

// Hence, subjects and observers refer to each other in their method signatures.
// 另一个例子见前面的
case class MyInt(value:Int) extends Ordered[MyInt] {
    //def <(y:Int) = value < y
    def <(y:MyInt) = value < y.value
}

// All elements of this design pattern are captured in the following system.
abstract class SubjectObserver {
    type S <: Subject
    type O <: Observer
    abstract class Subject requires S {     // 报错!!!  ';' expected but identifier found.
        private var observers: List[O] = List()
        def subscribe(obs: O) =
            observers = obs :: observers
        def publish =
            for (val obs <- observers) obs.notify(this)     // val 报错!!!
    }
    trait Observer {
        def notify(sub: S): Unit
    }
}

object Test {
    import SensorReader._
    val s1 = new Sensor { val label = "sensor1" }
    val s2 = new Sensor { val label = "sensor2" }
    def main(args: Array[String]) = {
        val d1 = new Display; val d2 = new Display
        s1.subscribe(d1); s1.subscribe(d2)
        s2.subscribe(d1)
        s1.changeValue(2); s2.changeValue(3)
    }
}

// -----------------------------------------
// 改写如下,但还是有错误
/*
<console>:22: error: type mismatch;
 found   : Subject.this.type (with underlying type SubjectObserver.this.Subject)
 required: SubjectObserver.this.S
                   for (obs <- observers) obs.notify(this)
                                                     ^
*/
abstract class SubjectObserver {  
    type S <: Subject
    type O <: Observer
    abstract class Subject  {
        private var observers: List[O] = List()
        def subscribe(obs: O) =
            observers = obs :: observers
        def publish =
            for (obs <- observers) obs.notify(this)
    }
    trait Observer {
        def notify(sub: S): Unit
    }
}

// Note also that class Subject carries an explicit requires annotation:
abstract class Subject requires S { ...
// The annotation expresses that Subject can only be instantiated as a part of a class that also conforms to S. 
// Here, S is called a self-type of class Subject. When a self-type is given, it is taken as the type of this inside the class (without a self-type annotation the type of this is taken as usual to be the type of the class itself). 
// In class Subject, the self-type is necessary to render the call obs.notify(this) type-correct.

// 其他实验
abstract class MyT {
    type S <: scala.AnyVal
    type O <: scala.AnyRef
 }
val myT = new MyT{type S=Int; type O=AbsCell}


object SensorReader extends SubjectObserver {
    type S = Sensor
    type O = Display
    abstract class Sensor extends Subject {
        val label: String
        var value: double = 0.0
        def changeValue(v: double) = {
            value = v
            publish
        }
    }   
    class Display extends Observer {
        def println(s: String) = println(s)
        def notify(sub: Sensor) =
        println(sub.label + " has value " + sub.value)
    }
}
// ----------------------------------------------------------------------------
// 5.3 Modeling Generics with Abstract Types

// In this section we show that functional type abstraction (aka generics) can indeed be modeled by object-oriented type abstraction (aka abstract types). The idea of the encoding is as follows.

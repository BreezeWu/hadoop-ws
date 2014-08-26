// ----------------------------------------------------------------------------
// Basic type inference

val n: Int = 1

// But it’s pretty obvious in this case what the type of n is. In many cases, you can omit the type ascriptions:
val n = 1 + 1
val s = "hello, type inference"
// In this case, the static types of n and s are inferred from the types of the expressions on the right hand side of the assignments.

// We can go further…
import util.Random._
val n = if (nextBoolean()) 1 else 0
val s = if (nextBoolean()) "less filling" else "tastes great"

// ----------------------------------------------------------------------------
// Least upper bounds

// What type is inferred here, if the types don’t quite line up?
val weird1 = if (nextBoolean()) 1 else 'a'                  // Int  
val weird2 = if (nextBoolean()) 1 else true                 // AnyVal
val weird3 = if (nextBoolean()) 1 else "tastes great"       // AnyRef

// We have to look at Scala’s type lattice (informally, a class hierarchy where all classes have both a common superclass—like Object in Java—as well as a common subclass) to find the closest common ancestor of the two sub-expression types:

// The AnyVal types correspond to the JVM’s primitive types, and the AnyRef types correspond to the JVM’s object and array types.

// convertibility
// Note that the grey arrows in this diagram don’t represent actual subclass relationships, just convertibility (“weak conformance”).
So the LUB of 1: Int and 'a': Char is Int by weak conformance, 
the LUB of 1: Int and true: Boolean is AnyVal, 
and the LUB of 1: Int and "tastes great": String is Any.

// We can do the same thing with classes:
class Base
class A extends Base
class B extends Base
val obviousAlready = if (nextBoolean()) new A else new B





// ----------------------------------------------------------------------------
// A Unified Object Model

// Scala uses a pure object-oriented model similar to Smalltalk's: Every value is an object and every operation is a message send.

// ----------------------------------
// 1. Classes

// Every class in Scala inherits from class Scala.Any.
// Subclasses of Any fall into two categories: the value classes which inherit from scala.AnyVal and the reference classes which inherit from scala.AnyRef. 
// Every primitive Java type name corresponds to a value class, and is mapped to it by a predefined type alias. 


// In a Java environment, AnyRef is identified with the root class java.lang.Object. 
// An instance of a reference class is usually implemented as a pointer to an object stored in the program heap. 
// An instance of a value class is usually represented directly, without indirection through a pointer.

//We considered a design alternative with subtyping between value classes. For instance, we could have made Int a subtype of Float, instead of having an implicit conversion fromInt to Float. 
// We refrained from following this alternative, because we want to maintain the invariant that interpreting a value of a subclass as an instance of its superclass does not change the value's representation. Among other things, we want to guarantee that for each pair of types S <: T and each instance x of S the following equality holds:

x.asInstanceOf[T].asInstanceOf[S] == x

// At the bottom of the type hierarchy are the two classes scala.Null and scala.Nothing. 
// Type Null is a subtype of all reference types; its only instance is the null reference. Since Null is not a subtype of value types, null is not a member of any such type. For instance, it is not possible to assign null to a variable of type int.

// Type Nothing is a subtype of every other type; there exist no instances of this type. Even though type Nothing is empty, it is nevertheless useful as a type parameter.
// For instance, the Scala library defines a value Nil of type List[Nothing]. Because lists are covariant in Scala, this makes Nil an instance of List[T], for any element type T.

// reference equality
eq

// 
For reference types, == is treated as an alias of the equals method from java.lang.Object. 
That method is originally defined as reference equality, but is meant to be overridden in subclasses to implement the natural notion of equality for these subclasses. 

// ----------------------------------
// 2.  Operations

// First, Scala treats operator names as ordinary identifiers. 
// More precisely, an identifier is either a sequence of letters and digits starting with a letter, or a sequence of operator characters. Hence, it is possible to define methods called +, <=, or ::, for example. 

// Next, Scala treats every occurrence of an identifier between two expressions as a method call. For instance, in Listing 1, one could have used the operator syntax (arg startsWith "-") as syntactic sugar for the more conventional syntax (arg.startsWith("-")).

// 即 识别identifiers
// 在两个identifiers之间的是 operators.  "user-defined infix operators"?

// examples: 
// class Nat for natural numbers. This class (very ineficiently) represents numbers as instances of two classes Zero and Succ. 
// The number N would hence be represented as new SuccN(Zero). 

:paste
abstract class Nat {
    def isZero: Boolean
    def pred: Nat
    def succ: Nat = new Succ(this)
    def + (x: Nat): Nat =
            if (x.isZero) this else succ + x.pred
    def - (x: Nat): Nat =
            if (x.isZero) this else pred - x.pred
}
    
object Zero extends Nat {
    def isZero: Boolean = true
    def pred: Nat = throw new Error("Zero.pred")
    override def toString: String = "Zero"
}

class Succ(n: Nat) extends Nat {
    def isZero: Boolean = false
    def pred: Nat = n
    override def toString: String = "Succ("+n+")"
}

// The override modifier is required in Scala for methods that override a *concrete method* in some inherited class; it is optional for methods that implement some *abstract method* in their superclass. The modifier gives useful redundancy to protect against two common class of errors. 

// operators的优先级
// Scala opts for a simpler scheme with fixed precedences and associativities. 
// The precedence of an infix operator is determined by its first character; it coincides with the operator precedence of Java for those operators that start with an operator character used in these languages. 
// The following lists operators in increasing precedence:
(all letters)
|
^
&
< >
= !
:
+ -
* / %
(all other special characters)

// Operators are usually left-associative, i.e. x + y + z is interpreted as (x + y) + z. 
// The only exception to that rule are operators ending in a colon. These are treated as right-associative. An example is the list-consing operator ::. Here, x :: y :: zs is interpreted as x :: (y :: zs).

// Right-associative operators are also treated differently with respect to method lookup. Whereas normal operators take their left operand as receiver, right-associative operators take their right operand as receiver. 
// For instance, the list consing sequence x :: y :: zs is treated as equivalent to zs.::(y).::(x). In fact, :: is implemented as a method in Scala's List class, which prefixes a given argument to the receiver list and returns the resulting list as result.

abstract class Bool {
    def && (x: => Bool): Bool       // these parameters (x) are passed in unevaluated form
    def || (x: => Bool): Bool           // these parameters (x) are passed in unevaluated form
}
// these parameters are passed in unevaluated form. The arguments are evaluated every time the formal parameter name is mentioned (that is, the formal parameter behaves like a parameterless function).

// Here are the two canonical instances of class Bool:
object False extends Bool {
    def && (x: => Bool): Bool = this    // left operand (this)是真,所以,不再evaluating right operand
    def || (x: => Bool): Bool = x
}
object True extends Bool {
    def && (x: => Bool): Bool = x
    def || (x: => Bool): Bool = this    // left operand (this)是假,所以,不再evaluating right operand
}

// As can be seen in these implementations, the right operand of a && (resp. ||) operation is evaluated only if the left operand is the True (False) object.

// As the examples in this section show, it is possible in Scala to define every operator as a method and treat every operation as an invocation of a method. In the interest of efficiency, the Scala compiler translates operations on value types directly to primitive instruction codes; this, however, is completely transparent to the programmer.

// ----------------------------------
// 3. ❱❛r✐❛❜❧❡s ❛♥❞ Pr♦♣❡rt✐❡s



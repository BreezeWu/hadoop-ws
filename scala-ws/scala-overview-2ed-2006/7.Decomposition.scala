// ----------------------------------------------------------------------------
// 7    Decomposition
// 7.1    Object-Oriented Decomposition
// 7.2    Pattern Matching Over Class Hierarchies

// ----------------------------------------------------------------------------
// 7.1    Object-Oriented Decomposition
//  Using an object-oriented implementation scheme, we can decompose the evaluator according to the term structure as follows:
abstract  class  Term  {
	def  eval:  Int
}
class  Num(x:  Int)  extends  Term  {
	def  eval:  Int  =  x
}
class  Plus(left:  Term,  right:  Term)  extends  Term  {
	def  eval:  Int  =  left.eval  +  right.eval
}

// Such an object-oriented decomposition scheme requires the anticipation of all operations traversing a given structure.

// ----------------------------------------------------------------------------
// 7.2    Pattern Matching Over Class Hierarchies

// The  program  above  is  a  good  example  for  cases  where  a functional decomposition scheme is more appropriate.  In a functional language, a programmer typically separates the denition of the data structure from the implementation of the operations. 

//  While data structures are usually dened using algebraic datatypes, operations on such datatypes are simply functions which use pattern matching  as the basic decomposition principle.

//  Using case classes, the algebraic term example can be implemented as follows:
abstract  class  Term
case  class  Num(x:  Int)  extends  Term
case  class  Plus(left:  Term,  right:  Term)  extends  Term

// Given these denitions, it is now possible to create the algebraic term 1 + 2 + 3 without using the new primitive, simply by calling the constructors associated with case classes:
Plus(Plus(Num(1),  Num(2)),  Num(3))

// Scala’s pattern matching expressions provide a means of decomposition that uses these constructors as patterns.  Here is  the  implementation  of  the  eval  function  using  pattern matching:
object  Interpreter  {
	def  eval(term:  Term):  Int  =  term  match  {
		case  Num(x)  =>  x
		case  Plus(left,  right)  =>  eval(left)  +  eval(right)
	}
}

val mysome0 = new Some(Term)
val mysome1 = new Some(Num)
val mysome2 = new Some(Plus)















// ----------------------------------------------------------------------------
// ----------------------------------------------------------------------------
// There are four tree constructors: 		[变量-函数-函数调用-表达式]
//	Var for variables,
//	Lam for function abstractions, 
//	App for function applications, 
//	and Let for let expressions. 

// type Term is an open algebraic type for which further alternatives can be deﬁned.
abstract class Term {}
case class Var(x: String) extends Term {
	override def toString = x
}
case class Lam(x: String, e: Term) extends Term {
	override def toString = "(\\" + x + "." + e + ")"
}
case class App(f: Term, e: Term) extends Term {
	override def toString = "(" + f + " " + e + ")"
}
case class Let(x: String, e: Term, f: Term) extends Term {
	override def toString = "let " + x + " = " + e + " in " + f
}

// ----------------------------------------------------------------------------
// We next deﬁne the types that are computed by the inference system. [变量类型-函数类型-?类型]
// There are three type constructors: 
//	Tyvar for type variables, 
//	Arrow for function types
//	and Tycon for type constructors such as Boolean or List. 
//
// Type constructors have as component a list of their type parameters. 
// This list is empty for type constants such as Boolean. 
// Again, the type constructors implement the toString method in order to display types legibly.

// 注意: Note that Type is a sealed class. 
//  This makes Type a closed algebraic data type with exactly three alternatives
sealed abstract class Type {}
case class Tyvar(a: String) extends Type {
	override def toString = a
}

case class Arrow(t1: Type, t2: Type) extends Type {
	override def toString = "(" + t1 + "->" + t2 + ")"
}

case class Tycon(k: String, ts: List[Type]) extends Type {
	override def toString =
	k + (if (ts.isEmpty) "" else ts.mkString("[", ",", "]"))
}

// ----------------------------------------------------------------------------
// The main parts of the type inferencer are contained in object typeInfer.

object typeInfer {
	private var n: Int = 0
	def newTyvar(): Type = { n += 1; Tyvar("a" + n) }


}
// ----------------------------------------------------------------------------
// 
// ----------------------------------------------------------------------------
// 
// ----------------------------------------------------------------------------
// 
// ----------------------------------------------------------------------------
// 
// ----------------------------------------------------------------------------
// 
// ----------------------------------------------------------------------------
// 

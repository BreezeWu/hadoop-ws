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

	/*
	We next deﬁne a class for substitutions. A substitution is an idempotent function
	from type variables to types. It maps a ﬁnite number of type variables to some types,
	and leaves all other type variables unchanged. The meaning of a substitution is
	extended point-wise to a mapping from types to types. We also extend the meaning
	of substitution to environments, which are deﬁned later.

	"from types to types" ---> "from type variables to types"
	*/

	abstract class Subst extends Function1[Type,Type] {
		def lookup(x: Tyvar): Type

		def apply(t: Type): Type = t match {
			case tv @ Tyvar(a) => val u = lookup(tv); if (t == u) t else apply(u)
			case Arrow(t1, t2) => Arrow(apply(t1), apply(t2))
			case Tycon(k, ts) => Tycon(k, ts map apply)
		}

		def apply(env: Env): Env = env.map({ case (x, TypeScheme(tyvars, tpe)) =
			// assumes tyvars don’t occur in this substitution
			(x, TypeScheme(tyvars, apply(tpe)))
		})

		def extend(x: Tyvar, t: Type) = new Subst {
			def lookup(y: Tyvar): Type = if (x == y) t else Subst.this.lookup(y)

		}
	}

	val emptySubst = new Subst { def lookup(t: Tyvar): Type = t }

	// We represent substitutions as functions, of type Type => Type. This is achieved by
	// making class Subst inherit from the unary function type Function1[Type, Type]1.

	// To be an instance of this type, a substitution s has to implement an apply method
	// that takes a Type as argument and yields another Type as result. A function applica-
	// tion s(t) is then interpreted as s.apply(t).

	// --------------------------
	/*
	The next data type describes type schemes, which consist of a type and a list of
	names of type variables which appear universally quantiﬁed in the type scheme. For
	instance, the type scheme 8a8b.a!b would be represented in the type checker as:
	*/

	// TypeScheme(List(Tyvar("a"), Tyvar("b")), Arrow(Tyvar("a"), Tyvar("b"))) .

	case class TypeScheme(tyvars: List[Tyvar], tpe: Type) {
		def newInstance: Type = {
		(emptySubst /: tyvars) ((s, tv) => s.extend(tv, newTyvar())) (tpe)
	}
}
// ----------------------------------------------------------------------------
// 
/*
The last type we need in the type inferencer is Env, a type for environments, which
associate variable names with type schemes. They are represented by a type alias
Env inmodule typeInfer:
*/
	type Env = List[(String, TypeScheme)]

	// There are two operations on environments. 

	// The lookup function returns the type scheme associated with a given name, 
	// 	or null if the name is not recorded in the environment.
	def lookup(env: Env, x: String): TypeScheme = env match {
		case List() => null
		case (y, t) :: env1 => if (x == y) t else lookup(env1, x)
	}

	// The gen function turns a given type into a type scheme, quantifying over all type
	//	variables that are free in the type, but not in the environment.
	def gen(env: Env, t: Type): TypeScheme =
		TypeScheme(tyvars(t) diff tyvars(env), t)

…………………… 看不懂了啊!
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

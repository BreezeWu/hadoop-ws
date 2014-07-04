abstract class Expr
case class Number(n: Int) extends Expr
case class Sum(e1: Expr, e2: Expr) extends Expr

// 应用
val expr02 = Sum(Sum(Number(1), Number(2)), Number(3))
// 对比 val expr01 = new Sum(new Number(1), new Sum(new Number(3), new Number(7)))

Sum(Number(1), Number(2)) == Sum(Number(1), Number(2))
/*
will yield true. If Sum or Number were not case classes, the same expression
would be false, since the standard implementation of equals in class AnyRef
always treats objects created by different constructor calls as being differ-
*/

// 使用pattern matching的eval实现
def eval2(e: Expr): Int = e match {
	case Number(n) => n
	case Sum(l, r) => eval2(l) + eval2(r)
}

// 应用
eval2(expr02)


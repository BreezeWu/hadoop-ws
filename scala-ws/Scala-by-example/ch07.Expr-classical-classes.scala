abstract class Expr {
	def isNumber: Boolean
	def isSum: Boolean
	def numValue: Int
	def leftOp: Expr
	def rightOp: Expr
}

class Number(n: Int) extends Expr {
	def isNumber: Boolean = true
	def isSum: Boolean = false
	def numValue: Int = n
	def leftOp: Expr = error("Number.leftOp")
	def rightOp: Expr = error("Number.rightOp")
}

class Sum(e1: Expr, e2: Expr) extends Expr {
	def isNumber: Boolean = false
	def isSum: Boolean = true
	def numValue: Int = error("Sum.numValue")
	def leftOp: Expr = e1
	def rightOp: Expr = e2
}

// 应用
//  an expression 1 + (3 + 7) would be represented as
val expr01 = new Sum(new Number(1), new Sum(new Number(3), new Number(7)))

// 计算expr的function
def eval(e: Expr): Int = {
	if (e.isNumber) e.numValue
	else if (e.isSum) eval(e.leftOp) + eval(e.rightOp)
	else error("unrecognized expression kind")
}

// 应用
eval(expr01)

// 打印表达式
def print(e: Expr) {
	if (e.isNumber) Console.print(e.numValue)
	else if (e.isSum) {
		Console.print("(")
		print(e.leftOp)
		Console.print("+")
		print(e.rightOp)
		Console.print(")")
	} else error("unrecognized expression kind")
}

// 应用
print(expr01)

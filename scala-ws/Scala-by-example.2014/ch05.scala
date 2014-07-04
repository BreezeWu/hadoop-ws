// ----------------------------------------------------------------------------
// anonymous function
def sum(f: Int => Int, a: Int, b: Int): Int =
if (a > b) 0 else f(a) + sum(f, a + 1, b)

// 无名函数
def sumInts(a: Int, b: Int): Int = sum((x: Int) => x, a, b)
def sumSquares(a: Int, b: Int): Int = sum((x: Int) => x * x, a, b)

// 有名函数
def sumPowersOfTwo(a: Int, b: Int): Int = sum(powerOfTwo, a, b)
def powerOfTwo(x: Int): Int = if (x == 0) 1 else 2 * powerOfTwo(x - 1)

// ----------------------------------------------------------------------------
// 5.2 currying
def sum(f: Int => Int): (Int, Int) => Int = {
def sumF(a: Int, b: Int): Int =
if (a > b) 0 else f(a) + sumF(a + 1, b)
sumF
}

// 或者:使用def定义
def sumInts = sum(x => x)
def sumSquares = sum(x => x * x)
def sumPowersOfTwo = sum(powerOfTwo)

// 或者:使用val也是一眼的
val sumInts = sum(x => x)
val sumSquares = sum(x => x * x)
val sumPowersOfTwo = sum(powerOfTwo)

// 运用
sumSquares(1, 10) + sumPowersOfTwo(10, 20)
// ----------------------------------------------------------------------------
// finding the fixed points of functions
import scala.math._

val tolerance = 0.0001
def isCloseEnough(x: Double, y: Double) = abs((x - y) / x) < tolerance
def fixedPoint(f: Double => Double)(firstGuess: Double) = {

def fixedPoint(f: Double => Double)(firstGuess: Double) = {
def iterate(guess: Double): Double = {
val next = f(guess)
println(next)
if (isCloseEnough(guess, next)) next
else iterate(next)
}
iterate(firstGuess)
}

def sqrt(x: Double) = fixedPoint(y => (y + x/y) / 2)(1.0)

// call
sqrt(2.0)

// 通用的逼近函数: 替代 y=> (y+x/y)/2
def averageDamp(f: Double => Double)(x: Double) = (x + f(x)) / 2
// 重新定义sqrt
def sqrt(x: Double) = fixedPoint(averageDamp(y => x/y))(1.0)
// ----------------------------------------------------------------------------
// ----------------------------------------------------------------------------
// ----------------------------------------------------------------------------
// ----------------------------------------------------------------------------
// ----------------------------------------------------------------------------



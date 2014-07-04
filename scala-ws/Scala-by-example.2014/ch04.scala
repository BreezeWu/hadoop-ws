def radius = 10
def pi = 3.141592653589793


2 * pi * radius
(2 * 3.141592653589793) * radius
6.283185307179586 * radius
6.283185307179586 * 10
62.83185307179586

// ----------------------------------------
def loop: Int = loop
def first(x: Int, y: Int) = x

first(1, loop)  // 死循环了! 因为第二个参数默认也是call-by-value但若是call-by-name,则不会!

// -----------------------------
def constOne(x: Int, y: => Int) = 1
constOne(1, loop) // 能正常计算出1. 因为y是call-by-name
constOne(loop, 2) // gives an infinite loop. 因为x是call-by-value,要对x求值

// -----------------------求平方根方法
import scala.math.abs
//import scala.math.square

def improve(guess: Double, x: Double) =
(guess + x / guess) / 2

def square(x:Double) = x* x

def isGoodEnough(guess: Double, x: Double) =
abs(square(guess) - x) < 0.001

def sqrtIter(guess: Double, x: Double): Double =
{ 
  if (isGoodEnough(guess, x)) guess
  else sqrtIter(improve(guess, x), x)
}

def sqrt(x: Double) = sqrtIter(1.0, x)

// nested function
def sqrt(x: Double) = {
def sqrtIter(guess: Double, x: Double): Double =
if (isGoodEnough(guess, x)) guess
else sqrtIter(improve(guess, x), x)
def improve(guess: Double, x: Double) =
(guess + x / guess) / 2
def isGoodEnough(guess: Double, x: Double) =
abs(square(guess) - x) < 0.001
sqrtIter(1.0, x)
}

// nested function简化版本:因为上层函数的变量可直接被下面函数访问,不需要使用传参方式带入
def sqrt(x: Double) = {
def sqrtIter(guess: Double): Double =
if (isGoodEnough(guess)) guess
else sqrtIter(improve(guess))
def improve(guess: Double) =
(guess + x / guess) / 2
def isGoodEnough(guess: Double) =
abs(square(guess) - x) < 0.001
sqrtIter(1.0)
}

// tail recursion
def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)
// 或者
def gcd2(a: Int, b: Int): Int = if (b != 0) gcd2(b, a%b) else a

def factorial(n: Int): Int = if (n == 0) 1 else n * factorial(n - 1)

 

// ----------------------------------------------------------------------------
// Methods: def

// Method defs are syntactically uniform with var and val declarations. The only difference is that defs can take parameters:
def add(a: Int, b: Int): Int = a + b
// The type ascription is in the same place, right before the =. Also note: no braces or return statement are needed; the body of the add method is just an expression. 

def add(a, b) = a + b       // cannot work

// Type ascriptions are always required for method parameters (note: this is enforced by the language syntax, not the type checker), but you can usually omit the method’s result type ascription:
def add(a: Int, b: Int) = a + b

// The same rules you saw previously, about least upper bounds, apply here as well:
import util.Random._
def contrived(a: Int, b: String) = if (nextBoolean()) a else b

// ----------------------------------------------------------------------------
// Named and default arguments

// When calling a method, you don’t have to pass the arguments in the order they’re declared in the method parameter list; you can use named arguments instead:
def formatUser(userId: Long, userName: String, realName: String): String =
  "%s <%d>: %s".format(userName, userId, realName)

formatUser(
  realName = "Dan Rosen",
  userName = "drosen",
  userId = 31337
)

// Note for C refugees
// Don’t use terrible names for your method parameters. In Scala, those names are part of your public API, not just implementation detail.

// Named arguments also allow us to provide defaults:
def formatUser(
    userId: Long = 0,
    userName: String = "unknown",
    realName: String = "Unknown"
  ): String =
  "%s <%d>: %s".format(userName, userId, realName)

formatUser(userName = "drosen")

// ----------------------------------------------------------------------------
// Explicit type ascriptions

// Even though you can usually omit method result type ascriptions, most of the time it’s good practice to include them, because:
// (1) They serve as useful API documentation, saving other developers the hassle of trying to do type inference on your code in their heads.
// (2) The type inference algorithm assumes you don’t make mistakes. It occasionally infers a type you didn’t intend, due to a mistake in your code. Usually this will cause a compile error, as the resulting program fails to typecheck, but occasionally—surprisingly—compilation will succeed. This is bad.

// There is also a situation where you must provide a method result type: recursive methods.
def fib(n: Int) = if (n == 0 || n == 1) 1 else fib(n - 1) + fib(n - 2)  // shouldn't work

// ----------------------------------------------------------------------------
// Side-effecting methods and Unit

// How about methods, or statements, that don’t produce any value?
println("hello") // what's the result type of println?

// In C-influenced languages, the result type would be void, representing the absence of a value; but the notion that some methods can produce values and some can’t adds some degree of inconsistency and complexity to these languages. Scala, along with other functional languages such as Haskell and ML, introduces a type called Unit which has a single value: (). You can’t actually do anything interesting with ()—in that respect it’s very similar to void—but its presence comes in handy when you start using higher-order functions.

// Some more examples of the Unit type and () value:


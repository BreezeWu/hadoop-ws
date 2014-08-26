// ----------------------------------------------------------------------------
// Mutable variables: var

var n: Int = 1 + 1
n = n + 1

//  but don’t cross the streams:
var s: String = "hello, scala"
n = s // shouldn't work
s = n // shouldn't work either

// ----------------------------------------------------------------------------
// Statements vs. expressions

// Statements
// Imperative-style programming, as in most procedural and object-oriented languages since the 1950s, is all about the sequential execution of statements. The execution of a statement has some effect on the system’s state (e.g. assigning a value to a variable) and different code paths can execute predicated on the current state.

import util.Random._
var n: Int = 0
if (nextBoolean()) {
  println("randomly true")
  n = n + 1
} else {
  println("randomly false")
  n = n - 1
}
println(n)

// expressions
// In Scala, it’s more conventional to use control-flow structures as expressions, which have meaningful types at compile time and directly produce values at runtime.
n = if (nextBoolean()) n + 1 else n - 1

// block expressions
// Look ma, no side effects! n + 1 and n - 1 are both expressions of type Int, so the if-else expression will also have type Int. You can keep the println statements if you want to, by using block expressions:
n =
  if (nextBoolean()) {
    println("randomly true")
    n + 1
  } else {
    println("randomly false")
    n - 1
  }

// The big deal about expressions is that they compose cleanly:
var s: String =
  if (n % 2 == 0)
    "n is even: %d, half is: %d".format(n, n / 2)
  else
    "n is odd: %d, randomly fixing it: %d".format(n, if (nextBoolean()) n + 1 else n - 1)

// ----------------------------------------------------------------------------
// Immutable values: val

// Given that expressions compose, there isn’t nearly as much need for mutable variables as there would be in an imperative style. It’s typically easiest to compute values by composing expressions rather than sequencing effects. So we use val most of the time:

val n: Int = 1 + 1
// vals are immutable:
n = n + 1 // shouldn't work

// Protip: use val
// Sometimes var is the right thing. Most of the time it isn’t.














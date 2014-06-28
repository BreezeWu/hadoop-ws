// ----------------------------------------------------------------------------
// 1.7 Bringing it all together: in-core SSVD
// http://weatheringthrutechdays.blogspot.com/search?updated-min=2013-01-01T00:00:00-08:00&updated-max=2014-01-01T00:00:00-08:00&max-results=1
import org.apache.mahout.math.scalabindings._
import RLikeOps._
// import MatlabLikeOps._

import org.apache.mahout.math.DenseMatrix
import org.apache.mahout.math.Matrix
import org.apache.mahout.math._
import scala.math._
import scala.util._
// Just to illustrate semantic clarity, we will adduce a source for in-core SSVD code.
/**
* In-core SSVD algorithm.
*
* @param a input matrix A
* @param k request SSVD rank
* @param p oversampling parameter
* @param q number of power iterations
* @return (U,V,s)
*/
def ssvd(a: Matrix, k: Int, p: Int = 15, q: Int = 0) = {
    val m = a.nrow
    val n = a.ncol
    if (k > min(m, n))
      throw new IllegalArgumentException(
        "k cannot be greater than smaller of m,n")
    val pfxed = min(p, min(m, n) - k)

    // actual decomposition rank
    val r = k + pfxed

    // we actually fill the random matrix here
    // just like in our R prototype, although technically
    // that would not be necessary if we implemented specific random
    // matrix view. But ok, this should do for now.
    // it is actually the distributed version we are after -- we
    // certainly would try to be efficient there.

    val rnd = new Random()
    val omega = new DenseMatrix(n, r) := ((r, c, v) => rnd.nextGaussian)

    var y = a %*% omega
    var yty = y.t %*% y
    val at = a.t
    var ch = chol(yty)
    var bt = ch.solveRight(at %*% y)

    // power iterations
    for (i <- 0 until q) {
      y = a %*% bt
      yty = y.t %*% y
      ch = chol(yty)
      bt = ch.solveRight(at %*% y)
    }

    val bbt = bt.t %*% bt
    val (uhat, d) = eigen(bbt)

    val s = d.sqrt
    val u = ch.solveRight(y) %*% uhat
    val v = bt %*% (uhat %*%: diagv(1 /: s))

    (u(::, 0 until k), v(::, 0 until k), s(0 until k))
  }

// And a very-very naive test for it:

  def test("SSVD"): Unit = {

    val a = dense((1, 2, 3), (3, 4, 5))

    val (u, v, s) = ssvd(a, 2, q=1)

    printf("U:\n%s\n", u)
    printf("V:\n%s\n", v)
    printf("Sigma:\n%s\n", s)

    val aBar = u %*% diagv(s) %*% v.t

    val amab = a - aBar

    printf("A-USV'=\n%s\n", amab)

    assert(amab.norm < 1e-10)

  }

  def main(args: Array[String]): Unit = {
    println("Hello Scala!");
  }

// Mahout Scala Bindings and Mahout Spark Bindings for Linear Algebra Subroutines
// http://weatheringthrutechdays.blogspot.com/
// ----------------------------------------------------------------------------
// 1.1 Imports

import org.apache.mahout.math.scalabindings._
import RLikeOps._
import MatlabLikeOps._

 ----------------------------------------------------------------------------
// 1.2 Inline initialization

// ------------------------
val denseVec1: Vector = (1.0, 1.1, 1.2)
val denseVec2 = dvec(1, 0, 1.1, 1.2)

// Sparse vectors
val sparseVec = svec((5 -> 1) :: (10 -> 2.0) :: Nil)
val sparseVec2: Vector = (5 -> 1.0) :: (10 -> 2.0) :: Nil
// matrix inline initialization, either dense or sparse, is always row-wise:
// dense matrices :
val A = dense((1, 2, 3), (3, 4, 5))
// sparse matrices
val B = sparse(
	(1, 3) :: Nil,
	(0, 2) :: (10, 2.5) :: Nil
	)
// diagonal matrix with constant diagonal elements
// val diag01 = 
// diag(10, 3.5)
val diag01 = diag(10, 3)
// diagonal matrix with main diagonal backed by a vector
val diag02 = diagv((1, 2, 3, 4, 5))
//Identity matrix
val idM = eye(10)

// -------------------------------
val vec = sparseVec;
// geting vector element
val d = vec(5)
// setting vector element
vec(5) = 3.0

val l01 = svec((5, 1) :: (11, 2.0) :: Nil)
val l02 = svec((6, 11) :: (12, 6.0) :: Nil)
val m = sparse(l01,l02)
l01.size
l02.size

val l03 = dvec(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
val l04 = dvec(100, 101, 102, 103, 104, 105, 106, 107, 108, 109)
val l05 = dvec(200, 201, 202, 203, 204, 205, 206, 207, 208, 209)
val l06 = dvec(360, 361, 362, 363, 364, 365, 366, 367, 368, 369)
val m = dense(l03,l04,l05,l06)
// val m  = sparse(
// 	(5, 1) :: (11, 2.0) :: Nil,
// 	(6, 11) :: (12, 6.0) :: Nil
// 	)

m
m.numRows
m.numCols
m.viewPart(1,1,1,1)

// getting matrix element
val d = m(3,5)

// ???? setting matrix element (setQuick() behind the scenes) 
//val M = m
val l1 = dvec(0, .1, .2, .3, .4, .5, .6, .7, .8, .9)
val l2 = dvec(10.0, 10.1, 10.2, 10.3, 10.4, 10.5, 10.6, 10.7, 10.8, 10.9)
val l3 = dvec(20.0, 20.1, 20.2, 20.3, 20.4, 20.5, 20.6, 20.7, 20.8, 20.9)
val l4 = dvec(36.0, 36.1, 36.2, 36.3, 36.4, 36.5, 36.6, 36.7, 36.8, 36.9)
val M = dense(l1,l2,l3,l4)

M(3,5) = 3.0
// Getting matrix row or column
val rowVec = M(3, ::)
val colVec = M(::, 3)
// Setting matrix row or column
M(3, ::) = (1, 2, 3)   // 此时会产生一个不是矩阵的M,因为第4行只有三个元素了.
M(::, 3) = (1, 2, 3)
// thru vector assignment also works
M(3, ::) := (1, 2, 3)
M(::, 3) := (99991, 9992, 993)

//  什么是vector assignment? 
// val tuple01 = (1,2,3,4,5)
// ???? val vector := (1,2,3,4,5) 命令未结束?

val A = M
// subslices of row or vector work too
A(0, 0 to 1) = (3, 5)
// or with vector assignment
A(0, 0 to 1) := (3333, 5555)
// matrix contiguous block as matrix, with assignment
// block
val B = A(2 to 3, 3 to 4)
// asignment to a block
A(0 to 1, 1 to 2) = dense((3, 2), (2, 3))

// or thru the matrix assignment operator
A(0 to 1, 1 to 2) := dense((100000003, 10000002), (200002, 2000003))

// Assignment operator by copying between vectors or matrix
val vec1: Vector = (55555.0, 666661.1, 7777.2)
val vec2 := vec1 // ???? 不能赋值成功?
val M1 := M	 // ???? 不能赋值成功?

vec1 := vec2	// ???? 这个语法错误!
M1 := M2	// ???? 这个语法错误!

// also works for matrix subindexing notations as per above
// Assignment thru a function literal (matrix)
M := ((row, col, x) => if (row == col) 1 else 0)

val M = dense(l1,l2,l3,l4)
M := ((row, col, x) => if (row == col) 1 else 0)  // ????
// for a vector, the same:
// vec := ((index, x) => sqrt(x))  // ????
val vec = dvec(1,2)
vec := ((index, x) => x*x) 

// ----------------------------------------------------------------------------
// 1.4 BLAS-like operations

// vector
val a:Vector = (2,4,6,8)
val b:Vector = (1,10,100,1000)
//val b:Vector = (1,10,100,1000,10000, 9999, 999, 99, 9)

// matrix
val a = dense(
	(1,3,5,7),
	(2,4,6,8)
	)
val b = dense(
	(0.1,0.01,0.001,0.0001),
	(1,10,100,1000)
	)

// plus/minus, either vector or matrix or numeric, with assignment or not
a + b
a - b
a + 5.0
a - 5.0
// Hadamard (elementwise) product, either vector or matrix or numeric operands
a * b
a * 5
// same things with assignment, matrix, vector or numeric operands
a += b
a -= b
a += 5.0
a -= 5.0
a *= b
a *= 5
// One nuance here is associativity rules in scala. E.g. 1/x in R (where x is vector or matrix) is elementwise
// inverse operation and in scala realm would be expressed as
var x = b

val xInv = 1 /: x  // 而1/x是错误的.另外 "/:"是连接在一起的

// and R’s 5.0 - x would be
val x1 = 5.0 -: x

// Even trickier and really probably not so obvious stuff :
a -=: b
// assigns a - b to b (in-place) and returns b. Similarly for a /=: b or 1 /=: v.
// (all assignment operations, including :=, return the assignee argument just like in C++)

// dot product (vector operands)
// vector
val va:Vector = (2,4,6,8)
val vb:Vector = (1,10,100,1000)
va dot vb

// matrix /vector equivalency (or non-equivalency). Dangerous, exact equivalence is rarely useful, better use
// norm comparisons with admission of small errors
a === b
a !== b

// ----------------------------------------------------------------------
// b的行数要等于a的列数
// matrix
val a = dense(
	(1,3,5,7),
	(2,4,6,8)
	)
val bt = dense(
	(0.1,0.01,0.001,0.0001),
	(1,10,100,1000),
	(2,20,200,2000),
	(3,30,300,3000),
	(4,40,400,4000),
	(5,50,500,5000)
	)
val b = bt.t
// Matrix multiplication (matrix operands)
a %*% b
// for matrices that explicitly support optimized right and left muliply (currently, diagonal matrices)
// right-multiply (for symmetry, in fact same as %*%)
// diag(5,5) :%*% b
// 这里b为4*6,行数为4,所以左乘时,diag(5,5)的列数要改为4
val left_multiply = diag(1.23,4) :%*% b

// ------------------没有右乘!但 右乘= diag*矩阵的转置 即 
val right_multiply = diag(1.23,6) :%*% b.t	// 由于转置后的行数为6,所以diag也要为6
// optimized left multiply with a diagonal matrix:
// A %*%: diag(5,5) # i.e. same as (diag(5,5) :%*% A.t) t
// b的列数为6,所以右乘时,diag的行数要为4.这个可以定义么?
 // b %*%: diag(2.22,4).t

// optimized left multiply with a diagonal matrix:
// A %*%: diag(5,5) # i.e. same as (diag(5,5) :%*% A.t) t

// Second norm, vector or matrix argument:
a.norm
// Finally, transpose
val Mt = M.t

// Note: Transposition currently is handled via view, i.e. updating a transposed matrix will be updating the
// original. Also computing something like X>X
val X = b
val XtX = X.t %*% X
// will not therefore incur any additional data copying.

// ----------------------------------------------------------------------------
// 1.5 Decompositions

// 方阵M
val M = dense(
	(0.1,0.01,0.001,0.0001),
	(1,10,100,1000),
	(2,20,200,2000),
	(3,30,300,3000)
	)

val Ma = dense(
	(1,3,5,7),
	(10,30,50,70),
	(2,4,6,8),
	(20,40,60,80)
	)
val Mb = M
// All arguments in the following are matrices.
// Cholesky decompositon (as an object of a CholeskyDecomposition class with all its operations)
val ch = chol(M)
// SVD
val (U, V, s) = svd(M)  // ????
// EigenDecomposition
val (V, d) = eigen(M)	// ????
// QR decomposition
val (Q, R) = qr(M)	// ????
// Rank Check for rank deficiency (runs rank-revealing QR)
M.isFullRank

// In-core SSVD
val (U, V, s) = ssvd(A, k=50, p=15, q=1)	// ????
// Solving linear equation systems and matrix inversion This is fully similar to R semantics. There
// are three forms of invocation:
solve(A, B) // solves AX = B
solve(A, b) // solves Ax = b
solve(A) // computes inverse A−1

// ----------------------------------------------------------------------------
// 1.6 Misc

// vector cardinality
a.length
// matrix cardinality
m.nrow
m.ncol
// means and sums
m.colSums
m.colMeans
m.rowSums
m.rowMeans
// a copy-by-value (vector or matrix )
// val b = a cloned
// 前面的b与a是通过dense直接生成的,是不可编辑的(Matrix)；而通过list生成的可编辑(DenseMatrix)
val b = M

val b1 = b		// b1是org.apache.mahout.math.DenseMatrix
val bcp = b cloned	// bcp是org.apache.mahout.math.Matrix

b(3 to 3,3 to 3)
b(3 to 3,3 to 3) = 9000009 // 不能成功

b(0 to 1,2 to 3)
b(0 to 1,2 to 3) := dense((100000003, 10000002), (200002, 2000003))
b1
bcp

b(3,1 to 3)
b(3,1 to 3) := dense(111111, 22222, 33333)	 // 不能成功
b(3,1 to 3) := dense((111111), (22222), (33333))	 // 不能成功

// 必须是slice
b(2 to 3,1 to 3) := dense((111111, 22222, 33333), (111111, 22222, 33333))	// 能够成功
b(2 to 3,1 to 3) = dense((0.0000001, 0000002, 0000003), (1.0000001, 2.0000002, 3.0000003))	// 能够成功
b(2 to 3,1 to 3) := dense((0.0000001, 0000002, 0000003), (1.0000001, 2.0000002, 3.0000003))	// 能够成功
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

  test("SSVD") {

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

// ----------------------------------------------------------------------------
// 1.8 Stochastic PCA
/**
* PCA based on SSVD that runs without forming an always-dense A-(colMeans(A)) input for SVD. This
* follows the solution outlined in MAHOUT-817. For in-core version it, for most part, is supposed
* to save some memory for sparse inputs by removing direct mean subtraction.<P>
*
* Hint: Usually one wants to use AV which is approsimately USigma, i.e.<code>u %*%: diagv(s)</code>.
* If retaining distances and orignal scaled variances not that important, the normalized PCA space
* is just U.
*
* Important: data points are considered to be rows.
*
* @param a input matrix A
* @param k request SSVD rank
* @param p oversampling parameter
* @param q number of power iterations
* @return (U,V,s)
*/
def spca(a:Matrix, k: Int, p: Int = 15, q: Int = 0)

// Stochastic PCA is a re-flow of MAHOUT-817 for in-core DSL. One usually needs output AV ≈ UΣ:
val (inCoreU, _, s) = spca(a = input, k = 30, q = 1)
val uSigma = inCoreU %*%: diagv(s)

// ----------------------------------------------------------------------------
// 1.9 Pitfalls

// This one the people who are accustomed to writing R linear algebra will probably find quite easy to fall into.
// R has a nice property, a copy-on-write, so all variables actually appear to act as no-side-effects scalar-like
// values and all assignment appear to be by value. Since scala always assigns by reference (except for AnyVal
// types which are assigned by value), it is easy to fall prey to the following side effect modifications
val m1 = m
m1 += 5.0 // modifies m as well
// A fix is as follows:
val m1 = m cloned
m1 += 5.0 // now m is intact

// ----------------------------------------------------------------------------
// 2 Out-of-core linalg bindings

// ----------------------------------------------------------------------------
// 2.1 Initializing Mahout/Spark context

// To initialize Mahout/Spark session, just create an implicit value of a specifically prepped Spark context:
implicit val mahoutCtx = mahoutSparkContext(
	masterUrl = "spark://master-hadoop:7077",	//"local",
	appName = "MahoutLocalContext"
	// [,...]
)

// ----------------------------------------------------------------------------
// 2.2 Recommended imports for Scala & Spark Bindings

// Always do for seamless in-core, out-of-core DSL:
// Import matrix, vector types, etc.
import org.apache.mahout.math._
// Import scala bindings operations
import scalabindings._
// Enable R-like dialect in scala bindings
import RLikeOps._
// Import distributed matrix apis
import drm._
// Import R-like distributed dialect
import RLikeDrmOps._
// Those are needed for Spark-specific
// operations such as context creation
import org.apache.mahout.sparkbindings._

// ----------------------------------------------------------------------------
// 2.3 DRM Persistence operators

// 2.3.1 Loading DRM off HDFS
val A = drmFromHDFS(path = hdfsPath)
// 2.3.2 Parallelizing from an in-core matrix
val inCoreA = dense((1, 2, 3), (3, 4, 5))
val A = drmParallelize(inCoreA)
// 2.3.3 Empty DRM
val A = drmParallelizeEmpty(100, 50)

// 2.3.4 Collecting to driver’s jvm in-core
val inCoreA = A.collect()  // ???? 执行失败
/*
mahout> val inCoreA = A.collect()
<console>:66: error: overloaded method value apply with alternatives:
  (rowRange: Range,col: Int)org.apache.mahout.math.Vector <and>
  (row: Int,colRange: Range)org.apache.mahout.math.Vector <and>
  (rowRange: Range,colRange: Range)org.apache.mahout.math.Matrix <and>
  (row: Int,col: Int)Double
 cannot be applied to ()
       val inCoreA = A.collect()
                       ^

*/
// 2.3.5 Collecting to HDFS
// Collect Spark-backed DRM to HDFS in Mahout’s DRM format files:3
A.writeDRM(path = hdfsPath)
A.writeDRM(path = "hdfs://master-hadoop:9000/user/hadoop/mahout-spark-shell/drm001")

val path_b = "hdfs://master-hadoop:9000/user/hadoop/mahout-spark-shell/b"
val path_bt = "hdfs://master-hadoop:9000/user/hadoop/mahout-spark-shell/bt"
drmParallelize(b).writeDRM(path = path_b)
drmParallelize(bt).writeDRM(path = path_bt)

val hdfs_b = drmFromHDFS(path = path_b)
val hdfs_bt = drmFromHDFS(path = path_bt)
// ----------------------------------------------------------------------------
// 2.4 Logical algebraic operators on DRM matrices

// Optimizer actions.
// ----------------example 01----------------
val A = drmParallelize (...)
val B = drmParallelize (...)
val C = A %*% B.t
val D = C.t
val E = C.t %*% C
D.writeDRM(..path..)
E.writeDRM(..path..)

// ----------------example 02----------------
val A = drmParallelize (...)
val B = drmParallelize (...)
val C = (A %*% B.t).checkpoint
val D = C.t
val E = C.t %*% C
D.writeDRM(..path..)
E.writeDRM(..path..)

// Computational actions.
E.writeDrm(path)

// Caching in Spark’s Block Manager.
val drmA = (/*..drm expression..*/).checkpoint(CacheHint.MEMORY_AND_DISK)
// ... some computational actions involving drmA
// ... drmA is not needed anymore
drmA.uncache()

// ----------------------------------------------------------------------------
// 2.4.1 Transposition
A.t

// ----------------------------------------------------------------------------
// 2.4.2 Elementwise +, -, *, /
M = A + B
M = A − B
M = A ◦ B (Hadamard)

A + B
A - B
A * B
A / B

// Binary operators involving in-core argument (only on int-keyed DRMs)
A + inCoreB
A - inCoreB
A * inCoreB
A / inCoreB
A :+ inCoreB
A :- inCoreB
A :* inCoreB
A :/ inCoreB
inCoreA +: B
inCoreA -: B
inCoreA *: B
inCoreA /: B

// ----------------------------------------------------------------------------
// 2.4.3 Matrix-matrix multiplication %*%
// M = AB
A %*% B
A %*% inCoreB
A %*% inCoreDiagonal (i.e. things like A %*% diagv(d))
A :%*% inCoreB
inCoreA %*%: B

// ----------------------------------------------------------------------------
// 2.4.4 Matrix-vector multiplication %*%
val Ax = A %*% x
val inCoreX = Ax.collect(::,0)

// ----------------------------------------------------------------------------
// 2.4.5 Matrix-scalar +,-,*,/

// In this context, matrix-scalar operations mean element-wise operatios of every matrix element and a scalar.
A + 5.0
A - 5.0
A :- 5.0
5.0 -: A
A * 5.0
A / 5.0
5.0 /: A
// Note that 5.0 -: A means mij = 5 − aij and 5.0 /: A means mij = 5/aij
// for all elements of the result.

// ----------------------------------------------------------------------------
// 2.5 Slicing

// General slice
A(100 to 200, 100 to 200)
// Horizontal block
A(::, 100 to 200)
// Vertical block
A(100 to 200, ::)

// ----------------------------------------------------------------------------
// 2.6 Stitching (cbind)

// We can stitch two matrices side by side (cbind R semantics) :
val drmAnextToB = drmA cbind drmB
// Or, which is the same in Scala,
val drmAnextToB = drmA.cbind(drmB)
// Obviously, our variation of cbind is accepting only two arguments, but we can stitch more matrices by
// chaining the operation

// ----------------------------------------------------------------------------
// 2.7 Custom pipelines on blocks

// Here is definition for DRM block tuple type:
/** Drm block-wise tuple:
Array of row keys and the matrix block. */
type BlockifiedDrmTuple[K] = (Array[K], _ <: Matrix)

// Here is unit test that demonstrates use of mapBlock operator by producing A + 1.0:
val inCoreA = dense((1, 2, 3), (2, 3, 4), (3, 4, 5), (4, 5, 6))
val A = drmParallelize(m = inCoreA, numPartitions = 2)
val B = A.mapBlock(/* Inherit width */) {
	case (keys, block) => keys -> (block += 1.0)
}
val inCoreB = B.collect
val inCoreBControl = inCoreA + 1.0
println(inCoreB)
// Assert they are the same
(inCoreB - inCoreBControl).norm should be < 1E-10

// ----------------------------------------------------------------------------
// 2.8 Doing something completely custom

// The row-wise tuple types and RDDs are defined as following:
/** Drm row-wise tuple */
type DrmTuple[K] = (K, Vector)
/** Row-wise organized DRM rdd type */
type DrmRdd[K] = RDD[DrmTuple[K]]

// (type Vector here is org.apache.mahout.math.Vector).
// E.g.:
val myRdd = (A %*% B).checkpoint().rdd

// Similarly, an Rdd conforming to a type of DrmRdd, can be re-wrapped into optimizer checkpoint via
val rdd:DrmRdd[K] = ... //
val A = drmWrap(rdd = rdd, nrow = 50000, ncol = 100)
... // use A in a DRM pipeline

// ----------------------------------------------------------------------------
// 2.9 Broadcasting vectors and matrices to closures.

val factor:Int = ...
val drm2 = drm1.mapBlock() {
	case (keys, block) => block *= factor
	keys -> block
}

// E.g. the following fragment,
// implementing a vector subtraction from every matrix row, will fail with NotSerializableException:
val v:Vector = ...
val drm2 = drm1.mapBlock() {
	case (keys, block) =>
	for (row <- 0 until block.nrow) block(row,::) -= v
	keys -> block
}

// The fix to the previous fragment would be:
val v:Vector = ...
val bcastV = drmBroadcast(v)
val drm2 = drm1.mapBlock() {
	case (keys, block) =>
	for (row <- 0 until block.nrow) block(row,::) -= bcastV
	keys -> block
}
// ----------------------------------------------------------------------------
// 2.10 Computations providing ad-hoc summaries

// 2.10.1 nrow, ncol
// 2.10.2 colSums, colMeans
val acs = A.colSums
val amean = A.colMeans

// 2.10.3 rowMeans, rowSums
// 2.10.4 Matrix norm
val rmse = (A - U %*% V.t).norm / sqrt(A.nrow * A.ncol)


// ----------------------------------------------------------------------------
// 2.11 Distributed Decompositions

// Therefore, A and Q subsequently can
// be trivially zipped together if join of rows is needed (used in d-ssvd).
val (drmQ, inCoreR) = dqrThin(drmA)

	// The source of this method as of the time of this writing is extremely simple (probably too simple):
	def dqrThin[K: ClassTag](A: DrmLike[K], checkRankDeficiency: Boolean = true):
		(DrmLike[K], Matrix) = {
		if (A.ncol > 5000)
			log.warn("A is too fat. A’A must fit in memory and easily broadcasted.")

		implicit val ctx = A.context

		val AtA = (A.t %*% A).checkpoint()
		val inCoreAtA = AtA.collect

		if (log.isDebugEnabled) log.debug("A’A=\n%s\n".format(inCoreAtA))

		val ch = chol(inCoreAtA)
		val inCoreR = (ch.getL cloned) t

		if (log.isDebugEnabled) log.debug("R=\n%s\n".format(inCoreR))
		if (checkRankDeficiency && !ch.isPositiveDefinite)
			throw new IllegalArgumentException("R is rank-deficient.")

		val bcastAtA = drmBroadcast(inCoreAtA)

		// Unfortunately, I don’t think Cholesky decomposition is serializable to backend. So we re-
		// decompose A’A in the backend again.

		// Compute Q = A*inv(L’) -- we can do it blockwise.
		val Q = A.mapBlock() {
			case (keys, block) => keys -> chol(bcastAtA).solveRight(block)
	}

	Q -> inCoreR
}
// Since we see that is navigated twice, it is recommended that it is checkpointed before calling this method to
// avoid recomputation.

// ----------------------------------------------------------------------------
// 2.11.2 Distributed Stochastic SVD

// Usage example:
val (drmU, drmV, s) = dssvd(drmA, k = 40, q = 1)

Source (for those who likes counting lines):
/**
* Distributed Stochastic Singular Value decomposition algorithm.
*
* @param A input matrix A
* @param k request SSVD rank
* @param p oversampling parameter
* @param q number of power iterations
* @return (U,V,s). Note that U, V are non-checkpointed matrices
(i.e. one needs to actually use them
* e.g. save them to hdfs in order to trigger their computation.
*/
def dssvd[K: ClassTag](A: DrmLike[K], k: Int, p: Int = 15, q: Int = 0):
	(DrmLike[K], DrmLike[Int], Vector) = {

	val drmA = A.checkpoint()
	val m = drmA.nrow
	val n = drmA.ncol
	assert(k <= (m min n), "k cannot be greater than smaller of m, n.")
	val pfxed = safeToNonNegInt((m min n) - k min p)

	// Actual decomposition rank
	val r = k + pfxed

	// We represent Omega by its seed.
	val omegaSeed = Random.nextInt()

	// Compute Y = A*Omega.
	var drmY = drmA.mapBlock(ncol = r) {
		case (keys, blockA) =>
			val blockY = blockA %*% Matrices.symmetricUniformView(blockA.ncol, r, omegaSeed)
			keys -> blockY
	}

	var drmQ = dqrThin(drmY.checkpoint())._1
	if (q==0) drmQ = drmQ.checkpoint()
	// This actually is optimized as identically
	// partitioned map-side A’B since A and Q should
	// still be identically partitioned.
	var drmBt = drmA.t %*% drmQ

	// Checkpoint B’ if last iteration
	if (q==0) drmBt = drmBt.checkpoint()

	for (i <- 1 to q) {
		drmY = drmA %*% drmBt
		drmQ = dqrThin(drmY.checkpoint())._1

		// Checkpoint Q if last iteration
		if ( i == q) drmQ = drmQ.checkpoint()

		drmBt = drmA.t %*% drmQ

		// Checkpoint B’ if last iteration
		if ( i == q) drmBt = drmBt.checkpoint()
	}

	val inCoreBBt = (drmBt.t %*% drmBt).checkpoint(StorageLevel.NONE).collect
	val (inCoreUHat, d) = eigen(inCoreBBt)
	val s = d.sqrt
	val drmU = drmQ %*% inCoreUHat
	val drmV = drmBt %*% (inCoreUHat %*%: diagv(1 /: s))
	(drmU(::, 0 until k), drmV(::, 0 until k), s(0 until k))
}

// ----------------------------------------------------------------------------
// 2.11.3 Distributed Stochastic PCA
/**
* Distributed Stochastic PCA decomposition algorithm. A logical reflow of the "SSVD-PCA options.pdf"
* document of the MAHOUT-817.
*
* @param A input matrix A
* @param k request SSVD rank
* @param p oversampling parameter
* @param q number of power iterations (hint: use either 0 or 1)
* @return (U,V,s). Note that U, V are non-checkpointed matrices (i.e. one needs to actually use them
* e.g. save them to hdfs in order to trigger their computation.
*/
def dspca[K: ClassTag](A: DrmLike[K], k: Int, p: Int = 15, q: Int = 0):
(DrmLike[K], DrmLike[Int], Vector) = <...>

// Stochastic PCA is a re-flow of MAHOUT-817 for sparkbindings algebra. One usually needs output AV ≈ UΣ:
val (drmU, _, s) = dspca(a = drmA, k = 30, q = 1)
val drmUSigma = drmU %*% diagv(s)

// ----------------------------------------------------------------------------
// 2.12 Adjusting parallelism of computations

// Second, parallelism operators could be inserted into expressions.
drmA.par(min = 100)
// will establish minimum parallelism for ougoing computations of drmA.
drmA.par(exact = 100)
// will establish exact parallelism; and
drmA.par(auto = true)

// ----------------------------------------------------------------------------
// 2.13 Mahout shell for Spark

// Use something like the following to run Mahout Spark shell:
MASTER=<spark-master-url> mahout/bin spark-shell
// ----------------------------------------------------------------------------
// 

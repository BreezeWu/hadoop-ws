// ----------------------------------------------------------------------------
// MLlib - Basics
//	http://spark.apache.org/docs/latest/mllib-basics.html


// Converting Array[Double] to Vector is straightforward:
import org.apache.spark.mllib.linalg.{Vector, Vectors}
val array: Array[Double] = ... // a double array
val vector: Vector = Vectors.dense(array) // a dense vector

// ----------------------------------------------------------------------------
// Local vector
//	A local vector has integer-typed and 0-based indices and double-typed values, stored on a single machine.

// 	Scala imports scala.collection.immutable.Vector by default, so you have to import org.apache.spark.mllib.linalg.Vector explicitly to use MLlib’s Vector.

// The base class of local vectors is Vector, and we provide two implementations: DenseVector and SparseVector. 
// We recommend using the factory methods implemented in Vectors to create local vectors.
import org.apache.spark.mllib.linalg.{Vector, Vectors}

// Create a dense vector (1.0, 0.0, 3.0).
val dv: Vector = Vectors.dense(1.0, 0.0, 3.0)
// Create a sparse vector (1.0, 0.0, 3.0) by specifying its indices and values corresponding to nonzero entries.
val sv1: Vector = Vectors.sparse(3, Array(0, 2), Array(1.0, 3.0))
// Create a sparse vector (1.0, 0.0, 3.0) by specifying its nonzero entries.
val sv2: Vector = Vectors.sparse(3, Seq((0, 1.0), (2, 3.0)))

// ----------------------------------------------------------------------------
// Labeled point
//	A labeled point is a local vector, either dense or sparse, associated with a label/response. 

/*
A labeled point is a local vector, either dense or sparse, associated with a label/response. In MLlib, labeled points are used in supervised learning algorithms. We use a double to store a label, so we can use labeled points in both regression and classification. For binary classification, label should be either 0 (negative) or 1 (positive). For multiclass classification, labels should be class indices staring from zero: 0,1,2,….
*/

// A labeled point is represented by the case class LabeledPoint.
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.regression.LabeledPoint

// ---------------- Dense data ----------------

// Create a labeled point with a positive label and a dense feature vector.
val pos = LabeledPoint(1.0, Vectors.dense(1.0, 0.0, 3.0))

// Create a labeled point with a negative label and a sparse feature vector.
val neg = LabeledPoint(0.0, Vectors.sparse(3, Array(0, 2), Array(1.0, 3.0)))

// ---------------- Sparse data ----------------

// . Each line represents a labeled sparse feature vector using the following format:
//	label index1:value1 index2:value2 ...
// where the indices are one-based and in ascending order. After loading, the feature indices are converted to zero-based.

// MLUtils.loadLibSVMFile reads training examples stored in LIBSVM format.
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.mllib.util.MLUtils
import org.apache.spark.rdd.RDD

val examples: RDD[LabeledPoint] = MLUtils.loadLibSVMFile(sc, "mllib/data/sample_libsvm_data.txt")

// ----------------------------------------------------------------------------
// Local matrix
//	A local matrix has integer-typed row and column indices and double-typed values, stored on a single machine. 

// ---------------- dense matrix ----------------
// MLlib supports dense matrix, whose entry values are stored in a single double array in column major. 
// For example, the following matrix
//	⎛⎝⎜⎜1.03.05.02.04.06.0⎞⎠⎟⎟
//	is stored in a one-dimensional array [1.0, 3.0, 5.0, 2.0, 4.0, 6.0] with the matrix size (3, 2). 

// The base class of local matrices is Matrix, and we provide one implementation: DenseMatrix. Sparse matrix will be added in the next release. 

import org.apache.spark.mllib.linalg.{Matrix, Matrices}

// Create a dense matrix ((1.0, 2.0), (3.0, 4.0), (5.0, 6.0))
val dm: Matrix = Matrices.dense(3, 2, Array(1.0, 3.0, 5.0, 2.0, 4.0, 6.0))

// ---------------- sparse matrix ----------------
// We are going to add sparse matrix in the next release.

// ----------------------------------------------------------------------------
// Distributed matrix
//	A distributed matrix has long-typed row and column indices and double-typed values, stored distributively in one or more RDDs. 
// Note: 
//	The underlying RDDs of a distributed matrix must be deterministic, because we cache the matrix size. It is always error-prone to have non-deterministic RDDs.

// We implemented three types of distributed matrices in this release and will add more types in the future.

// ---------------- 1.RowMatrix ----------------
// The basic type is called RowMatrix. A RowMatrix is a row-oriented distributed matrix without meaningful row indices, e.g., a collection of feature vectors. It is backed by an RDD of its rows, where each row is a local vector. We assume that the number of columns is not huge for a RowMatrix.

//  This is similar to data matrix in the context of multivariate statistics. Since each row is represented by a local vector, the number of columns is limited by the integer range but it should be much smaller in practice.

import org.apache.spark.mllib.linalg.Vector
import org.apache.spark.mllib.linalg.distributed.RowMatrix

// 从hive取数据
val hiveContext = new org.apache.spark.sql.hive.HiveContext(sc)
val rddFromHive = hiveContext.hql("SELECT cons_id, ym, volume_per_month FROM bigdata_arc_volume_perm_s01 WHERE (cons_id = 25280011 OR cons_id = 25257000) order by cons_id")
val rddFromHive = hiveContext.hql("SELECT cons_id, ym, volume_per_month FROM bigdata_arc_volume_perm_s01")

/*
scala> rddFromHive.printSchema()
root
 |-- cons_id: IntegerType
 |-- ym: StringType
 |-- volume_per_month: IntegerType

*/

// 转换数据格式
/*
	rddFromHive.map(x => Vectors.dense(x))	// 由于第二个字段是String,不成功!
	val vector: Vector = rddFromHive.map(x => Vectors.dense(x._1, x._2.toDouble, x._3))  // row不是tuple,不能这么选取
	rddFromHive.map(x => x(0).toDouble)  // error: value toDouble is not a member of Any
*/
rddFromHive.map(x => x(0).toString) 	// 这个可以成功
rddFromHive.map(x => x(0).toString.toDouble) 	// 这个也可以成功
rddFromHive.map(x => Array(x(0).toString.toDouble,x(1).toString.toDouble,x(2).toString.toDouble)) 	// 这个也可以成功

// 所以,转换为vector的方法是
val rows = rddFromHive.map(x => Array(
		x(0).toString.toDouble,x(1).toString.toDouble,x(2).toString.toDouble)
	).map(x => Vectors.dense(x))
/*
val array: Array[Double] = ... // a double array
val vector: Vector = Vectors.dense(array) // a dense vector
//val rows: RDD[Vector] = ... // an RDD of local vectors
*/

// Create a RowMatrix from an RDD[Vector].
val mat: RowMatrix = new RowMatrix(rows)

// Get its size.
val m = mat.numRows()
val n = mat.numCols()

// =========== Multivariate summary statistics =============
// We provide column summary statistics for RowMatrix. 
// If the number of columns is not large, say, smaller than 3000, you can also compute the covariance matrix as a local matrix, which requires (n2) storage where n is the number of columns. 

import org.apache.spark.mllib.linalg.Matrix
import org.apache.spark.mllib.linalg.distributed.RowMatrix
import org.apache.spark.mllib.stat.MultivariateStatisticalSummary

// val mat: RowMatrix = ... // a RowMatrix

// Compute column summary statistics.
val summary: MultivariateStatisticalSummary = mat.computeColumnSummaryStatistics()
println(summary.mean) // a dense vector containing the mean value for each column
println(summary.variance) // column-wise variance
println(summary.numNonzeros) // number of nonzeros in each column

// Compute the covariance matrix.
val cov: Matrix = mat.computeCovariance()

// ---------------- 2.IndexedRowMatrix ----------------
// An IndexedRowMatrix is similar to a RowMatrix but with row indices, which can be used for identifying rows and joins.

// An IndexedRowMatrix is similar to a RowMatrix but with meaningful row indices. It is backed by an RDD of indexed rows, which each row is represented by its index (long-typed) and a local vector.

// An IndexedRowMatrix can be created from an RDD[IndexedRow] instance, where IndexedRow is a wrapper over (Long, Vector). An IndexedRowMatrix can be converted to a RowMatrix by dropping its row indices.

import org.apache.spark.mllib.linalg.distributed.{IndexedRow, IndexedRowMatrix, RowMatrix}

//val rows: RDD[IndexedRow] = ... // an RDD of indexed rows
// Create an IndexedRowMatrix from an RDD[IndexedRow].
val mat: IndexedRowMatrix = new IndexedRowMatrix(rows)

// Get its size.
val m = mat.numRows()
val n = mat.numCols()

// Drop its row indices.
val rowMat: RowMatrix = mat.toRowMatrix()  // error: value toRowMatrix is not a member of org.apache.spark.mllib.linalg.distributed.RowMatrix

// ---------------- 3.CoordinateMatrix ----------------
// A CoordinateMatrix is a distributed matrix stored in coordinate list (COO) format, backed by an RDD of its entries.

// A CoordinateMatrix is a distributed matrix backed by an RDD of its entries. Each entry is a tuple of (i: Long, j: Long, value: Double), where i is the row index, j is the column index, and value is the entry value. 
// A CoordinateMatrix should be used only in the case when both dimensions of the matrix are huge and the matrix is very sparse.

import org.apache.spark.mllib.linalg.distributed.{CoordinateMatrix, MatrixEntry}

val entries: RDD[MatrixEntry] = ... // an RDD of matrix entries
// Create a CoordinateMatrix from an RDD[MatrixEntry].
val mat: CoordinateMatrix = new CoordinateMatrix(entries)

// Get its size.
val m = mat.numRows()
val n = mat.numCols()

// Convert it to an IndexRowMatrix whose rows are sparse vectors.
val indexedRowMatrix = mat.toIndexedRowMatrix()



















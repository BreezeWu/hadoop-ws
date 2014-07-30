
// ----------------------------------------------------------------------------
// Dimension reduction?+ k-means

// compute principal components 
val points: RDD[Vector] = ... 
val mat = RowRDDMatrix(points) 
val pc = mat.computePrincipalComponents(20) 

// project points to a low-dimensional space 
val projected = mat.multiply(pc).rows 

// train a k-means model on the projected data 
val model = KMeans.train(projected, 10)


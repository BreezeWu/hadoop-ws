val fruit = "apples" :: ("oranges" :: ("pears" :: Nil))
val nums = 1 :: (2 :: (3 :: (4 :: Nil)))

val diag3 = (1 :: (0 :: (0 :: Nil))) ::
(0 :: (1 :: (0 :: Nil))) ::
(0 :: (0 :: (1 :: Nil))) :: Nil
val empty = Nil
// ----------------------------------------------------------------------------
def insert(x: Int, xs: List[Int]): List[Int] = xs match {
	case List() => List(x)
	case y :: ys => if (x <= y) x :: xs else y :: insert(x, ys)
}

// 普通版本
def isort2(xs: List[Int]): List[Int] =
	if (xs.isEmpty) Nil
	else insert(xs.head, isort2(xs.tail))

// pattern match版本
def isort(xs: List[Int]): List[Int] = xs match {
	case List() => List()
	case x :: xs1 => insert(x, isort(xs1))
}

// 应用
val nums = 4 :: (3 :: (2 :: (1 :: Nil)))
nums
isort2(nums)
isort(nums)

// ----------------------------------------------------------------------------
// ----------------------------------------------------------------------------
// ----------------------------------------------------------------------------

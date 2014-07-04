
/*
abstract class List[A] { ...
def map[B](f: A => B): List[B] = this match {
	case Nil => this
	case x :: xs => f(x) :: xs.map(f)
}
*/
def squareList1(xs: List[Int]): List[Int] = xs match {
	case List() => Nil
	case y :: ys => y*y :: squareList1(ys)
}
def squareList(xs: List[Int]): List[Int] =
	xs map (x => x*x)

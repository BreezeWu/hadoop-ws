abstract class IntTree
case object EmptyTree extends IntTree
case class Node(elem: Int, left: IntTree, right: IntTree) extends IntTree

// Complete the following implementations of function contains and insert for
IntTree’s.
def contains(t: IntTree, v: Int): Boolean = t match { 
	case EmptyTree => false
	case Node => {if (v < elem) left contains v
		else if (v > elem) right contains v
		else true}
	case _ => println("no matching!")
}

def insert(t: IntTree, v: Int): IntTree = t match {
	case EmptyTree => new NonEmptySet(v, new EmptySet, new EmptySet)
	case Node => {
			if (v < elem) new NonEmptySet(elem, left insert v, right)
			else if (v > elem) new NonEmptySet(elem, left, right insert v)
			else this
		}
	case _ => println("no matching!")
}

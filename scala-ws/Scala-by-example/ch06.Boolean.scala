package scala

abstract class Boolean {
	def ifThenElse(thenpart: => Boolean, elsepart: => Boolean)
	def && (x: => Boolean): Boolean = ifThenElse(x, false)
	def || (x: => Boolean): Boolean = ifThenElse(true, x)
	def ! : Boolean = ifThenElse(false, true)
	def == (x: Boolean) : Boolean = ifThenElse(x, x.!)
	def != (x: Boolean) : Boolean = ifThenElse(x.!, x)
	def < (x: Boolean) : Boolean = ifThenElse(false, x)
	def > (x: Boolean) : Boolean = ifThenElse(x.!, false)
	def <= (x: Boolean) : Boolean = ifThenElse(x, true)
	def >= (x: Boolean) : Boolean = ifThenElse(true, x.!)
}

case object True extends Boolean {
	def ifThenElse(t: => Boolean, e: => Boolean) = t
}

case object False extends Boolean {
	def ifThenElse(t: => Boolean, e: => Boolean) = e
}

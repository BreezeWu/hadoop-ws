// ----------------------------------------------------------------------------
// 6 Composition

// 1.Class Linearization
// 2.Membership
// 3.Super calls
// 4.Service-Oriented Component Model

// ------------------------------------
// 1.Class Linearization
trait AbsIterator[T] {
    def hasNext: Boolean
    def next: T
 }
 // Note the use of the keyword trait instead of class. A trait is a special form of an abstract class which does not have any value parameters for its constructor. Traits can be used in all contexts where other abstract classes appear; however only traits can be used as mixins (see below).
 
trait  RichIterator[T]  extends  AbsIterator[T]  {
	def  foreach(f:  T  =>  Unit):  Unit  =
		while  (hasNext)  f(next)
}

class  StringIterator(s:  String)  extends  AbsIterator[Char]  {
	private  var  i  =  0
	def  hasNext  =  i  <  s.length
	def  next  =  {  val  x  =  s  charAt  i;  i  =  i  +  1;  x  }
}

object  Test  {
	def  main(args:  Array[String]):  Unit  =  {
		class  Iter  extends  StringIterator(args(0)) with  RichIterator[Char]
		val  iter  =  new  Iter
		iter  foreach  System.out.println
	}
}

// 测试代码
Test.main(Array("这是第一个字符串"))
Test.main(Array("FirstArgs:This is a string", "Seconde Args"))

// The rst parent is called the superclass of Iter, whereas the second parent is called a mixin.
// ------------------------------------
// 2.Membership

// Member  denitions  of  a  class  fall  into  two  categories: concrete and abstract.  There are two rules that determine the set of members of a class, one for each category:


// Concrete members of a class C are all concrete denitions M  in C  and its base classes,  except if there is already a concrete  denition  of  a  matching  member  in  a  preceding (wrt L (c)) base class.
// Abstract members of a class C are all abstract denitions M in C and its base classes, except if C has a already concrete denition of a matching member or there is already an abstract denition of a matching member in in a preceding base class.
// ------------------------------------
// 3.Super calls

trait  SyncIterator[T]  extends  AbsIterator[T]  {
	abstract  override  def  hasNext:  Boolean  =
	synchronized(super.hasNext)
	abstract  override  def  next:  T  =
	synchronized(super.next)
}

val iter1 = new {class  Iter1  extends  StringIterator("someString Iter1")  with  RichIterator[Char] with  SyncIterator[Char]}
// iter1: AnyRef{type Iter <: StringIterator with RichIterator[Char] with SyncIterator[Char]} = $anon$1@4eb1c7e6
val iter2 = new {class  Iter2  extends  StringIterator("someString  Iter2")  with  SyncIterator[Char] with  RichIterator[Char]}

//
class  Iter1  extends  StringIterator("someString Iter1")  with  RichIterator[Char] with  SyncIterator[Char]
//class  Iter2  extends  StringIterator("someString  Iter2")  with  SyncIterator[Char] with  RichIterator[Char]
val iter1 = new Iter1
//val iter2 = new Iter2

// Because  RichIterator  and  StringIterator  dene  different sets of members, the order in which they appear in a mixin composition does not matter.  In the example above, we could have equivalently written
class  Iter2  extends  StringIterator("someString  Iter2")  with  SyncIterator[Char] with  RichIterator[Char]
val iter2 = new Iter2


trait LoggedIterator[T] extends AbsIterator[T] {
	abstract override def next: T = {
		val x = super.next; System.out.println(x); x
	}
}

trait  SyncIterator[T]  extends  AbsIterator[T]  {
	abstract  override  def  hasNext:  Boolean  = { System.out.println("@SyncIterator: hasNext");
	synchronized(super.hasNext) }
	abstract  override  def  next:  T  = {
	synchronized(super.next) }
}

class Iter2 extends StringIterator(someString) with SyncIterator[Char] with LoggedIterator[Char]
class Iter2 extends StringIterator(someString) with LoggedIterator[Char] with SyncIterator[Char]
// ------------------------------------
// 4.Service-Oriented Component Model
// ------------------------------------

// ------------------------------------
// ------------------------------------                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           
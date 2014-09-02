// ----------------------------------------------------------------------------
// 6 Composition

trait AbsIterator[T] {
    def hasNext: boolean
    def next: T
 }
 // Note the use of the keyword trait instead of class. A trait is a special form of an abstract class which does not have any value parameters for its constructor. Traits can be used in all contexts where other abstract classes appear; however only traits can be used as mixins (see below).
 
 

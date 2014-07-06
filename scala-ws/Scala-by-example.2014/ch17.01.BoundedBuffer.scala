// As an example of howmonitors are used, here is is an implementation of a bounded buffer class.
import scala.Array
class BoundedBuffer[A](N: Int) {
	//val in ,out,n = 0

	var in = 0
	val out =0 
	val n =0 

	val elems = new Array[A](N)

	def put(x: A) = synchronized {
		while (n >= N) wait()

		elems(in) = x ; in = (in + 1) % N ; n = n + 1
		if (n == 1) notifyAll()
	}

	def get: A = synchronized {
		while (n == 0) wait()

		val x = elems(out) ; out = (out + 1) % N ; n = n - 1
		if (n == N - 1) notifyAll()
		x
	}
}

//  应用
import scala.concurrent.ops._

val buf = new BoundedBuffer[String](10)
spawn { while (true) { val s = produceString ; buf.put(s) } }
spawn { while (true) { val s = buf.get ; consumeString(s) } }

// The spawn method spawns a new thread which executes the expression given in the
// 	parameter. It is deﬁned in object concurrent.ops as follows.
def spawn(p: => Unit) {
	val t = new Thread() { override def run() = p }
	t.start()
}
//}

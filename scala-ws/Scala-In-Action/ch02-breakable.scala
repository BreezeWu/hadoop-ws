// Run with >scala breakable.scala  or
// load into the REPL in chap02/ via
// scala> :load breakable.scala

val breakException = new RuntimeException("break exception")
def breakable(op: => Unit) {
  try {
    op
  } catch { case _ => println("捕捉到了异常!")}
}

def break = throw breakException

// ---------------------------------------------------
def install = {
  val env = System.getenv("SCALA_HOME")
  if(env == null) break
  println("[install function]\t\tfound scala home lets do the real work")
}

// ---------------------------------------------------
// 将一个函数传递给breakable函数
//passing the method name
//breakable(install) 

/*// --------环境A
// 在环境变量中设置SCALA_HOME
// export SCALA_HOME=随便什么值啊
breakable(install)	// 会打印出 "found scala home lets do the real work"
install				// 会打印出 "found scala home lets do the real work"
*/

// --------环境B
// 取消环境变量中的SCALA_HOME
// unset SCALA_HOME
breakable(install)	// 打印出异常捕捉的打印结果,程序继续运行.
install		// 系统抛出java.lang.RuntimeException: break exception, 并且,后面的语句不再执行!

// ---------------------------------------------------
// 不定义有名函数(install是有名称的),则会变成闭包这个说法
//as a closure : there has a function without function signature.
// inline the install function and pass it as a closure

breakable {
  val env = System.getenv("SCALA_HOME")
  if(env == null) break
  println("[inline function of breakable]\tfound scala home lets do the real work")
}

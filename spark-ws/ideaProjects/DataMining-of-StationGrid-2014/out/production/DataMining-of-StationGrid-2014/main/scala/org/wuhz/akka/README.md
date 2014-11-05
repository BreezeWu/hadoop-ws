DISPATCHER AND A MAILBOX
======================
当我们创建了ActorSystem 和ActorRef,Dispatcher和MailBox也将会创建。

1、MailBox
　　 每个Actor都有一个MailBox（后面我们将看到一个特殊情况）。
在我们之前的模型中，每个Teacher也有一个MailBox。Teacher需要检查MailBox并处理其中的message。
MailBox中有个队列并以FIFO方式储存和处理消息。

2、Dispatcher
　　 Dispatcher做一些很有趣的事。
Dispatcher好像只是仅仅将message从ActorRef 传递到MailBox中。
但是在这背后有件很奇怪的事：Dispatcher 包装了一个 ExecutorService (ForkJoinPool 或者 ThreadPoolExecutor).
它通过ExecutorService运行 MailBox。代码片段如下：
protected[akka] override def registerForExecution(mbox: Mailbox, ...): Boolean = {
...
try {
    executorService execute mbox
...
}

 什么？你说是你来运行Mailbox？是的，我们前面已经看到Mailbox的队列中持有所有的消息。
 用executorService 运行Mailbox也一样。Mailbox必须是一个线程。
 代码中有大量的Mailbox的声明和构造函数，代码片段如下：
private[akka] abstract class Mailbox(val messageQueue: MessageQueue)
     extends SystemMessageQueue with Runnable

Teacher Actor
===============
 当MailBox的run方法被运行，它将从队列中取出消息，并传递到Actor进行处理。
 该方法最终在你将消息tell到ActorRef 中的时候被调用，在目标Actor其实是个receive 方法。
 TeacherActor 是基本的类，并且拥有一系列的quote，很明显，receive 方法是用来处理消息的。
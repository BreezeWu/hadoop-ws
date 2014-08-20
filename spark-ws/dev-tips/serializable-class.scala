// ----------------------------------------------------------------------------
// java.io.NotSerializableException Of dependent Java lib.
// http://apache-spark-user-list.1001560.n3.nabble.com/java-io-NotSerializableException-Of-dependent-Java-lib-td1973.html

/*
We now have a method to work this around. 

For the classes that can't easily implement serialized, we wrap this class a scala object. 
For example: 
*/
     class A {}   // This class is not serializable, 
     object AHolder { 
          private val a: A = new A() 
          def get: A = a 
    } 

/*
This works cause spark will serialize the instance that are used in the closure. 
But the scala object won't be serialized and shipped. It's loaded locally in each worker JVM.
*/


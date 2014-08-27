// ----------------------------------------------------------------------------
// A Java-Like Language

// Java
class PrintOptions {
    public static void main(String[] args) {
        System.out.println("Options selected:")
        for (int i = 0; i < args.length; i++)
            if (args[i].startsWith("-"))
                System.out.println(" "+args[i].substring(1))
    }
}

// Scala
object PrintOptions {
    def main(args: Array[String]): Unit = {
        System.out.println("Options selected:")
        for (arg <- args)
            if (arg.startsWith("-"))
                System.out.println(" "+arg.substring(1))
    }
}

//  Scala does not have special syntax for array types and array accesses. 
// An array with elements of type T is written Array[T]. Here, Array is a standard class and [T] is a type parameter. 
// In fact, arrays in Scala inherit from functions. This is why array accesses are written like function applications a(i), instead of Java's a[i].

// The return type of main is written unit whereas Java uses void. 
// This stems from the fact that there is no distinction in Scala between *statements* and * expressions* .
// Every function returns a value. If the function's right hand side is a block, the evaluation of its last expression is returned as result. The result might be the trivial value {} whose type is unit. 
// Familar control constructs such as if-then-else are also generalized to expressions.

// It also accesses the static out field of the Java class System, and invokes its (overloaded) println method.
// This is possible even though Scala does not have a concept of static class members. In fact, every Java class is seen in Scala as two entities, a class containing all dynamic members and a singleton object, containing all static members.
// Hence, System.out is accessible in Scala as a member of the object System.


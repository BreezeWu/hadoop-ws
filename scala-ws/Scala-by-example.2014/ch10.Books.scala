
case class Book(title: String, authors: List[String])

val books: List[Book] = List(
	Book("Structure and Interpretation of Computer Programs",
		List("Abelson, Harold", "Sussman, Gerald J."))

	,Book("Principles of Compiler Design",
		List("Aho, Alfred", "Ullman, Jeffrey"))
		
	,Book("Programming in Modula-2",
		List("Wirth, Niklaus"))

	,Book("Introduction to Functional Programming",
		List("Bird, Richard"))

	,Book("The Java Language Specification",
		List("Gosling, James", "Joy, Bill", "Steele, Guy", "Bracha, Gilad"))
	)

// Then, to find the titles of all books whose author’s last name is “Ullman”:
for (b <- books; a <- b.authors if a startsWith "Ullman")
yield b.title

//Or, to find the titles of all books that have the string “Program” in their title:
for (b <- books if (b.title indexOf "Program") >= 0)
yield b.title

// Or, to find the names of all authors that have written at least two books in the database.
for (b1 <- books; b2 <- books if b1 != b2;
a1 <- b1.authors; a2 <- b2.authors if a1 == a2)
yield a1

//
def removeDuplicates[A](xs: List[A]): List[A] =
if (xs.isEmpty) xs 
else xs.head :: removeDuplicates(xs.tail filter (x => x != xs.head))
// 上面一行等价于： xs.head :: removeDuplicates(for (x <- xs.tail if x != xs.head) yield x)
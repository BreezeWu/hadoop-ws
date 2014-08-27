// ----------------------------------------------------------------------------
// 1 Introduction

// Ideally, software should be assembled from libraries of pre-written components,  just as hardware is assembled from pre-fabricated chips. 
// In reality, large parts of software applications are written from scratch,  so that software production is still more a craft than an industry.

// Components can take many forms; they can be modules, classes, libraries, frameworks, processes, or web services.
// Their size might range from a couple of lines to hundreds of thousands of lines. They might be linked with other components by a variety of mechanisms, such as aggregation, parameterization, inheritance, remote invocation, or message passing.

// There are two hypotheses that we would like to validate with the Scala experiment. 
// First, we postulate that a programming language for component software needs to be scalable in the sense that the same concepts can describe small as well as large parts. Therefore, we concentrate on mechanisms for abstraction, composition, and decomposition rather than adding a large set of primitives which might be useful for components at some level of scale, but not at other lev-els. 
// Second, we postulate that scalable support for components can be provided by a programming language which unies and generalizes object-oriented and functional programming. For statically typed languages, of which Scala is an instance, these two paradigms were up to now largely separate.

// While Scala's syntax is intentionally conventional, its type system breaks new ground in at least three areas. 
// First, abstract type defininitions and path-dependent types apply the νObj calculus [36] to a concrete language design. 
// Second, modular mixin composition combines the advantages of mixins and traits. 
// Third, views enable component adaptation in a modular way.

// ----------------------------------------------------------------------------
// The rest of this paper gives an overview of Scala. It expands on the following key aspects of the language:
1. Scala programs resemble Java programs in many ways and they can seamlessly interact with code written in Java (Section 2).
2. Scala has a uniform object model, in the sense that every value is an object and every operation is a method call (Section 3).
3. Scala is also a functional language in the sense that functions are first-class values (Section 4).
4. Scala has uniform and powerful abstraction concepts for both types and values (Section 5).
5. It has flexible modular mixin-composition constructs for composing classes and traits (Section 6).
6. It allows decomposition of objects by pattern matching (Section 7).
7. Patterns and expressions are generalized to support the natural treatment of XML documents (Section 8).
8. Scala allows external extensions of components using views (Section 9).
9. Section 10 discusses related work and Section 11 concludes.


package org.hablapps.fpinscala
package lenguajes



// What about functions that receive functions? Those functions will be pure
// if no side effect can be observed when pure functions are used as arguments
object Example8{

  // pure function
  def apply[A,B](a: A)(f: A => B): B = 
    f(a)

  // invocation with a pure function: no observable effect
  val l: String= apply(1)(_.toString)

  // invocation with an impure function: the execution produces side effects
  val l2: String = apply(1){ i => println(s"evaluating $i"); i.toString }

}


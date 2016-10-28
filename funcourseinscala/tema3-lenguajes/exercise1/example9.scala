package org.hablapps.fpinscala
package lenguajes


object Example9{

  // Impure non-parametric function: for A === String, it will cause side effects when 
  // passing a pure function 
  def apply[A,B](a: A)(f: A => B): B = {
    println(s"passing value $a")
    f(a)
  }

  // invocation with a pure function: observable effect
  val l: String= apply(1)(_.toString)

}


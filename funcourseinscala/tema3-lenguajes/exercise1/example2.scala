package org.hablapps.fpinscala
package lenguajes

// A functional version, using recursion
object Example2{
  
  def length[A](seq: Seq[A]): Int =
    seq match {
      case Nil => 0
      case _ :: xs => length(xs) + 1
    }
}


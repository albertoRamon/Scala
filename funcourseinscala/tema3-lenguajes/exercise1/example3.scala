package org.hablapps.fpinscala
package lenguajes


// Although there is a `var` in the function, it's not observable anywhere outside 
// of this function. This function can be cached.
object Example3{
  
  def length[A](xs: Seq[A]): Int = {
    var aux = 0
    for (_ <- xs) aux += 1
    aux
  }
}


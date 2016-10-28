package org.hablapps.fpinscala
package lenguajes

// What about functions defined over a type class? Provided that the components of 
// the type class are pure, the resulting expression will be pure as well. 

object Example10{
  import typeclasses.pattern.code.Monoid

  // pure function: when passed a "pure" monoid, no side-effect will be observed
  def reduce[T](l: List[T])(implicit T: Monoid[T]) =
    l.foldLeft(T.empty)(T.combine(_,_))

  // Impure monoid
  object ImpureIntMonoid extends Monoid[Int]{
    val empty = {println(s"returning empty"); 0}
    def combine(x1: Int, x2: Int) = x1+x2
  }

  // Non-referentially transparent expression, since the arguments are not pure
  val i1: Int = reduce(List(1,2,3))(ImpureIntMonoid)

  // Referentially transparent expression
  import typeclasses.pattern.code.Monoid.intInstance
  
  val i2: Int = reduce(List(1,2,3))(intInstance)

}


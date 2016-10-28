package org.hablapps.fpinscala
package lenguajes
package templates

// La type class `Monad` contiene todo lo relativo a los operadores 
// `doAndThen` and `returns`: API, sintaxis e intérpretes.

trait Monad[M[_]]

object Monad {

  def apply[M[_]](implicit M: Monad[M]): Monad[M] = M

  // Syntax for the type class

  object Syntax

  // Common instances

  import typeclasses.Id

  implicit object IdMonad extends Monad[Id]
}

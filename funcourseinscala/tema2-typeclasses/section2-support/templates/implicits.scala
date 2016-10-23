package org.hablapps.fpinscala.typeclasses
package support
package templates


/**
  Scala ofrece facilidades para trabajar con type classes, basadas principalmente
  en el mecanismo de implícitos.
*/
object Implicits{

  import intro.code.TypeClasses.Monoid

  // IMPLICITS

  // Pasar las operaciones del monoide una a una era engorroso, pero
  // si nos podemos ahorrar incluso pasar el monoide manualmente
  // mejor aún: declaramos el argumento como `implicit` y dejamos
  // que el compilador busque la instancia correspondiente.
  def collapse[A](t: List[A])(implict monoid: Monoid[A]): A =
    t match {
      case Nil => monoid.zero
      case h::t =>
        monoid.add(collapse(t)(monoid),h)
    }

  // Para que el compilador encuentre automáticamente las evidencias
  // tenemos que anotar las declaraciones como implícitas.
  implicit val intMonoid: Monoid[Int] = new Monoid[Int]{
    val zero = 0
    def add(t1: Int, t2: Int): Int = t1 + t2
  }

  // La composición ya no requiere pasar ni siquiera la instancia
  // del monoide. El compilador encuentra la instancia `intMonoid`
  // automáticamente
  def sumaInt(t: List[Int]): Int = ???

  
  // CONTEXT BOUNDS

  // Lo anterior se puede escribir también mediante `context bounds`,
  // pero entonces hay que acceder a la evidencia implícita mediante
  // la utilidad `implicitly[_]`
  // ???


  // SYNTAX

  // Para evitar `implicitly`, podemos utilizar algo de azúcar sintáctico.
  // Para las operaciones binarias podemos utilizar `implicit class`,
  // y para las operaciones que devuelven valores estáticos, helper
  // methods.

  object MonoidSyntax{

    // Operaciones binarias

    // Valores estáticos
    
  }

  // Con estas utilidades, podemos escribir nuestra función de una forma
  // más limpia.
  import MonoidSyntax._
  
  
}

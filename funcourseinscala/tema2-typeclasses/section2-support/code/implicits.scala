package org.hablapps.fpinscala.typeclasses
package support
package code

/**
  Scala ofrece facilidades para trabajar con type classes, basadas principalmente
  en el mecanismo de implícitos.
*/
object Implicits {

  import intro.code.TypeClasses.Monoid

  object ImplicitParameters{

    // Pasar las operaciones del monoide una a una era engorroso, pero 
    // si nos podemos ahorrar incluso pasar el monoide manualmente 
    // mejor aún: declaramos el argumento como `implicit` y dejamos
    // que el compilador busque la instancia correspondiente.

    def collapse[A](t: List[A])(implicit monoid: Monoid[A]): A = {
      t match {
        case Nil => monoid.zero
        case h::t => 
          monoid.add(collapse(t)(monoid),h)
      }
    }

    // Para que el compilador encuentre automáticamente las evidencias
    // tenemos que anotar las declaraciones como implícitas. 

    implicit val intMonoid = intro.code.TypeClasses.intMonoid
  
    // La composición ya no requiere pasar ni siquiera la instancia
    // del monoide. Se puede activar la opción "-Xprint:typer" del compilador
    // para ver exactamente qué instancia se ha insertado automáticamente.

    import intro.code.TypeClasses._

    // el compilador encuentra la instancia `intMonoid` automáticamente
    def sumaInt(t: List[Int]): Int =
      collapse(t)
  }

  
  object ContextBounds{
    
    // Lo anterior se puede escribir también mediante `context bounds`,
    // pero entonces hay que acceder a la evidencia implícita mediante
    // la utilidad `implicitly[_]`

    def collapse[A: Monoid](t: List[A]): A = {
      val monoid = implicitly[Monoid[A]]
      t match {
        case Nil => monoid.zero
        case h::t => 
          monoid.add(collapse(t)(monoid),h)
      }
    }
  }

  object AvoidingImplicitly{

    // Para evitar `implicitly`, podemos utilizar algo de azúcar sintáctico.
    // Para las operaciones binarias podemos utilizar `implicit class`,
    // y para las operaciones que devuelven valores estáticos, helper 
    // methods.

    object MonoidSyntax{
  
      // Operaciones binarias
      implicit class InfixSyntax[A](a1: A)(implicit M: Monoid[A]){
        def add(a2: A): A = M.add(a1,a2)
      }

      // Valores estáticos
      def zero[T](implicit M: Monoid[T]): T = M.zero
    }

    // Con estas utilidades, podemos escribir nuestra función de una forma
    // más limpia.
    import MonoidSyntax._

    def collapse[A: Monoid](t: List[A]): A =
      // t.foldLeft(zero)(_ add _)
      t match {
        case Nil => zero
        case h::t => collapse(t) add h
      }
  }
  
  
}













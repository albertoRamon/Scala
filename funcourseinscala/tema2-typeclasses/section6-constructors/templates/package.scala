package org.hablapps.fpinscala.typeclasses
package constructors

package object templates {

  // Podemos definir la función `map` para diferentes estructuras de datos.
  // La operación siempre va a tener el mismo significado, estamos cambiando
  // el contenido de la estructura de datos según una función.

  // Para `List[A]`
  def map[A,B](l: List[A])(f: A=>B): List[B] = 
    l match {
      case Nil => Nil
      case head :: tail => 
        (f(head): B) :: map(tail)(f)
    }

  // Para `Option[A]`
  def map[A,B](o: Option[A])(f: A=>B): Option[B] = 
    o.fold(None:Option[B])(
      (a: A) => Some(f(a)):Option[B]
    )

  // Digamos que ahora queremos implementar una función que transforma
  // el contenido de una estructura de datos de tal manera que duplica
  // todos los elementos, es decir, transforma cada `a` en `(a, a)`

  def duplicate[A](l: List[A]): List[(A,A)] = 
    map(l)(a => (a,a))

  def duplicate[A](o: Option[A]): Option[(A,A)] =
    map(o)(a => (a,a))

  // Vamos a crear una type class que contenga la función map. Esta type class
  // define la clase de los tipos para los que se puede transformar el contenido.

  trait Functor[F[_]]{
    // 1. Abstract

    // 2. Concrete (lift, as)
  }

  object Functor extends FunctorInstances
    with FunctorSyntax
    with FunctorLaws

  // 3. Instances
  trait FunctorInstances {
    // Summoner

    // Option instance
  }

  // 4. Syntax
  trait FunctorSyntax {
    object syntax {
      // Infix syntax

        // map infix operator
    }
  }

  // 5. Laws
  trait FunctorLaws {}

  // Ahora podemos crear nuestro duplicate genérico

}
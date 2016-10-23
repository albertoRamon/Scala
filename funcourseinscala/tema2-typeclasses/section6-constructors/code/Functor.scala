package org.hablapps.fpinscala.typeclasses
package constructors
package code

object HigherKindsTypeClasses {

  // Podemos definir la función `map` para diferentes estructuras de datos.
  // La operación siempre va a tener el mismo significado, estamos cambiando
  // el contenido de la estructura de datos según una función.

  // Para `List[A]`
  def map[A,B](l: List[A])(f: A => B): List[B] =
    l.foldRight(List.empty[B])(f(_) :: _)

  // Para `Option[A]`
  def map[A,B](o: Option[A])(f: A => B): Option[B] =
    o.fold(Option.empty[B])(f andThen Option.apply)

  // Digamos que ahora queremos implementar una función que transforma
  // el contenido de una estructura de datos de tal manera que duplica
  // todos los elementos, es decir, transforma cada `a` en `(a, a)`

  def duplicate[A](l: List[A]): List[(A,A)] = 
    map(l)(a => (a, a))

  def duplicate[A](o: Option[A]): Option[(A,A)] =
    map(o)(a => (a, a))

  // Vamos a crear una type class que contenga la función map. Esta type class
  // define la clase de los tipos para los que se puede transformar el contenido.

  trait Functor[F[_]]{
    // 1. Abstract
    def map[A, B](fa: F[A])(f: A => B): F[B]

    // 2. Concrete
    def lift[A, B](f: A => B): F[A] => F[B] = map(_)(f)
    def as[A, B](fa: F[A], b: B): F[B] = map(fa)(_ => b)
  }

  object Functor extends FunctorInstances
    with FunctorSyntax
    with FunctorLaws

  // 3. Instances
  trait FunctorInstances {
    def apply[F[_]](implicit ev: Functor[F]) = ev

    implicit val optionFunctor = new Functor[Option]{
      def map[A, B](fa: Option[A])(f: A => B): Option[B] =
        fa map f
    }    
  }

  // 4. Syntax
  trait FunctorSyntax {
    object syntax {
      implicit class FunctorOps[F[_], A](fa: F[A])(implicit ev: Functor[F]) {
        def map[B](f: A => B): F[B] = ev.map(fa)(f)
      }
    }
  }

  // 5. Laws
  trait FunctorLaws {
    import Functor.syntax._

    trait Laws[F[_]] {
      implicit val functor: Functor[F]

      def identity[A](fa: F[A]): Boolean =
        (fa map (x => x)) == fa
      def composite[A, B, C](fa: F[A], f1: A => B, f2: B => C): Boolean =
        (fa map f1 map f2) == (fa map (f1 andThen f2))
    }

    def laws[F[_]](implicit ev: Functor[F]) = new Laws[F] { implicit val functor = ev }
  }

  // Ahora podemos crear nuestro duplicate genérico
  import Functor.syntax._
  def duplicate[F[_]: Functor, A](o: F[A]): F[(A, A)] =
    o.map(a => (a,a))

}
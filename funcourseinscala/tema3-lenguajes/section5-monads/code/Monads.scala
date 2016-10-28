package org.hablapps.fpinscala
package lenguajes

// Type class definition

trait Monad[M[_]]{
  def flatMap[A, B](ma: M[A])(f: A => M[B]): M[B]
  def returns[A](value: A): M[A]

  // derived

  def map[A, B](ma: M[A])(f: A => B): M[B] =
    flatMap(ma)(f andThen returns)

  def flatten[A](mma: M[M[A]]): M[A] =
    flatMap(mma)(identity)

  def iterateUntil[A](ma: M[A])(p: A => Boolean): M[A] =
    flatMap(ma)(a => if (p(a)) returns(a) else iterateUntil(ma)(p))

}

object Monad {

  def apply[M[_]](implicit M: Monad[M]): Monad[M] = M

  // Syntax for the type class

  object Syntax{

    def returns[M[_],A](a: A)(implicit M: Monad[M]) = M.returns(a)

    implicit class InfixSyntax[M[_], A](ma: M[A])(implicit M: Monad[M]){

      def flatMap[B](f: A => M[B]): M[B] = 
        M.flatMap(ma)(f)

      def map[B](f: A => B): M[B] = 
        M.flatMap(ma)(s => M.returns(f(s)))

      def iterateUntil(cond: A => Boolean): M[A] =
        M.iterateUntil(ma)(cond)

    }
  }

  // Common instances

  import typeclasses.Id

  implicit object IdMonad extends Monad[Id]{
    def returns[A](a: A): A = a
    def flatMap[A,B](a: A)(f: A => B) = f(a)
  }
}

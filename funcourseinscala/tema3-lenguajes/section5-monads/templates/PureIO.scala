package org.hablapps.fpinscala
package lenguajes
package templates

trait IO[F[_]]{

  // Los operadores 
  def read: F[String]
  def write(msg: String): F[Unit]

  // Los combinadores `doAndThen` and `returns` desaparecen

  def doAndThen[A, B](f: F[A])(cont: A => F[B]): F[B]
  def returns[A](a: A): F[A]
}

object IO{

  // Syntax

  object Syntax{
    def read[F[_]](implicit IO: IO[F]) = IO.read
    def write[F[_]](msg: String)(implicit IO: IO[F]) = IO.write(msg)

    // Lo mismo con respecto a la sintaxis: también desaparecen.
  
    def returns[F[_], A](a: A)(implicit S: IO[F]): F[A] = S.returns(a)
    
    implicit class IOOps[F[_], A](f: F[A])(implicit S: IO[F]){
      def flatMap[B](cont: A => F[B]): F[B] = S.doAndThen(f)(cont)
      def map[B](m: A=>B): F[B] = S.doAndThen(f)(m andThen S.returns)
    }


  }
}

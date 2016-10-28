package org.hablapps.fpinscala
package lenguajes.io
package templates

// IO Language

trait IO{
  def read: String
  def write(msg: String): Unit
}

object IO{

  // Syntax

  object Syntax{
    // def read[F[_]](implicit IO: IO[F]) = ???
    // def write[F[_]](msg: String)(implicit IO: IO[F]) = ???

    // // combinators
  
    // def returns[F[_], A](a: A)(implicit S: IO[F]): F[A] = ???
    
    // implicit class IOOps[F[_], A](f: F[A])(implicit S: IO[F]){
    //   def flatMap[B](cont: A => F[B]): F[B] = ???
    //   def map[B](m: A=>B): F[B] = ???
    // }


  }
}

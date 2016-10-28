package org.hablapps.fpinscala
package lenguajes

// IO Language

trait IO[F[_]]{
  def read: F[String]
  def write(msg: String): F[Unit]
}

object IO{

  // Syntax

  object Syntax{
    def read[F[_]](implicit IO: IO[F]) = IO.read
    def write[F[_]](msg: String)(implicit IO: IO[F]) = IO.write(msg)
  }

  // Interpreter

  import scala.io.StdIn._, typeclasses.Id

  implicit object ConsoleIO extends IO[Id] {
    def read: String = readLine
    def write(msg: String): Unit = print(msg)
  }

}

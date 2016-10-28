package org.hablapps.fpinscala
package lenguajes

object IOPrograms{
  // Logic 

  import IO.Syntax._, Monad.Syntax._

  def echo[F[_]: IO: Monad]: F[String] =
    for {
      msg <- read
      _ <- write(msg)
    } yield msg
  
  // Interpretation
  import typeclasses.Id 

  def runEcho: String = echo[Id](IO.ConsoleIO, Monad[Id])

}


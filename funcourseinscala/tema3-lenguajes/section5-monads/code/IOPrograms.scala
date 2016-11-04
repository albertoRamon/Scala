package org.hablapps.fpinscala
package lenguajes

object IOPrograms{
  // Logic 

  import IO.Syntax._, Monad.Syntax._
// F ya no tiene flatmap, y hay que definirlo como monada
  def echo[F[_]: IO: Monad]: F[String] =
    for {
      msg <- read
      _ <- write(msg)
    } yield msg
  
  // Para ejecutar los programas debemos proporcionar ahora no sólo
  // la instancia de IO sino también la de mónada.
  import typeclasses.Id 

  def runEcho: String = echo[Id](IO.ConsoleIO, Monad[Id])

}


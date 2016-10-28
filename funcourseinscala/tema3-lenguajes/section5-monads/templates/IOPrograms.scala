package org.hablapps.fpinscala
package lenguajes
package templates

object IOPrograms{
  
  // En la nueva versión del programa `echo` tenemos 
  // que añadir una nueva evidencia a la lista de implícitos
  // para poder utilizar los combinadores.
  import lenguajes.io.IO.Syntax._

  def echo[F[_]: lenguajes.io.IO]: F[String] =
    for {
      msg <- read
      _ <- write(msg)
    } yield msg  

  // Para ejecutar los programas debemos proporcionar ahora no sólo
  // la instancia de IO sino también la de mónada.
  import typeclasses.Id
  
  def runEcho: String = echo[Id](Interpreter.ConsoleIO)

}


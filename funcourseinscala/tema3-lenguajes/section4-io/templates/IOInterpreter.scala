package org.hablapps.fpinscala
package lenguajes.io
package templates

object Interpreter{

  // Interpreter

  import scala.io.StdIn._, typeclasses.Id

  object ConsoleIO extends ImpureIO.IO{
    def read(): String = readLine
    def write(msg: String): Unit = println(msg)
  }

  // Ejemplo de ejecución

  def consoleEcho: String = ImpureIO.echo(ConsoleIO)

}


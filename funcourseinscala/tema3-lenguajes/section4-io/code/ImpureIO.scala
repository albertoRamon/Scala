package org.hablapps.fpinscala
package lenguajes.io

import scala.io.StdIn._

object ImpureIO{

  // Interface

  trait IO{
    def read(): String
    def write(msg: String): Unit
  }

  // Logic 

  def echo(IO: IO): String = {
    val msg: String = IO.read
    IO.write(msg)
    msg
  }

  // Interpretation

  object ConsoleIO extends IO{
    def read(): String = readLine
    def write(msg: String): Unit = println(msg)
  }

  // All together
  def consoleEcho: String = echo(ConsoleIO)

}
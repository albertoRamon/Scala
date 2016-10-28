package org.hablapps.fpinscala
package lenguajes.io

object Interpreter{

  // Interpreter

  import scala.io.StdIn._, typeclasses.Id

  implicit object ConsoleIO extends IO[Id] {
    def read: String = readLine
    def write(msg: String): Unit = print(msg)

    // Combinators      
    def returns[A](a: A): A = a
    def doAndThen[A, B](fa: A)(f: A => B): B = f(fa)
  }

  // Ejemplo de ejecución

  def runEcho: String = IOPrograms.echo[Id](Interpreter.ConsoleIO)

}


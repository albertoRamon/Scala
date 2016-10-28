package org.hablapps.fpinscala
package lenguajes
package templates

object Interpreter{

  // Interpreter

  import scala.io.StdIn._, typeclasses.Id

  // Del intérprete también debemos eliminar todo lo relativo
  // a los combinadores genéricos
  implicit object ConsoleIO extends lenguajes.io.IO[Id] {
    def read: String = readLine
    def write(msg: String): Unit = print(msg)

    // Implementación de los combinadores innecesaria.      
    def returns[A](a: A): A = a
    def doAndThen[A, B](fa: A)(f: A => B): B = f(fa)
  }

}


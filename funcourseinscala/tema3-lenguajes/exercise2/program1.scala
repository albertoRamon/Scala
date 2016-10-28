package org.hablapps.fpinscala
package lenguajes.io


/**
 * Define un programa que lea de pantalla un número y diga
 * si es par o impar.
 *
 * Ejemplo de ejecución
 *
 * scala> runWriteANumber
 * (Escribe "8")
 * 8 es un número par
 *
 * scala> runWriteANumber
 * (Escribe "5")
 * 5 es un número impar
 *
 * Para probar este programa puedes ejecutar el comando de sbt: 
 *   `test-tema3-ejercicio2`
 */
object Program1{

  // Función auxiliar
  def evenOdd(n: String): String =
    if (n.toInt % 2 == 0)
      s"$n es un número par"
    else
      s"$n es un número impar"


  // Versión impura, ofrecida como referencia
  import lenguajes.io.ImpureIO

  trait Impure{ IO: ImpureIO.IO =>
    import IO._ 

    def writeANumber: Unit = {
      val num = read
      write(evenOdd(num))
    }

  }

  // Versión pura a implementar
  object Pure{
    import IO.Syntax._
  
    def writeANumber[F[_]: IO]: F[Unit] = ???
  }

}

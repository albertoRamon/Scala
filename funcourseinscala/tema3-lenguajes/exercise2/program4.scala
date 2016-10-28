package org.hablapps.fpinscala
package lenguajes.io

/**
 * Escribe un programa similar al anterior, pero esta vez que se
 * vaya escribiendo por pantalla lo que se va leyendo del teclado
 *
 * Ejemplo de ejecución:
 *
 * scala> readUntilExit2
 *   (escribe "hola")
 *   hola
 *   (escribe "mundo")
 *   mundo
 *   (escribe "exit")
 *   exit
 *
 * Para probar estos programas puedes ejecutar el comando de sbt: 
 *   `test-tema3-ejercicio2`
 */
object Program4{

  // Versión impura
  import lenguajes.io.ImpureIO
  
  trait Impure{ IO: ImpureIO.IO with Program1.Impure => 
    import IO._

    def readUntilExit: Unit = {
      val msg = read
      write(msg)
      if (msg == "exit") ()
      else readUntilExit
    }
  }

  // Versión pura
  object Pure{
    import IO.Syntax._

    def readUntilExit[F[_]: IO]: F[Unit] = ???
  }

}

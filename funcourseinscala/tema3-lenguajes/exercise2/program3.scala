package org.hablapps.fpinscala
package lenguajes.io

/**
 * Escribe un programa que lea líneas de la consola
 * constantemente
 * hasta que se escriba la palabra "exit"
 *
 * Ejemplo de ejecución:
 * scala> readUntilExit
 *   (escribe "hola")
 *   (escribe "mundo")
 *   (escribe "exit")
 *
 *
 * Para probar estos programas puedes ejecutar el comando de sbt: 
 *   `test-tema3-ejercicio2`
 */
object Program3{

  // Versión impura
  import lenguajes.io.ImpureIO
  
  trait Impure{ IO: ImpureIO.IO with Program1.Impure => 
    import IO._

    def readUntilExit: Unit = {
      val msg = read
      if (msg == "exit") ()
      else readUntilExit
    }

  }

  // Versión pura
  object Pure{
    import IO.Syntax._

    def readUntilExit[F[_]: IO]: F[Unit] = ???
    for {
    msg <-read
    _ <- if (msg=="exit") returns (())
    
    falta
    }
    
    
  }

}

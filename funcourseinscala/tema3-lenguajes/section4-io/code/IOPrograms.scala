package org.hablapps.fpinscala
package lenguajes.io

// def prog abstracto
object IOPrograms{
  // Logic 

  import IO.Syntax._  //para que encuentra map y flatmap

  def echo[F[_]: IO]: F[String] =
    for {
      msg <- read
      _ <- write(msg)
    } yield msg
  
}


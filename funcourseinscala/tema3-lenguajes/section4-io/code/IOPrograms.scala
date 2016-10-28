package org.hablapps.fpinscala
package lenguajes.io

object IOPrograms{
  // Logic 

  import IO.Syntax._

  def echo[F[_]: IO]: F[String] =
    for {
      msg <- read
      _ <- write(msg)
    } yield msg
  
}


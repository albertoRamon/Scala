package org.hablapps.fpinscala
package lenguajes.io
package templates

object IOPrograms{
  
  def echo(IO: ImpureIO.IO): String = {
    val msg: String = IO.read
    IO.write(msg)
    msg
  }
  
}


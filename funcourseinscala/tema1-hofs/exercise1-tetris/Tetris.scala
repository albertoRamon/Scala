package org.hablapps.fpinscala.hofs

// Ejercicio Diagrams: Implementar las figuras del tetris con la
// librería de diagrams

// Prueba este ejercicio con el comando `test-tema1-tetris`
object Tetris {
  import diagrams.code._, Shapes._, StyleSheets._, Pictures._

  // Se proporciona una Picture que representa un cuadrado de 1 x 1
  // Utiliza estos "bloques" para crear las figuras
  def block(c: Color): Picture =
    Place(FillColor(c) :: Nil, Rectangle(1, 1))

//ejemplos
  val I: Picture =
    block(Maroon) above
    block(Maroon) above
    block(Maroon) above
    block(Maroon)

  val J: Picture =
    (block(Alpha)     beside block(LightGray)) above
    (block(Alpha)     beside block(LightGray)) above
    (block(LightGray) beside block(LightGray))

  // Utiliza el color `Purple` para esta figura
  val L: Picture =  
	block(Maroon) beside block(Alpha) beside
    block(Maroon) beside block(Alpha) 

  // Utiliza el color `NavyBlue` para esta figura
  val O: Picture = ???

  // Utiliza el color `DarkGreen` para esta figura
  val S: Picture = ???

  // Utiliza el color `Brown` para esta figura
  val T: Picture = ???

  // Utiliza el color `Teal` para esta figura
  val Z: Picture = ???

  def comprobacion = {
    println("L:")
    println(SVG.toSVG(Drawings.drawPicture(L)))
    println("O:")
    println(SVG.toSVG(Drawings.drawPicture(O)))
    println("S:")
    println(SVG.toSVG(Drawings.drawPicture(S)))
    println("T:")
    println(SVG.toSVG(Drawings.drawPicture(T)))
    println("Z:")
    println(SVG.toSVG(Drawings.drawPicture(Z)))
  }

  // Descomenta la siguiente línea para comprobar el Ejercicio1
  // comprobacion

}
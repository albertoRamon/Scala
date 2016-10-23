package org.hablapps.fpinscala.hofs
package test

import org.scalatest._
import diagrams.code.{StyleSheets, Pictures}, StyleSheets._, Pictures._

// Prueba este ejercicio con el comando `test-tema1-tetris`
class TetrisSpec extends FlatSpec with Matchers {

  "Tetris" should "L tiene la forma correcta" in {
    Tetris.L shouldBe
      Above(
        Above(
          Beside(Tetris.block(Purple), Tetris.block(Alpha)),
          Beside(Tetris.block(Purple), Tetris.block(Alpha))),
        Beside(Tetris.block(Purple), Tetris.block(Purple)))
  }

  it should "O tiene la forma correcta" in {
    Tetris.O shouldBe
      Above(
        Beside(Tetris.block(NavyBlue), Tetris.block(NavyBlue)),
        Beside(Tetris.block(NavyBlue), Tetris.block(NavyBlue)))
  }

  it should "S tiene la forma correcta" in {
    Tetris.S shouldBe
      Above(
        Beside(
          Beside(Tetris.block(Alpha), Tetris.block(DarkGreen)),
          Tetris.block(DarkGreen)),
        Beside(
          Beside(Tetris.block(DarkGreen), Tetris.block(DarkGreen)),
          Tetris.block(Alpha)))
  }

  it should "T tiene la forma correcta" in {
    Tetris.T shouldBe
      Above(
        Beside(
          Beside(Tetris.block(Brown), Tetris.block(Brown)),
          Tetris.block(Brown)),
        Beside(
          Beside(Tetris.block(Alpha), Tetris.block(Brown)),
          Tetris.block(Alpha)))
  }

  it should "Z tiene la forma correcta" in {
    Tetris.Z shouldBe
      Above(
        Beside(
          Beside(Tetris.block(Teal), Tetris.block(Teal)),
          Tetris.block(Alpha)),
        Beside(
          Beside(Tetris.block(Alpha), Tetris.block(Teal)),
          Tetris.block(Teal)))
  }

}

package org.hablapps.fpinscala.hofs
package test

import org.scalatest._

// Prueba este ejercicio con el comando `test-tema1-comp`
class CompositionSpec extends FlatSpec with Matchers {

  def square(i:Int) = i * i
  def toQuotedString(i:Int) = "'" + i.toString + "'"

  "ComposiciónFunciones" should "componer dos funciones" in {
    Composition.compose(toQuotedString, square)(10) shouldBe "'100'"
    Composition.andThen(square, toQuotedString)(10) shouldBe "'100'"
  }

}

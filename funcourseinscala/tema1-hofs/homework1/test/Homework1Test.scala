package org.hablapps.fpinscala.hofs
package test

import org.scalatest._
import homework.Exercise1._

// Prueba este ejercicio con el comando `test-tema1-ej1`
class Ej1Spec extends FlatSpec with Matchers {

  "Filter" should "funcionar de forma correcta para Option" in {
    PartI.filter(Option(3))(_ % 2 != 0) shouldBe Option(3)
    PartI.filter(Option(3))(_ % 2 == 0) shouldBe Option.empty
    PartI.filter(Option.empty[Int])(_ => true) shouldBe Option.empty
  }

  "Map" should "funcionar de forma correcta para Option" in {
    PartI.map(Option(3))(_ + 1) shouldBe Option(4)
    PartI.map(Option.empty[Int])(_ + 1) shouldBe Option.empty
  }

  "Fold" should "funcionar de forma correcta para Option" in {
    PartII.fold(Option(3))("tres")(_.toString) shouldBe "3"
    PartII.fold(Option.empty[Int])("tres")(_.toString) shouldBe "tres"
  }

  "FilterFold" should "funcionar de forma correcta para Option" in {
    PartII.filter(Option(3))(_ % 2 != 0) shouldBe Option(3)
    PartII.filter(Option(3))(_ % 2 == 0) shouldBe Option.empty
    PartII.filter(Option.empty[Int])(_ => true) shouldBe Option.empty
  }

  "MapFold" should "funcionar de forma correcta para Option" in {
    PartII.map(Option(3))(_ + 1) shouldBe Option(4)
    PartII.map(Option.empty[Int])(_ + 1) shouldBe Option.empty
  }

}

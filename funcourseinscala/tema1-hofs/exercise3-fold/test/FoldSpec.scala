package org.hablapps.fpinscala.hofs
package test

import org.scalatest._
import diagrams.code.{StyleSheets, Pictures}, StyleSheets._, Pictures._

// Prueba este ejercicio con el comando `test-tema1-fold`
class FoldSpec extends FlatSpec with Matchers {

  "FilterEnFuncionDeFold" should "funcionar como el filter original" in {
    val l1 = Catamorphism.List(1, 2, 3)
    val l2 = Catamorphism.List(1, 3)
    Catamorphism.filter(l1)(_ % 2 != 0) shouldBe l2
  }

}

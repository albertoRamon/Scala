package org.hablapps.fpinscala.typeclasses
package test

import _root_.org.scalatest._
import exercises.HomeworkTypeClasses._

class HomeTCSpec extends FlatSpec with Matchers {

  val l1 = List(1,2,3,4)
  val l2 = List(1.0,2.0,3.0,4.0)

  "SumInt" should "sumar todos los elementos de una lista de Ints" in {
    ParteII.sum(l1) shouldBe 10
  }

  "SumDouble" should "sumar todos los elementos de una lista de Doubles" in {
    ParteII.sum(l2) shouldBe 10.0
  }

  "AvgInt" should "calcular la media de una lista de Ints" in {
    ParteII.avg(l1) shouldBe 2.5
  }

  "AvgDouble" should "calcular la media de una lista de Doubles" in {
    ParteII.avg(l2) shouldBe 2.5
  }

  "VarianceInt" should "calcular la varianza de una lista de Ints" in {
    ParteII.variance(l1) shouldBe 1.25
  }

  "VarianceDouble" should "calcular la varianza de una lista de Doubles" in {
    ParteII.variance(l2) shouldBe 1.25
  }

}

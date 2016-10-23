package org.hablapps.fpinscala.typeclasses
package test

import _root_.org.scalatest._
import exercises.HomeworkTypeConstructors._

class HomeTypeConstructorsSpec extends FlatSpec with Matchers {

  "ListaInt" should "crear listas identicas independientemente de la interpretación" in {
    PartI.sample[List] shouldBe PartI.sample[Vector].toList
  }

  "ListaString" should "crear listas identicas independientemente de la interpretación" in {
    PartI.sample2[List] shouldBe PartI.sample2[Vector].toList
  }

}

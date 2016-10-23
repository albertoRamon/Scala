package org.hablapps.fpinscala.hofs
package test

import org.scalatest._
import homework.Exercise2._

// Prueba este ejercicio con el comando `test-tema1-ej2`
class Ej2Spec extends FlatSpec with Matchers {

  val t1 = Node(Node(Node(Node(Empty(), 5, Empty()), 4, Empty()), 2, Empty()), 1, Node(Empty(), 3, Empty()))
  val emptyT = Empty[Int]()
  def foldFunction(l: Int, i: Int, r: Int): Int = (l :: i :: r :: Nil).max

  "Sum" should "funcionar de forma correcta para Tree" in {
    PartII.sum(t1) shouldBe 15
  }

  "Max" should "funcionar de forma correcta para Tree" in {
    PartII.max(t1) shouldBe 5
  }

  "Empties" should "funcionar de forma correcta para Tree" in {
    PartII.emptyTrees(t1) shouldBe 6
  }

  "Depth" should "funcionar de forma correcta para Tree" in {
    PartII.depth(t1) shouldBe 4
  }

  "Fold" should "funcionar de forma correcta para Tree" in {
    PartIII.fold(emptyT)(1, foldFunction) shouldBe 1
    PartIII.fold(t1)(0, foldFunction) shouldBe 5
  }

  "SumFold" should "funcionar de forma correcta para Tree" in {
    PartIII.sum(t1) shouldBe 15
  }

  "MaxFold" should "funcionar de forma correcta para Tree" in {
    PartIII.max(t1) shouldBe 5
  }

  "EmptiesFold" should "funcionar de forma correcta para Tree" in {
    PartIII.emptyTrees(t1) shouldBe 6
  }

  "DepthFold" should "funcionar de forma correcta para Tree" in {
    PartIII.depth(t1) shouldBe 4
  }

}

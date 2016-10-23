package org.hablapps.fpinscala.typeclasses
package test

import _root_.org.scalatest._
import EjerciciosTypeClasses._

class ClaseSpec extends FlatSpec with Matchers {

  val l1 = 1 :: 4 :: 3 :: 5 :: 2 :: Nil
  val l2 = "1" :: "4" :: "3" :: "5" :: "2" :: Nil
  val l3 = Option(1) :: Option(4) :: Option(3) :: Option(5) :: Option(2) :: Nil
  val l4 = List.empty[Int]

  "writeFirstUnsafe" should "work with non-empty Int lists" in {
    EjerciciosTypeClasses.writeFirstUnsafe(l1) shouldBe "1"
  }

  it should "work with non-empty String lists" in {
    EjerciciosTypeClasses.writeFirstUnsafe(l2) shouldBe "1"
  }

  it should "work with non-empty Option[Int] lists" in {
    EjerciciosTypeClasses.writeFirstUnsafe(l3) shouldBe "Some(1)"
  }

  it should "fail with an empty Int list" in {
    a [NoSuchElementException] should be thrownBy
      EjerciciosTypeClasses.writeFirstUnsafe(l4)
  }

  "writeFirstSafe" should "work with non-empty Int lists" in {
    EjerciciosTypeClasses.writeFirstSafe(l1) shouldBe Some("1")
  }

  it should "work with non-empty String lists" in {
    EjerciciosTypeClasses.writeFirstSafe(l2) shouldBe Some("1")
  }

  it should "work with non-empty Option[Int] lists" in {
    EjerciciosTypeClasses.writeFirstSafe(l3) shouldBe Some("Some(1)")
  }

  it should "work with an empty Int list" in {
    EjerciciosTypeClasses.writeFirstSafe(l4) shouldBe None
  }

}

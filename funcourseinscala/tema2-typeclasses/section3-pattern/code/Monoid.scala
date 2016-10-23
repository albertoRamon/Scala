package org.hablapps.fpinscala.typeclasses
package pattern
package code

trait Monoid[A] {
  // 1. Abstract interface
  val empty: A
  def combine(a1: A, a2: A): A

  // 2. Concrete interface
  def combineN(a: A, n: Int): A =
    if (n < 0) throw new IllegalArgumentException("Repeated combining for semigroups must have n > 0")
    else if (n == 0) empty
    else (1 to n).foldLeft(a)((acc, _) => combine(acc, a))
}

object Monoid extends MonoidInstances
  with MonoidSyntax
  with MonoidLaws

// 3. Instances (including summoner)
trait MonoidInstances {
  def apply[A](implicit ev: Monoid[A]) = ev

  implicit val intInstance = new Monoid[Int] {
    val empty: Int = 0
    def combine(a1: Int, a2: Int): Int = a1 + a2
  }

  implicit val stringInstance = new Monoid[String] {
    val empty: String = ""
    def combine(a1: String, a2: String): String = a1 + a2
  }

  implicit def optionInstance[A](implicit ev: Monoid[A]) =
    new Monoid[Option[A]] {
      val empty: Option[A] = None
      def combine(o1: Option[A], o2: Option[A]): Option[A] =
        for {
          a1 <- o1
          a2 <- o2
        } yield ev.combine(a1, a2)
    }
}

// 4. Syntax
trait MonoidSyntax {
  object syntax {
    // 4.1. Infix Ops
    implicit class MonoidOps[A](a: A)(implicit ev: Monoid[A]) {
      def |+|(other: A) = ev.combine(a, other)
    }

    // 4.2. Global functions
    def empty[A](implicit ev: Monoid[A]) =
      ev.empty
  }
}

// 5. Laws
trait MonoidLaws {
  import Monoid.syntax._

  trait Laws[A] {
    implicit val instance: Monoid[A]

    def associative(a1: A, a2: A, a3: A): Boolean =
      ((a1 |+| a2) |+| a3) == (a1 |+| (a2 |+| a3))
    def leftIdentity(a: A): Boolean =
      (empty[A] |+| a) == a
    def rightIdentity(a: A): Boolean =
      (a |+| empty[A]) == a

  }

  object Laws {
    def apply[A](implicit ev: Monoid[A]) =
      new Laws[A] {
        implicit val instance: Monoid[A] = ev
      }
  }
}

package org.hablapps.fpinscala.typeclasses
package pattern
package code

trait Order[A] {
  // 1. Abstract interface
  def compare(a1: A, a2: A): Int

  // 2. Concrete interface
  def gt(a1: A, a2: A): Boolean = compare(a1, a2) > 0
  def lt(a1: A, a2: A): Boolean = compare(a1, a2) < 0
  def eq(a1: A, a2: A): Boolean = compare(a1, a2) == 0
  def gteq(a1: A, a2: A): Boolean = !lt(a1, a2)
  def lteq(a1: A, a2: A): Boolean = !gt(a1, a2)
  def greater(a1: A, a2: A): A =
    if (gteq(a1, a2)) a1
    else a2
}

object Order extends OrderInstances
  with OrderSyntax
  with OrderLaws

// 3. Instances (including summoner)
trait OrderInstances {
  def apply[A](implicit ev: Order[A]) = ev

  implicit val intInstance = new Order[Int] {
    def compare(a1: Int, a2: Int): Int = a1 - a2
  }

  implicit def optionInstance[A](implicit ev: Order[A]) =
    new Order[Option[A]] {
      def compare(o1: Option[A], o2: Option[A]): Int =
        (o1, o2) match {
          case (Some(a1), Some(a2)) => ev.compare(a1, a2)
          case (None, None) => 0
          case (Some(_), _) => 1
          case _ => -1
        }
    }
}

// 4. Syntax
trait OrderSyntax {
  object syntax {
    // 4.1. Infix Ops
    implicit class OrderOps[A](a: A)(implicit ev: Order[A]) {
      def compareTo(other: A) = ev.compare(a, other)
      def >(other: A): Boolean = ev.gt(a, other)
      def <(other: A): Boolean = ev.lt(a, other)
      def ===(other: A): Boolean = ev.eq(a, other)
      def >=(other: A): Boolean = ev.gteq(a, other)
      def <=(other: A): Boolean = ev.lteq(a, other)
    }

    // 4.2. Global functions
    def greater[A](a1: A, a2: A)(implicit ev: Order[A]) =
      ev.greater(a1, a2)
  }
}

// 5. Laws
trait OrderLaws {
  import Order.syntax._

  trait Laws[A] {
    implicit val instance: Order[A]

    def antisymmetric(a1: A, a2: A): Boolean =
      (a1 > a2) == (a2 <= a1)

    def transitive(a1: A, a2: A, a3: A): Boolean = {
      val a1a2 = a1 > a2
      val a2a3 = a2 > a3
      val a1a3 = a1 > a3
      if (a1a2 == a2a3)
        a1a3 == a1a2
      else
        true
    }
  }

  object Laws {
    def apply[A](implicit ev: Order[A]) =
      new Laws[A] {
        implicit val instance: Order[A] = ev
      }
  }
}

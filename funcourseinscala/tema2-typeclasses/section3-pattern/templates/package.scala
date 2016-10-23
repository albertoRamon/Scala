package org.hablapps.fpinscala.typeclasses
package pattern

package object templates {

  trait Order[A] {
    // 1. Abstract interface
    def compare(a1: A, a2: A): Int

    // 2. Concrete interface
    def gt(a1: A, a2: A): Boolean = ???
    def lt(a1: A, a2: A): Boolean = ???
    def eq(a1: A, a2: A): Boolean = ???
    def gteq(a1: A, a2: A): Boolean = ???
    def lteq(a1: A, a2: A): Boolean = ???
    def greater(a1: A, a2: A): A = ???
  }

  object Order extends OrderInstances
    with OrderSyntax
    with OrderLaws

  // 3. Instances (including summoner)
  trait OrderInstances {
    def apply[A](implicit ev: Order[A]) = ev

    implicit val intInstance = new Order[Int] {
      def compare(a1: Int, a2: Int): Int = ???
    }

    implicit def optionInstance[A](implicit ev: Order[A]) =
      new Order[Option[A]] {
        def compare(o1: Option[A], o2: Option[A]): Int = ???
      }
  }

  // 4. Syntax
  trait OrderSyntax {
    object syntax {
      // 4.1. Infix Ops
      implicit class OrderOps[A](a: A)(implicit ev: Order[A]) {
        def compareTo(other: A) = ???
        def >(other: A): Boolean = ???
        def <(other: A): Boolean = ???
        def ===(other: A): Boolean = ???
        def >=(other: A): Boolean = ???
        def <=(other: A): Boolean = ???
      }

      // 4.2. Global functions
      def greater[A](a1: A, a2: A)(implicit ev: Order[A]) = ???
    }
  }

  // 5. Laws
  trait OrderLaws {
    import Order.syntax._

    trait Laws[A] {
      implicit val instance: Order[A]

      // Antisymetric
      def antisymmetric(a1: A, a2: A): Boolean = ???

    }

    object Laws {
      def apply[A](implicit ev: Order[A]) =
        new Laws[A] {
          implicit val instance: Order[A] = ev
        }
    }
  }

  // Programas genéricos que utilizan Order[_]

}

package org.hablapps.fpinscala.typeclasses
package pattern
package code

trait TypeClassName[A] {
  // 1. Abstract interface
  def foo(): Nothing
  def op(a1: A, a2: A): Nothing

  // 2. Concrete interface
  def bar(): Nothing = foo()
}

object TypeClassName extends TypeClassNameInstances
  with TypeClassNameSyntax
  with TypeClassNameLaws

// 3. Instances (including summoner)
trait TypeClassNameInstances {
  def apply[A](implicit ev: TypeClassName[A]) = ev

  implicit val stringInstance = new TypeClassName[String] {
    def foo(): Nothing = ???
    def op(a1: String, a2: String): Nothing = ???
  }

  implicit def optionInstance[A](implicit ev: TypeClassName[A]) =
    new TypeClassName[Option[A]] {
      def foo(): Nothing = ???
      def op(a1: Option[A], a2: Option[A]): Nothing = ???
    }
}

// 4. Syntax
trait TypeClassNameSyntax {
  object syntax {
    // 4.1. Infix Ops
    implicit class TypeClassNameOps[A](a: A)(implicit ev: TypeClassName[A]) {
      def op(other: A): Nothing = ev.op(a, other)
    }

    // 4.2. Global functions
    implicit def foo[A]()(implicit ev: TypeClassName[A]) = ev.foo()
  }
}

// 5. Laws
trait TypeClassNameLaws {
  trait Laws[A] {
    implicit val instance: TypeClassName[A]

    def predicate1: Boolean = true
    def predicate2: Boolean = true
  }

  object Laws {
    def apply[A](implicit ev: TypeClassName[A]) =
      new Laws[A] {
        implicit val instance: TypeClassName[A] = ev
      }
  }
}

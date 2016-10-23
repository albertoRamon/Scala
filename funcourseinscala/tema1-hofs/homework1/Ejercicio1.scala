package org.hablapps.fpinscala.hofs.homework

// Prueba este ejercicio con el comando `test-tema1-ej1`
object Exercise1 {

  object PartI {
    // Implementa `filter` para el tipo `Option`
    def filter[A](o: Option[A])(p: A => Boolean): Option[A] = ???

    // Implementa `map` para el tipo `Option`
    def map[A, B](o: Option[A])(f: A => B): Option[B] = o match {
      case None => ???
      case Some(x) => ???
    }
  }

  object PartII {
    // Implementa `fold` para el tipo `Option`
    def fold[A, B](o: Option[A])(b: B)(f: A => B): B = ???

    // Implementa `filter` en términos de `fold`
    def filter[A](o: Option[A])(p: A => Boolean): Option[A] =
      fold(???)(???)(???)

    // Implementa `map` en términos de `fold`
    def map[A, B](o: Option[A])(f: A => B): Option[B] =
      fold(???)(???)(???)

  }
}
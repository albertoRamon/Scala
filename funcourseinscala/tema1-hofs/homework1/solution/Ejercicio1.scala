package org.hablapps.fpinscala.hofs.homework
package solution

object Exercise1 {

  object PartI {
    // Implementa `filter` para el tipo `Option`
    def filter[A](o: Option[A])(p: A => Boolean): Option[A] = o filter p

    // Implementa `map` para el tipo `Option`
    def map[A, B](o: Option[A])(f: A => B): Option[B] = o map f
  }

  object PartII {
    // Implementa `fold` para el tipo `Option`
    def fold[A, B](o: Option[A])(b: B)(f: A => B): B = o.fold(b)(f)

    // Implementa `filter` en términos de `fold`
    def filter[A](o: Option[A])(p: A => Boolean): Option[A] =
      o.filter(p)

    // Implementa `map` en términos de `fold`
    def map[A, B](o: Option[A])(f: A => B): Option[B] =
      o map f

  }
}

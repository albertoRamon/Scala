package org.hablapps.fpinscala.typeclasses
package intro
package code

/**
  Para evitar tener que pasar una y otra vez la misma información sobre un 
  determinado parámetro, podemos agrupar la información requerida en una 
  nueva estructura y pasar únicamente una instancia de dicha estructura.
*/

object TypeClasses {

  // Las operaciones que nos hacen falta son las correspondientes a un monoide.
  // Un monoide es un tipo de datos para el que podemos dar un zero y 
  // una operación binaria de composición (suma). Estas operaciones deben
  // satisfacer determinadas leyes: 1) elemento neutro y 2) asociatividad.
  // Un monoide define una clase de tipos (type class): aquellos para los que hay 
  // un zero y una operación binaria con esas propiedades.

  trait Monoid[T]{
    val zero: T
    def add(t1: T,t2: T): T
  }

  // Una vez hecho esto, podemos proporcionar esta información de "golpe"
  def collapse[A](t: List[A])(monoid: Monoid[A]): A =
    t match {
      case Nil => monoid.zero
      case h::t => 
        monoid.add(collapse(t)(monoid),h)
    }
  
  // Para poder utilizar la estructura debemos instanciar la typeclass `Monoid`
  // con los tipos correspondientes. La composición ya solo requiere pasar 
  // la instancia correspondiente

  val intMonoid: Monoid[Int] = new Monoid[Int]{
    val zero = 0
    def add(t1: Int, t2: Int): Int = t1 + t2
  }

  object stringMonoid extends Monoid[String]{
    val zero: String = ""
    def add(t1: String, t2: String): String = t1 + t2
  }

  def sumaInt(l: List[Int]): Int =
    collapse[Int](l)(intMonoid)

  def concatBis(l: List[String]): String = ???

}

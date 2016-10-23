package org.hablapps.fpinscala.typeclasses
package intro
package code

/**
  El polimorfismo paramétrico a veces no es suficiente porque hace falta
  información concreta sobre el parámetro. Esa información se puede abstraer
  mediante parametrización de valores y/o funciones.
 */
object HigherOrderFunctions {
  
  // Funciones monolíticas

  def sumaInt(l: List[Int]): Int =
    l match {
      case Nil => 0
      case x :: r => x + sumaInt(r)
    }

  def concat(l: List[String]): String =
    l match {
      case Nil => ""
      case x :: r => x + concat(r)
    }

  // Abstracción

  def collapse[A](l: List[A])(
    zero: A, add: (A,A) => A): A =
    l match {
      case Nil => zero
      case x :: r => add(x,collapse(r)(zero,add))
    }

  // Composición

  def sumaIntBis(l: List[Int]): Int =
    collapse(l)(0, _+_)

  def concatBis(l: List[String]): String =
    collapse(l)("", _+_)

}

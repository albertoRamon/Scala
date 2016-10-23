package org.hablapps.fpinscala.typeclasses
package data
package code

object ADT {

  // Datos

  sealed trait Exp
  case class Lit(x: Int) extends Exp
  case class Add(l: Exp, r: Exp) extends Exp

  // Funcionalidad

  def eval(e: Exp): Int = e match {
    case Lit(i) => i
    case Add(l, r) => eval(l) + eval(r)
  }

  // Ejecución de funcionalidad sobre datos

  val expr: ADT.Exp = ADT.Add(ADT.Lit(1), ADT.Lit(3))

  val v1: Int = ADT.eval(expr)

}


package org.hablapps.fpinscala.hofs.homework
package solution

object Exercise3 {

  /**
   * Parte I.
   *
   * A continuación hay un ADT para representar operaciones aritméticas
   * con números enteros, se pide:
   *  - Extender el ADT para representar restas y multiplicaciones
   */
  sealed abstract class IntExpr
  case class IntVal(i: Int) extends IntExpr
  case class Add(e1: IntExpr, e2: IntExpr) extends IntExpr
  case class Subtract(e1: IntExpr, e2: IntExpr) extends IntExpr
  case class Times(e1: IntExpr, e2: IntExpr) extends IntExpr
  
  /** 
   * Parte II.
   *
   * Implementa los siguientes métodos que operan sobre el ADT
   * definido anteriormente utilizando pattern matching.
   */
  object PartII {

    /**
     * Implementa un pretty printer para el ADT tal que las expresiones se vean así:
     *
     * 3
     * (3 + 2)
     * (4 - 1)
     * (2 * (2 + 3))
     * ((1 + 8) + (5 - 2))
     * ...
     */
    def pretty[A](e: IntExpr): String = e match {
      case IntVal(i) => i.toString
      case Add(e1, e2) => s"(${pretty(e1)} + ${pretty(e2)})"
      case Subtract(e1, e2) => s"(${pretty(e1)} - ${pretty(e2)})"
      case Times(e1, e2) => s"(${pretty(e1)} * ${pretty(e2)})"
    }

    /**
     * El ADT forma una estructura de árbol, cuantas más operaciones anidemos, mayor
     * profundidad tendrá el árbol. Calcula dicha profundidad.
     *
     * depth(IntVal(1)) = 0
     * depth(Add(IntVal(1), IntVal(2))) = 1
     * depth(Add(IntVal(1), Add(IntVal(2), IntVal(3)))) = 2
     * depth(Add(Add(IntVal(0), IntVal(1)), Add(IntVal(2), IntVal(3)))) = 2
     * ...
     */
    def depth[A](e: IntExpr): Int = e match {
      case IntVal(i) => 0
      case Add(e1, e2) => math.max(depth(e1), depth(e2)) + 1
      case Subtract(e1, e2) => math.max(depth(e1), depth(e2)) + 1
      case Times(e1, e2) => math.max(depth(e1), depth(e2)) + 1
    }

    /** 
     * Evalua las expresiones aritméticas
     *
     * eval(IntVal(1)) = 1
     * eval(Add(IntVal(1), IntVal(2))) = 3
     * eval(Add(IntVal(1), Add(IntVal(2), IntVal(3)))) = 6
     * eval(Add(Add(IntVal(0), IntVal(1)), Add(IntVal(2), IntVal(3)))) = 6
     * ...
     */
    def eval[A](e: IntExpr): Int = e match {
      case IntVal(i) => i
      case Add(e1, e2) => eval(e1) + eval(e2)
      case Subtract(e1, e2) => eval(e1) - eval(e2)
      case Times(e1, e2) => eval(e1) * eval(e2)
    }
  }
  
  /** 
   * Parte III.
   *
   * Implementa el `fold` (catamorfismo) para este ADT y acontinuación
   * modulariza las funciones definidas en la Parte II.
   */
  object PartIII {

    def fold[B](e: IntExpr)
        (v: Int => B)
        (add: (B, B) => B)
        (sub: (B, B) => B)
        (times: (B, B) => B): B = e match {
      case IntVal(i) => v(i)
      case Add(e1, e2) => add(fold(e1)(v)(add)(sub)(times), fold(e2)(v)(add)(sub)(times))
      case Subtract(e1, e2) => sub(fold(e1)(v)(add)(sub)(times), fold(e2)(v)(add)(sub)(times))
      case Times(e1, e2) => times(fold(e1)(v)(add)(sub)(times), fold(e2)(v)(add)(sub)(times))
    }

    def pretty[A](e: IntExpr): String = fold[String](e)(_.toString)((e1, e2) => s"($e1 + $e2)")((e1, e2) => s"($e1 - $e2)")((e1, e2) => s"($e1 * $e2)")

    def depth[A](e: IntExpr): Int = fold[Int](e)(_ => 0)(math.max(_, _))(math.max(_, _))(math.max(_, _))

    def eval[A](e: IntExpr): Int = fold[Int](e)(identity)(_ + _)(_ - _)(_ * _)

  }

}
package org.hablapps.fpinscala.hofs.homework

// Prueba este ejercicio con el comando `test-tema1-ej3`
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
     * ((1 + 8) * (5 - 2))
     * ...
     */
    def pretty[A](e: IntExpr): String = ???

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
    def depth[A](e: IntExpr): Int = ???

    /** 
     * Evalua las expresiones aritméticas
     *
     * eval(IntVal(1)) = 1
     * eval(Add(IntVal(1), IntVal(2))) = 3
     * eval(Add(IntVal(1), Add(IntVal(2), IntVal(3)))) = 6
     * eval(Add(Add(IntVal(0), IntVal(1)), Add(IntVal(2), IntVal(3)))) = 6
     * ...
     */
    def eval[A](e: IntExpr): Int = ??? 
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
        (times: (B, B) => B): B = ???

    def pretty[A](e: IntExpr): String = ???

    def depth[A](e: IntExpr): Int = ???

    def eval[A](e: IntExpr): Int = ???

  }

}

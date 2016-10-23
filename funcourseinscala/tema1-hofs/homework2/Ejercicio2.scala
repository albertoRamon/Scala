package org.hablapps.fpinscala.hofs.homework

// Prueba este ejercicio con el comando `test-tema1-ej2`
object Exercise2 {

  /**
   * Parte I. 
   * 
   * Crea un ADT para representar árboles binarios con valores en los nodos.
   */

  sealed abstract class BinTree[A]
  case class Node[A](left: BinTree[A], a: A, right: BinTree[A]) extends BinTree[A]
  case class Empty[A]() extends BinTree[A]

  /**
   * Parte II.
   *
   * Implementa las siguientes funciones para el ADT que acabamos de crear
   * utilizando pattern matching
   */
  object PartII {

    // Suma los valores de los nodos de un árbol de enteros
    def sum(t: BinTree[Int]): Int = ???

    // Devuelve el valor máximo de un árbol de enteros
    def max(t: BinTree[Int]): Int = ???

    // Cuenta el número de `Empty`s de un árbol cualquiera
    def emptyTrees[A](t: BinTree[A]): Int = ???

    // Calcula la profundidad de un árbol cualquiera
    def depth[A](t: BinTree[A]): Int = ???

  }

  /**
   * Parte III.
   *
   * Implementa el `fold` (catamorfismo) para árboles binarios y
   * modulariza las funciones de la Parte II.
   */
  object PartIII {

    def fold[A,Z](t: BinTree[A])(
        leaf: Z,
        node: (Z, A, Z) => Z): Z = ???

    def sum(t: BinTree[Int]): Int = ???

    def max(t: BinTree[Int]): Int = ???

    def emptyTrees[A](t: BinTree[A]): Int = ???

    def depth[A](t: BinTree[A]): Int = ???
  }

}

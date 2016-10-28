package org.hablapps.fpinscala.hofs.homework
package solution

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
    def sum(t: BinTree[Int]): Int = t match {
      case Node(left, i, right) => sum(left) + i + sum(right)
      case Empty() => 0
    }

    // Devuelve el valor máximo de un árbol de enteros
    def max(t: BinTree[Int]): Int = t match {
      case Node(left, i, right) => List(max(left), max(right), i).max
      case Empty() => Int.MinValue
    }

    // Cuenta el número de `Empty`s de un árbol cualquiera
    def emptyTrees[A](t: BinTree[A]): Int = t match {
      case Node(left, a, right) => emptyTrees(left) + emptyTrees(right)
      case Empty() => 1
    }

    // Calcula la profundidad de un árbol cualquiera
    def depth[A](t: BinTree[A]): Int = t match {
      case Node(left, a, right) => math.max(depth(left), depth(right)) + 1
      case Empty() => 0
    }
    
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
        node: (Z, A, Z) => Z): Z = t match {
      case Node(left, a, right) => node(fold(left)(leaf, node), a, fold(right)(leaf, node))
      case Empty() => leaf
    }

    def sum(t: BinTree[Int]): Int = fold[Int, Int](t)(0, _ + _ + _)

    def max(t: BinTree[Int]): Int = fold[Int, Int](t)(Int.MinValue, List(_, _, _).max)

    def emptyTrees[A](t: BinTree[A]): Int = fold[A, Int](t)(1, (l, _, r) => l+r)
    
    def depth[A](t: BinTree[A]): Int = fold[A, Int](t)(0, (l, _, r) => math.max(l, r) + 1)
  }

}
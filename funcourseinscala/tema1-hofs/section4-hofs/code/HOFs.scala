package org.hablapps.fpinscala.hofs.hofs
package code

// 7. Aquí se muestra como se utilizan las funciones de orden superior
//    (HOFs) como mecanismo de modularidad. Las HOFs abstraen sobre
//    funciones como veremos a continuación.
object HOFs {

  import org.hablapps.fpinscala.hofs.funciones.code.Metodos.odd

  // (I) Funciones monolíticas
  def filterOdd(l: List[Int]): List[Int] = l match {
      case Nil => Nil
      case head :: tail =>
        if (odd(head)) head :: filterOdd(tail)
        else filterOdd(tail)
    }

  def filterPositive(l: List[Int]): List[Int] = l match {
      case Nil => Nil
      case head :: tail =>
        if (head > 0) head :: filterPositive(tail)
        else filterPositive(tail)
    }

  // (II) Abstracción 1
  def filter(l: List[Int])(cond: Int => Boolean): List[Int] =
    l match {
      case Nil => Nil
      case head :: tail =>
        if (cond(head)) head :: filter(l)(cond)
        else filter(l)(cond)
    }

  // (II) Abstracción 2
  def filter2[A](l: List[A])(cond: A => Boolean): List[A] =
    l match {
      case Nil => Nil
      case head :: tail =>
        if (cond(head)) head :: filter2(l)(cond)
        else filter2(l)(cond)
    }

  // (III) Funciones modularizadas
  def filterOddMod(l: List[Int]): List[Int] =
    filter2(l)(odd)
  def filterPositiveMod(l: List[Int]): List[Int] =
    filter2(l)(_ > 0)
}

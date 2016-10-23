package org.hablapps.fpinscala.hofs

// Ejercicio Catamorfismos: Dado el método 'fold' para listas,
// implementar 'filter' en listas usando 'fold'.

// Prueba este ejercicio con el comando `test-tema1-fold`
object Catamorphism {

  // Esta es la lista de la transparencia 48
  sealed abstract class List[A]
  object List {
    def apply[A](as: A*):List[A] = {
      if (as.isEmpty) Nil()
      else Cons(as.head, apply(as.tail: _*))
    }
  }
  case class Cons[A](head: A, tail: List[A]) extends List[A]
  case class Nil[A]() extends List[A]

  // Este es el fold de la transparencia 50
  def fold[A,B](l: List[A])(nil: B, cons: (A,B) => B): B = l match {
    case Nil() => nil
    case Cons(h, t) => cons(h, fold(t)(nil, cons))
  }

  def filter[A](l: List[A])(p: A => Boolean): List[A] = ???
  
  def comprobacion = {
    val l1 = List("","hola","que","","tal")
    val l2 = List(1,2,3,4,5)
    assert(filter(l1)(!_.isEmpty) == List("hola", "que", "tal"))
    assert(filter(l2)(_ % 2 != 0) == List(1, 3, 5))
  }

}
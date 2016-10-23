package org.hablapps.fpinscala.hofs.hofs
package code

// 8. Los catamorfismos son simplemente funciones de orden superior,
//    sin embargo, se tratan de funciones muy importantes, debido a
//    su genericidad (el catamorfismo se puede definir para cualquier
//    ADT) y a su significado (permite interpretar una estrucura a
//    través de su forma)
object Catamorfismo {

  // 8.1. Aquí vemos como está definido el catamorfismo para el tipo `List`
  def fold[A, B](l: List[A])
      (nil: B, cons: (A, B) => B): B =
    l match {
      case h :: t => cons(h, fold(t)(nil, cons))
      case Nil => nil
    }

  // 8.2. Podemos utilizar el catamorfismo para calcular un monton de cosas
  //      como por ejemplo la suma de una lista de enteros
  def suma(l: List[Int]): Int = fold[Int, Int](l)(0, _ + _)
  
}


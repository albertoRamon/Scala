package org.hablapps.fpinscala.typeclasses
package intro

/**
  El polimorfismo paramétrico a veces no es suficiente porque hace falta
  información concreta sobre el parámetro. Esa información se puede abstraer 
  mediante parametrización de valores y/o funciones. 
 */
package object templates {
  
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
//aplicamos 2º y 3º peldaño de la escalera
  def  collapse [A]  (l:List[A])(empty:A, combine:(A,A=>Boolean):A = 
	l match {
	case Nil =>empty
	case h :: t => combine (h, collapse(t)(empty , combine))
	}

trait Monoid[A] { //type class abs
	def empty: A
	def combine (a1:A, a2:A):A
}

trait intMonoid[Int] {  //type class para Int
	def empty: Int =0
	def combine (a1:A, a2:A):Int =a1,a2
}


 def  collapse [A]  (l:List[A])(monoid: Monoid[A]):A = 
	l match {
	case Nil =>Monoid.empty
	case h :: t => Monoid.combine (h, collapse(t)(empty , combine))
}

 object stringMonoid extends Monoid[String]{
	val empty:String=""
	def combien(a1:String,a2:String):String = a1+a2
}

  
  // Composición
  def sumaIntBis(l: List[Int]): Int = collapse(l)(intMonoid)

  def concatBis(l: List[String]): String = ???

  /**
    Para evitar tener que pasar una y otra vez la misma información sobre un 
    determinado parámetro, podemos agrupar la información requerida en una 
    nueva estructura y pasar únicamente una instancia de dicha estructura.
  */

  // Las operaciones que nos hacen falta son las correspondientes a un monoide.
  // Un monoide es un tipo de datos para el que podemos dar un zero y 
  // una operación binaria de composición (suma). Estas operaciones deben
  // satisfacer determinadas leyes: 1) elemento neutro y 2) asociatividad.
  // Un monoide define una clase de tipos (type class): aquellos para los que hay 
  // un zero y una operación binaria con esas propiedades.
  // trait Monoid
  
  // Una vez hecho esto, podemos proporcionar esta información de "golpe"
  // def collapse
  
  // Para poder utilizar la estructura debemos instanciar la typeclass `Monoid`
  // con los tipos correspondientes. La composición ya solo requiere pasar 
  // la instancia correspondiente
  // val intMonoid
  // object stringMonoid

  def sumaIntTC(l: List[Int]): Int = ???

  def concatBisTC(l: List[String]): String = ???

}

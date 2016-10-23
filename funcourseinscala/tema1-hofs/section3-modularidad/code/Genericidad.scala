package org.hablapps.fpinscala.hofs.modularidad
package code

// 5. Aquí se muestra como se utiliza el polimorfismo paramétrico
//    (genericidad) como mecanismo de modularidad. El polimorfismo
//    paramétrico abstrae sobre tipos como veremos a continuación.
object Genericidad {

  // 5.1. Se puede aplicar tanto a estructuras de datos...
  object Estructuras {
    // (I) Estructuras monolíticas
    sealed trait ListaString
    case class NilString() extends ListaString
    case class ConsString(
      elemento: String, 
      resto: ListaString) extends ListaString

    sealed trait ListaBoolean
    case class NilBoolean() extends ListaBoolean
    case class ConsBoolean(
      elemento: Boolean, 
      resto: ListaBoolean) extends ListaBoolean

    // (II) Abstracción
    sealed trait Lista[T]
    case class Nil[T]()    extends Lista[T]
    case class Cons[T](
      elemento: T     , 
      resto: Lista[T]   ) extends Lista[T]

    // (III) Estructuras modularizadas
    type ListaStringMod = Lista[String]
    type ListaBooleanMod = Lista[Boolean]
  }

  // 5.2. ... como a funciones.
  object Funciones {
    // (I) Funciones monolíticas
    def duplicateInt(l: List[Int]): List[Int] =
      l match {
        case Nil => Nil
        case head :: tail => 
          head :: head :: duplicateInt(tail)
      }

    def duplicateString(l: List[String]): List[String] = 
      l match {
        case Nil => Nil
        case head :: tail =>
          head :: head :: duplicateString(tail)
      }

    // (II) Abstracción
    def duplicate[A](l: List[A]): List[A] =
      l match {
        case Nil => Nil
        case head :: tail =>
          head :: head :: duplicate(tail)
      }

    // (III) Funciones modularizadas
    def duplicateIntMod(l: List[Int]): List[Int] =
      duplicate(l)
    def duplicateStringMod(l: List[String]): List[String] =
      duplicate(l)
  }
}

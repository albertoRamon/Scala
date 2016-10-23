package org.hablapps.fpinscala.typeclasses
package oo
package code

object AdaptersVsTypeClasses{

  // Un tipo propio sencillo para instanciar type classes y adaptadores

  case class Potato(color: (Int, Int, Int), size: Int)
  // Instances
  val p1: Potato = new Potato((10, 10, 10), 100)
  val p2: Potato = new Potato((10, 10, 10), 101)

  /**
   * (I) VERSIONES MONOLÍTICAS
   */
  object MonolythicPrograms{

    // Sobre tipos preexistentes

    def greatestChar(l: List[Char]): Option[Char] =
      l.foldLeft(Option.empty[Char]){
        (acc, x) => acc.fold(Option(x)){ 
          y => if (x < y) Option(y) else Option(x)
        }
      }

    def greatestInt(l: List[Int]): Option[Int] =
      l.sortWith(_>_).headOption
      
    def greatestString(l: List[String]): Option[String] =
      l.sortWith(_>_).headOption
      
    def greatestPotato(l: List[Potato]): Option[Potato] =
      l.sortWith(_.size>_.size).headOption
  }
      

  /** 
   * (II) PATRON RECURRENTE (TYPE CLASSES)
   */
  object RecurrentPatternWithTypeClasses{ 

    /**
     * Implementamos el patrón recurrente utilizando la type class Order
     * que ya vimos en el ejercicio anterior.
     */
    import pattern.code.Order
    import pattern.code.Order.syntax._

    def greatest[A: Order](l: List[A]): Option[A] =
      l.foldLeft(Option.empty[A]){
        (acc, x) => acc.fold(Option(x)){ 
          y => if (x < y) Option(y) else Option(x)
        }
      }
  }

  /** 
   * (III) PATRON RECURRENTE (ADAPTADORES)
   */
  object RecurrentPatternWithAdaptors{ 

    /**
     * Definimos la funcionalidad de comparación en términos de un adaptador,
     * en lugar de utilizar una type class
     */
    trait Order[A]{
      val unwrap: A
      def compare(other: A): Int
      // derived
      def greaterThan(t2: A): Boolean = compare(t2) > 0
      def equalThan(t2: A): Boolean = compare(t2) == 0
      def lowerThan(t2: A): Boolean = compare(t2) < 0
    }

    /**
     * Implementación del patrón recurrente utilizando el adaptador
     */
    def greatest[A](l: List[A])(wrap: A => Order[A]): Option[A] =
      l.foldLeft(None: Option[A]){
        (acc, x) => acc.fold(Option(x)){ 
          y => if (wrap(x).lowerThan(y)) Option(y) else Option(x)
        }
      }

    /** 
     * Implementación de la función de ordenación del ejercicio anterior
     * utilizando el adaptador
     */
    def sortAscending[A](l:List[A])(wrap: A => Order[A]): List[A] =
      l.sortWith((c1,c2) => wrap(c1).greaterThan(c2))
  }

  /** 
   * (III) VERSIONES MODULARIZADAS
   */
  object ModularPrograms{
    import RecurrentPatternWithAdaptors._
    
    // Instancias del adaptador
    
    implicit class IntOrder(val unwrap: Int) extends Order[Int] {
      def compare(i2: Int): Int = unwrap - i2
    }
    
    implicit class CharOrder(val unwrap: Char) extends Order[Char] {
      def compare(c2: Char) =
        if (unwrap > c2) 1
        else if (unwrap == c2) 0
        else -1
    }
    
    implicit class StringOrder(val unwrap: String) extends Order[String] {
      def compare(s2: String) =
        if (unwrap > s2) 1
        else if (unwrap == s2) 0
        else -1
    }

    implicit class PotatoOrder(val unwrap: Potato) extends Order[Potato] {
      def compare(other: Potato): Int = 
        if (unwrap.size == other.size) 0 
        else if (unwrap.size <= other.size) -1
        else 1
    }

    // Versiones modularizadas

    def greatestChar(l: List[Char]): Option[Char] =
      greatest(l)(CharOrder(_))

    def greatestInt(l: List[Int]): Option[Int] =
      greatest(l)(IntOrder(_))

    def greatestString(l: List[String]): Option[String] =
      greatest(l)(StringOrder(_))

    def greatestPotato(l: List[Potato]): Option[Potato] =
      greatest(l)(PotatoOrder(_))
  }

}



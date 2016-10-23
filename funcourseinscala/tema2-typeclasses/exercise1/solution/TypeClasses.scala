package org.hablapps.fpinscala.typeclasses
package solution

/**
 * Se quiere crear una type class de tipos que pueden ser representados mediante
 * un `String`. Deberemos implementar la type class con las siguientes partes:
 * - Parte I: Instancias
 * - Parte II: Sintaxis
 *
 * Más adelante se pide crear programas genéricos utilizando dicha type class
 */
object EjerciciosTypeClasses {

  trait Show[A] {
    def write(a: A): String
  }

  trait ShowInstances {
    // Parte I: Da instancias para la type class Show[_]
    // Implementa el invocador
    def apply[A](implicit ev: Show[A]) = ev

    // Da una instancia para `Int`
    implicit val intInstance: Show[Int] = new Show[Int] {
      def write(a: Int): String = a.toString
    }

    // Da una instancia para `String`
    implicit val stringInstance: Show[String] = new Show[String] {
      def write(a: String): String = a
    }

    // Da una instancia para `Option[A]` requiriendo una instancia de `Show[A]`
    implicit def optionInstance[A](implicit ev: Show[A]): Show[Option[A]] =
      new Show[Option[A]] {
        def write(o: Option[A]): String = o.fold("None")(a => s"Some(${ev.write(a)})")
      }
  }

  trait ShowSyntax {
    // Parte II: Proporciona una sintaxis para esta type class
    // (Nota): Puedes proporcionar tanto una función ordinaria como un operador
    // para `write`
    object syntax {
      implicit class ShowOps[A](a: A)(implicit ev: Show[A]) {
        def write: String = ev.write(a)
      }

      def write[A](a: A)(implicit ev: Show[A]): String = ev.write(a)
    }
  }

  object Show extends ShowInstances with ShowSyntax

  // Parte III: Escribe ejemplos de programas genéricos que utilicen esta
  // type class
  import Show.syntax._

  // Esta función debe devolver el primer elemento de la lista en formato `String`
  // (Nota): Este ejercicio requiere añadir "algo" a la signatura
  def writeFirstUnsafe[A: Show](l: List[A]): String =
    l.head.write
    // write(l.head)

  // La función anterior no era segura, ya que si la lista es vacía no hay `String`
  // válido posible.
  // (Nota): Este ejercicio requiere añadir "algo" a la signatura
  def writeFirstSafe[A: Show](l: List[A]): Option[String] =
    l.headOption.map(write(_))

}

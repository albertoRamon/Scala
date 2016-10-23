package org.hablapps.fpinscala.typeclasses
package exercises

/**
 * El objetivo de este ejercicio es familiarizarse con el manejo de type
 * classes que utilizan higher-kinds generics.
 *
 * En este caso se muestra una type class para trabajar con colecciones
 * de enteros.
 * En la parte I se pide hacer esa type class genérica en los elementos
 * que contiene la colección. Es decir, se quiere dar la posibilidad
 * de crear colecciones de otros tipos que no sean `Int`
 *
 * Ejecuta `test-tema2-constructors` para comprobar este ejercicio.
 */
object HomeworkTypeConstructors {

  object Initial {

    // Partimos de la type class para representar colecciones de enteros
    trait Lista[L] {
      def nil: L
      def cons(head: Int, tail: L): L
    }

    // Se define una sintaxis para `Lista` tal que pueda utilizarse `::`
    // para insertar elementos en la colección
    implicit class ListaOps[L](l: L)(implicit L: Lista[L]) {
      def ::(elem: Int): L = L.cons(elem, l)
    }

    // Ya podemos usar nuestra type class para crear un ejemplo
    def sample[L](implicit L: Lista[L]): L = 1 :: 2 :: 3 :: L.nil

    // Damos una instancia para el tipo `List[Int]`
    implicit object listMyListInstance extends Lista[List[Int]] {
      def nil = List.empty[Int]
      def cons(head: Int, tail: List[Int]) = head :: tail
    }

    // Damos una instancia para el tipo `Vector[Int]`
    implicit object vectorMyListInstance extends Lista[Vector[Int]] {
      def nil = Vector.empty[Int]
      def cons(head: Int, tail: Vector[Int]) = head +: tail
    }  

    // Ya podemos materializar el ejemplo anterior para nuestras dos implementaciones
    val sampleList: List[Int] = sample[List[Int]]
    val sampleVector: Vector[Int] = sample[Vector[Int]]
  }

  object PartI {

    /**
     * Part I.
     *
     * Crea una type class que permita trabajar con colecciones genéricas
     */
    trait Lista[L[_]]//{....}

    /**
     * Part II.
     *
     * Define una sintaxis para `Lista` tal que pueda utilizarse `::`
     * para insertar elementos en la colección
     */
    implicit class ListaOps[L[_], A](l: L[A])(implicit L: Lista[L]) // {... }

    /**
     * Part III.
     *
     * Utiliza la type class para crear 2 ejemplos de colecciones, una de
     * `Int` y otra de `String`
     */
    def sample[L[_]](implicit L: Lista[L]): L[Int] = ???
    def sample2[L[_]](implicit L: Lista[L]): L[String] = ???

    /**
     * Part IV.
     *
     * Crea una instancia de la type class para `List`
     */
    implicit object listMyListInstance extends Lista[List]// {...}

    /**
     * Part V.
     *
     * Crea una instancia de la type class para `Vector`
     */
    implicit object vectorMyListInstance extends Lista[Vector] //{....}

    /**
     * Part VI.
     *
     * Materializa los ejemplos anteriores para nuestras dos implementaciones
     * de la type class
     */
    val sampleList: List[Int] = ???
    val sampleVector: Vector[Int] = ???
    val sample2List: List[String] = ???
    val sample2Vector: Vector[String] = ???
  }

}

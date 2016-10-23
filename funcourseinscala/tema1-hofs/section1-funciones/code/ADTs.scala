package org.hablapps.fpinscala.hofs.funciones
package code

// 3. Los tipos se pueden combinar mediante operaciónes algebraicas como la
//    suma (+) o el producto (x), para conseguir obtener tipos mas complejos.
//    Los tipos, de hecho, forman un álgebra, por eso se les llama tipos de
//    datos algebraicos.
object ADTs {

  // 3.1. Una operación muy común con tipos es multiplicarlos, a continuación
  //      vemos algunos ejemplos de multiplicación de tipos.
  object Productos {
    // Int x Boolean
    type T1 = Tuple2[Int, Boolean]
    val t1: T1 = (3, true)

    // Unit x Int
    type T2 = (Unit, Int)
    val t2: T2 = ((), 4)

    // String x Int
    case class T3(name: String, age: Int)
    val t3: T3 = T3("John Doe", 26)
  }

  // 3.2. Otra operación muy común es la suma, a continuación se muestran
  //      ejemplos de suma de tipos.
  object Sumas {
    // Int + Boolean
    type T1 = Either[Int, Boolean]
    val t1: T1 = Left(4)

    // Unit + Int
    type T2 = Either[Unit, Int]
    val t2: T2 = Right(18)

    // Nothing + Boolean
    type T3 = Either[Nothing, Boolean]
    val t3: T3 = Right(true)
  }

  // 3.3. Los ADTs son una combinación de sumas y productos que producen tipos
  //      más complejos, a continuación vemos el caso general, es decir, cómo
  //      se definen ADTs en Scala.
  object CasoGeneral {

    // "Implementación" de Option en Scala
    // ¿Cuál es la estructura algebraica de Option?
    sealed abstract class Option[A]
    case class Some[A](a: A) extends Option[A]
    case class None[A]() extends Option[A]

    // "Implementación" de List en Scala
    // ¿Cuál es la estructura algebraica de List?
    sealed abstract class List[A]
    case class Cons[A](h: A, t: List[A]) extends List[A]
    case class Nil[A]() extends List[A]
  }
}

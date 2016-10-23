package org.hablapps.fpinscala.typeclasses
package constructors
package code

object SafeExpressions {

  object UnsafePrograms {

    /** 
     * La implementación de las expresiones aritméticas realizada anteriormente
     * no impide que se puedan crear expresiones mal formadas, que den lugar en 
     * tiempo de ejecución a una excepción. Esto pasa en todas las versiones: por
     * ejemplo, en la versión con ADTs:
     */
    object WithADTs{

      sealed trait Exp
      case class Lit(x: Int) extends Exp
      case class Add(l: Exp, r: Exp) extends Exp
      case class Bool(b: Boolean) extends Exp
      case class Ifte(cond: Exp, t: Exp, e: Exp) extends Exp

      val estoSiCompila: Exp = Add(Lit(3), Bool(true))

    }

    // Y en la versión con type classes

    object WithTypeClasses {

      trait Exp[E] {
        def lit(x: Int): E
        def add(l: E, r: E): E
        def bool(b: Boolean): E
        def ifte(cond: E, t: E, e: E): E
      }

      // Con esta solución se pueden crear expresiones erroneas
      // que fallarán en runtime
      def estoSiCompila[E](implicit E: Exp[E]): E =
        E.add(E.lit(3), E.bool(true))

    }

  }

  /** 
   * Para solucionarlo podemos parametrizar el tipo de datos, utilizando 
   * lo que se denomina un Generalized Algebraic Data Type (GADT).
   */
  object SafeADTs {

    // El parámetro A puede tomar tipos distintos en cada variante
    // del ADT. En eso consisten los GADTs.

    sealed trait Exp[A]
    case class Lit(x: Int) extends Exp[Int]
    case class Add(l: Exp[Int], r: Exp[Int]) extends Exp[Int]
    case class Bool(b: Boolean) extends Exp[Boolean]
    case class Ifte[A](cond: Exp[Boolean], t: Exp[A], e: Exp[A]) extends Exp[A]

    // Y ya no puedo crear expresiones erroneas
    // val estoNoCompila: Exp[Int] = Add(Lit(3), Bool(true))

    // ¡Y Ya no necesitamos el tipo `Value` para el intérprete `eval`!

    def eval[A](exp: Exp[A]): A = exp match {
      case Lit(i) => i
      case Add(l, r) => eval(l) + eval(r)
      case Bool(b) => b
      case Ifte(cond, t, e) =>
        if (eval(cond)) eval(t)
        else eval(e)
    }

    def print[A](e: Exp[A]): String = e match {
      case Lit(i) => i.toString
      case Add(l, r) => s"${print(l)} + ${print(r)}"
      case Bool(b) => b.toString
      case Ifte(cond, t, e) =>
        s"""|if (${print(cond)})
            |  ${print(t)}
            |else
            |  ${print(e)}""".stripMargin
    }
    
  }

  /**
   * En la solución mediante type classes, puedo conseguir algo similar a lo realizado
   * en la versión con GADTs. Para ello, parametrizamos la type class mediante un constructor
   * de tipos, en lugar de un tipo normal.
   */
  object SafeTypeclasses {

    // El parámetro `E[T]` puede entenderse como el tipo de una expresión aritmética 
    // que cuando se evalúe devolverá un valor de tipo `T`; el tipo `E[_]`, como un
    // tipo que permite representar expresiones aritméticas, es decir, como un lenguaje; 
    // y la type class `Expr[_[_]]`, por tanto, como la clase de los lenguajes de 
    // expresiones aritméticas.

    trait Expr[E[_]] {
      def lit(i: Int): E[Int]
      def add(e1: E[Int], e2: E[Int]): E[Int]
      def bool(b: Boolean): E[Boolean]
      def ifte[A](cond: E[Boolean], t: E[A], e: E[A]): E[A]
    }

    // Con esta nueva versión, ya no puedo crear expresiones artiméticas erroneas:

    // def estoNoCompila[E[_]](implicit E: Expr[E]): E[Int] =
    //   E.add(E.lit(3), E.bool(true))

    def estoSiCompila[E[_]](implicit E: Expr[E]): E[Int] =
      E.add(E.lit(3), E.lit(4))

    // Para implementar los intérpretes tengo que proporcionar un constructor de tipos.
    // En el caso de la evaluación, el objetivo es evaluar una expresión del tipo 
    // `E[Int]` a `Int`, y una expresión de tipo `E[String]` a `String`. Por tanto, 
    // lo que nos hace falta es un constructor "identidad".

    implicit object Eval extends Expr[Id] {
      def lit(i: Int): Int = i
      def add(e1: Int, e2: Int): Int = e1 + e2
      def bool(b: Boolean): Boolean = b
      def ifte[A](cond: Boolean, t: A, e: A): A = if (cond) t else e
    }

    // Interpretación
    val evalInt: Int = estoSiCompila[Id]

    // En el caso de la interpretación a Strings, independientemente del tipo que 
    // devuelva la expresión aritmética, el resultado tiene que ser un `String`. 
    // Por tanto, puedo utilizar un constructor de tipos "constante".
    
    type StringF[A] = String

    implicit object Print extends Expr[StringF] {
      def lit(i: Int) = i.toString
      def add(e1: String, e2: String) = s"$e1 + $e2"
      def bool(b: Boolean) = b.toString
      def ifte[A](cond: String, t: String, e: String): String =
        s"""|if ($cond)
            |  $t
            |else
            |  $e""".stripMargin

    }

    // Interpretación
    val printInt: String = estoSiCompila[StringF]

  }

}

package org.hablapps.fpinscala.typeclasses
package data
package code

object Visitors {

  // Si queremos evaluar una expresión aritmética definida de manera abstracta
  // por medio de la factoría, podemos crear una instancia de `ADT.Expr` mediante
  // esta type class y después evaluarla con el propio intérprete de `ADT.Expr`.
  object IndirectEvaluation{
    import Factories._
  
    // val v: Int = ADT.eval(expr(Exp.ADTExpInstance))
    val v: Int = ADT.eval(expr)
  }

  // ¡Pero no tenemos por qué dar este rodeo! Podemos implementar directamente el 
  // intérprete como una instancia de la type class `Exp`
  object DirectEvaluation{

    // Instanciación
    import Factories._

    implicit object eval extends Exp[Int] {
      def lit(i: Int): Int = i
      def add(e1: Int, e2: Int): Int = e1 + e2
    } 

    // Interpretación
    import Factories._

    val v1: Int = expr(eval)

    // or directly
    import Exp.syntax._
    val v: Int = lit(1) + lit(3)
  }

}
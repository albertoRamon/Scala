package org.hablapps.fpinscala.typeclasses
package data
package code

object Factories {

  // La type class `Exp` implementa la clase de los tipos `E` que 
  // pueden representar expresiones aritméticas. La funcionalidad que 
  // proporciona esta type class consiste precisamente en los constructores
  // de las expresiones aritméticas. Puede entenderse, por tanto, como
  // una factoría abstracta de expresiones. 
  trait Exp[E] {
    // 1. Abstract
    def lit(i: Int): E
    def add(e1: E, e2: E): E

    // 2. Concrete
  }

  object Exp extends ExpInstances
    with ExpSyntax
    with ExpLaws

  // 3. Instances
  trait ExpInstances {
    def apply[E](implicit ev: Exp[E]) = ev

    // El tipo de expresiones con el que estuvimos trabajando en los ejemplos
    // anteriores es un miembro de esta type class
    implicit object ADTExpInstance extends Exp[ADT.Exp]{
      def lit(i: Int): ADT.Exp = ADT.Lit(i)
      def add(e1: ADT.Exp, e2: ADT.Exp): ADT.Exp = ADT.Add(e1,e2)
    }
  }

  // 4. Syntax
  trait ExpSyntax {
    object syntax{
      def lit[E](i: Int)(implicit E: Exp[E]) = E.lit(i)

      implicit class BinOps[E](e1: E)(implicit E: Exp[E]){
        def +(e2: E) = E.add(e1,e2)
      }
    }
  }

  // 5. Laws
  trait ExpLaws {}

  // Ejemplo de expresión definida de manera abstracta
  import Exp.syntax._
  def expr[E: Exp]: E = lit(3) + lit(7)

  // Interpretación de expresiones

  // Obtención de la expresión concreta
  val expr1: ADT.Exp = expr[ADT.Exp] // (Exp.ADTExpInstance)

}

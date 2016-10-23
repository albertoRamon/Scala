package org.hablapps.fpinscala.typeclasses
package data

package object templates {

  // La type class `Exp` implementa la clase de los tipos `E` que 
  // pueden representar expresiones aritméticas. La funcionalidad que 
  // proporciona esta type class consiste precisamente en los constructores
  // de las expresiones aritméticas. Puede entenderse, por tanto, como
  // una factoría abstracta de expresiones. 

  // Vamos a crear la type class
  // 1. Abstract
  // 2. Concrete
  // 3. Instances
  // 3.1 El tipo de expresiones con el que estuvimos trabajando en los ejemplos
  //     anteriores es un miembro de esta type class
  // 4. Syntax
  // 5. Laws

  // Ejemplo de expresión definida de manera abstracta
  // def expr

  // Interpretación de expresiones
  import code.ADT
  val expr1: ADT.Exp = ???

  // Si queremos evaluar una expresión aritmética definida de manera abstracta
  // por medio de la factoría, podemos crear una instancia de `ADT.Expr` mediante
  // esta type class y después evaluarla con el propio intérprete de `ADT.Expr`.
  import code.Factories._

  val v: Int = ???

  // ¡Pero no tenemos por qué dar este rodeo! Podemos implementar directamente el 
  // intérprete como una instancia de la type class `Exp`

  val v1: Int = expr(???)
  val v2: Int = ???

}

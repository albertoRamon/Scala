package org.hablapps.fpinscala
package lenguajes
package templates

object ImperativePrograms{


  object Impure{

    def program(a: String): Boolean = {
      val i: Int = a.length
      val j: Int = i + 1 
      j > 0
    }

  }

  object Pure{
    import Monad.Syntax._

    def program[P[_]: Monad](a: String): P[Boolean] = ???

  }

  object ModularImpure{

    def program(a: String): Boolean = ???
  }

}

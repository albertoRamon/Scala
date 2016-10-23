package org.hablapps.fpinscala.hofs.funciones
package code

// 2. La recursividad es un elemento clave en la programación funcional. Los
//    problemas asociados a esta técnica residen en el consumo poco eficiente
//    de espacio en la pila. Podemos eliminar esta problemática con Tail Recursion.
object Recursividad {

  // 2.1. La optimización Tail Recursive puede llevarse a cabo cuando la última
  //      llamada que se realiza en el cuerpo de la función es la propia llamada
  //      recursiva. De no ser así, nos podríamos meter en problemas.
  def factorial(n: Int): Int =
    if (n > 1)
      n * factorial(n-1)
    else
      1

  // 2.2. Existe una versión Tail Recursive (stack safe) para cualquier función
  //      recursiva, aunque no siempre resulta trivial llegar a ella.
  def factorial2(n: Int): Int = {
    @scala.annotation.tailrec
    def go(acc: Int, _n: Int): Int = {
      if (_n > 1)
        go(_n * acc, _n - 1)
      else
        acc
    }
    go(1, n)
  }

}

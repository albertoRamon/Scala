package org.hablapps.fpinscala.hofs

// Prueba este ejercicio con el comando `test-tema1-comp`
object Composition {

  // Ejercicio composición de funciones: Implementar
  // 'compose' y después implementar 'andThen' a partir de 'compose':
  def compose[A,B,C](g: B => C, f: A => B): A => C = (a:A) =>g(f(a))
  def andThen[A,B,C](f: A => B, g: B => C): A => C = compose (g,f)

}
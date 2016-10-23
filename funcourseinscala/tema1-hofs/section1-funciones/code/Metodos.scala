package org.hablapps.fpinscala.hofs.funciones
package code

// 1. Vamos a ver algunos ejemplos de cómo podemos
//    definir funciones como métodos en Scala
object Metodos {

  // 1.1. Calculamos la longitud de una cadena de caracteres
  def length(s: String): Int = s.length

  // 1.2. Sumamos dos números enteros
  def add(i: Int, j: Int): Int = i + j

  // 1.3. Multiplicamos dos números enteros
  def times(i: Int, j: Int): Int = i * j

  // 1.4. Comprobamos si un número entero es impar
  def odd(i: Int): Boolean = i % 2 == 1

  // 1.5. Comprobamos si un número entero es par
  def even(i: Int): Boolean = !odd(i)

  // 1.6. Ejemplo de función 0-aria, para representar el número 5
  def five: Int = 5
}

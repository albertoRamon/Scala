package org.hablapps.fpinscala.hofs.hofs
package code

// 6. Vamos a ver algunos como representar funciones como objetos
object Objetos {

  // 6.1. Teniendo en cuenta las funciones definidas en `Metodos.scala`
  def length(s: String): Int = s.length
  def add(i: Int, j: Int): Int = i + j
  def times(i: Int, j: Int): Int = i * j
  def odd(i: Int): Boolean = i % 2 == 1
  def even(i: Int): Boolean = !odd(i)
  def five: Int = 5

  // 6.2. Representar las mismas funciones utilizando objetos
  val lengthObj: String => Int = _.length
  val addObj: (Int, Int) => Int = _ + _
  val timesObj: (Int, Int) => Int = _ * _
  val oddObj: Int => Boolean = _ % 2 == 1
  val evenObj: Int => Boolean = !odd(_)
  val fiveObj: Unit => Int = _ => 5

  // 6.3. Obtener los objetos a través de la `eta expansion` sobre
  //      los métodos ya existentes
  val lengthExp = length _
  val addExp = add _
  val timesExp = times _
  val oddExp = odd _
  val evenExp = even _
  val fiveExp = five _
}

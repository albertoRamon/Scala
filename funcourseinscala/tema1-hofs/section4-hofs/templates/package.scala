package org.hablapps.fpinscala.hofs.hofs

package object templates {

  // 6. Vamos a ver algunos como representar funciones como objetos

  // 6.1. Teniendo en cuenta las funciones definidas en `Metodos.scala`
  def length(s: String): Int = s.length
  def add(i: Int, j: Int): Int = i + j
  def times(i: Int, j: Int): Int = i * j
  def odd(i: Int): Boolean = i % 2 == 1
  def even(i: Int): Boolean = !odd(i)
  def five: Int = 5

  // 6.2. Representar las mismas funciones utilizando objetos
  val lengthObj: String => Int = (s:String)=>s.length
  val addObj: (Int, Int) => Int = (i1,i2) =>i1+i2
  val timesObj: (Int, Int) => Int = _*_
  //val oddObj: Int => Boolean = ???
  //val evenObj: Int => Boolean = ???
  //val fiveObj: Unit => Int = ???

  // 6.3. Obtener los objetos a través de la `eta expansion` sobre
  //      los métodos ya existentes
  val lengthExp: String => Int = ???
  val addExp: (Int, Int) => Int = ???
  val timesExp: (Int, Int) => Int = ???
  val oddExp: Int => Boolean = ???
  val evenExp: Int => Boolean = ???
  val fiveExp: () => Int = ???


  // 7. Aquí se muestra como se utilizan las funciones de orden superior
  //    (HOFs) como mecanismo de modularidad. Las HOFs abstraen sobre
  //    funciones como veremos a continuación.

  // (I) Funciones monolíticas
//ocje una lista de enteros y quita los pares
  def filterOdd(l: List[Int]): List[Int] = l match {
      case Nil => Nil
      case head :: tail =>
        if (odd(head)) 
			head :: filterOdd(tail)
        else 
			filterOdd(tail)
    }

  def filterPositive(l: List[Int]): List[Int] = l match {
      case Nil => Nil
      case head :: tail =>
        if (head > 0) 
			head :: filterPositive(tail)
        else 
			filterPositive(tail)
    }

  // (II) Abstracción 1
//aunque es innecesario, separar los parámetros en dos grupos, 
// ayuda a inferir los tipos de una función
  def filter(l: List[Int])(cond: Int => Boolean): List[Int] =
	l match {
      case Nil => Nil
      case head :: tail =>
        if (cond(head))
			head :: filter(tail)(cond)
        else 
			filter(tail)(cond)
}
//filfer (List (1,2,3,4,5))(_%2==1)
//filfer (List (1,2,3,4,5))(odd)
//filfer (List (1,2,3,4,5))(_<3)

//ahora si que hace falta separarlo en dos tipos
  def filter2[A](l: List[A])(cond: A => Boolean): List[A] =
	l match {
      case Nil => Nil
      case head :: tail =>
        if (cond(head))
			head :: filter2(tail)(cond)
        else 
			filter2(tail)(cond)
}
//filfer (List ("aa","a"))(_.Length ==1)

// Si que funcionaria filfer si lo juntamos en un parametro
// si especificamos el tipos del parametro 
// filter [Srting] (List ("aa","a"),_.Length ==1)
// filter  (List ("aa","a"), {x[String]=>x.Length ==1})

  // (II) Abstracción 2

  // (III) Funciones modularizadas


  // 8. Los catamorfismos son simplemente funciones de orden superior,
  //    sin embargo, se tratan de funciones muy importantes, debido a
  //    su genericidad (el catamorfismo se puede definir para cualquier
  //    ADT) y a su significado (permite interpretar una estrucura a
  //    través de su forma)

  // 8.1. Aquí vemos como está definido el catamorfismo para el tipo `List`
  def fold[A, B](l: List[A])
      (nil: B, cons: (A, B) => B): B =
    l match {
      case h :: t => cons(h, fold(t)(nil, cons))
      case Nil => nil
    }

  // 8.2. Podemos utilizar el catamorfismo para calcular un monton de cosas
  //      como por ejemplo la suma de una lista de enteros
  def suma(l: List[Int]): Int = fold[Int, Int](l)(0, _ + _)

}

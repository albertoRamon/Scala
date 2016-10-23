package org.hablapps.fpinscala.hofs.funciones

package object templates {

  // 1. Vamos a ver algunos ejemplos de cómo podemos
  //    definir funciones como métodos en Scala

  // 1.1. Calculamos la longitud de una cadena de caracteres
def longitud (s:String):Int = s.length
  // 1.2. Sumamos dos números enteros




  // 2. La recursividad es un elemento clave en la programación funcional. Los
  //    problemas asociados a esta técnica residen en el consumo poco eficiente
  //    de espacio en la pila. Podemos eliminar esta problemática con Tail Recursion.

  // 2.1. La optimización Tail Recursive puede llevarse a cabo cuando la última
  //      llamada que se realiza en el cuerpo de la función es la propia llamada
  //      recursiva. De no ser así, nos podríamos meter en problemas.
def factorial(n:Int):Int = {
if (n>1)
 n*factorial(n-1)
else 
 1
}
  // 2.2. Existe una versión Tail Recursive (stack safe) para cualquier función
  //      recursiva, aunque no siempre resulta trivial llegar a ella.
def factorial2(_n:Int):Int = {
	@scala.annotation.tailrec
	//se opcimiza igualemtne, solo sirve para verificar que si es recursiva, sino lo fuera
	// nos dará un error
	def go(n:Int, acc:Int):Int = {
	if (n>1)
	 	go (n-1,n*acc)
	else 
	 	acc
	}
	go(_n,1)
}



  // 3. Los tipos se pueden combinar mediante operaciónes algebraicas como la
  //    suma (+) o el producto (x), para conseguir obtener tipos mas complejos.
  //    Los tipos, de hecho, forman un álgebra, por eso se les llama tipos de
  //    datos algebraicos.

    // 3.1. Una operación muy común con tipos es multiplicarlos, a continuación
    //      vemos algunos ejemplos de multiplicación de tipos.

    // Int x Boolean
type T1=Tuple2[Int,Boolean] //Usamos un type Alias
// Hay que dar valor a los dos --> hay el producto cartesiano de combinatorias
//val t1:T1 =(5,true)
//val t2:T1 =(5,true)
//val t3:T1 =(5,true)


    // Unit x Int
type T2=(Unit, Int)

    // String x Int
case class T3(s:String,i:Int)
//val t1:T3=T3("Hola", 1)


    // 3.2. Otra operación muy común es la suma, a continuación se muestran
    //      ejemplos de suma de tipos.
//el tipo suma se llama Either ¿¿??
    // Int + Boolean
type T4=Either[Int,Boolean]  //será uno de los dos tipos ¡¡
//val t1: T4=Left(25)
//val t2: T4=Right(true)

    // Unit + Int
//falta
sealed trait T5
case class CaseUnit (u:Unit) extends T5
case class CaseInt (i:Int) extends T5
//val t1:T5=CaseUnit(())
//val t2:T5=CaseInt(165)

    // Nothing + Boolean

    // 3.3. Los ADTs son una combinación de sumas y productos que producen tipos
    //      más complejos, a continuación vemos el caso general, es decir, cómo
    //      se definen ADTs en Scala.

    // "Implementación" de Option en Scala
    // ¿Cuál es la estructura algebraica de Option?
//sealed trait Option [A] // |A| + 1
//case class Some[A](a:A) extends Option[A]
//case class None[A]() extends Option[A]
//val t3:Option[Int]=Some(8)
//val t4:Option[Int]=None

//Añade un hueco mas al tipo, se usa para control de errores
def divide (n:Int, d:Int):Option[Int] =
if (d==0)
  None
else
  Some (n/d)


    // "Implementación" de List en Scala
    // ¿Cuál es la estructura algebraica de List?
sealed trait List[A]
case class Cons[A] (head:A, tail:List[A]) extends List[A]
case class Nil[A]() extends List[A]





}

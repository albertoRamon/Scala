package org.hablapps.fpinscala.hofs.modularidad

package object templates {

  // 4. Aquí se muestra como se utilizan las funciones ordinarias
  //    como mecanismo de modularidad. Las funciones ordinarias
  //    abstraen sobre valores como veremos a continuación.

  // Creamos una configuración dummy para utilizarla en el ejemplo
  object config {
    def get(s: String): Option[String] = s match {
      case "URL" => Option("my.awesome.uri")
      case "PORT" => Option("9000")
      case _ => None
    }
  }

  // (I) valores monolíticos
//se repite codigo
  val url: String = config.get("URL") match {
    case Some(u) => u
    case None => "default.url"
  }

  val port: String = config.get("PORT") match {
    case Some(p) => p
    case None => "8080"
  }

  // (II) Abstracción
def getconfigProperty (key:String, default:String)=
config.get(key) match {
case Some(p) => p
case None =>default
}



  // (III) Valores modularizados
val urlMod:String = getconfigProperty("URL","default.url" )
val portMod:String = getconfigProperty("PORT","8080" )
//urlMod


  // 5. Aquí se muestra como se utiliza el polimorfismo paramétrico
  //    (genericidad) como mecanismo de modularidad. El polimorfismo
  //    paramétrico abstrae sobre tipos como veremos a continuación.

  // 5.1. Se puede aplicar tanto a estructuras de datos...

  // (I) Estructuras monolíticas
  sealed trait ListaString
  case class NilString() extends ListaString
  case class ConsString(
    elemento: String, 
    resto: ListaString) extends ListaString

  sealed trait ListaBoolean
  case class NilBoolean() extends ListaBoolean
  case class ConsBoolean(
    elemento: Boolean, 
    resto: ListaBoolean) extends ListaBoolean

  // (II) Abstracción
//  sealed trait Lista[A]
//  case class Nil[A]() extends Lista[A]
//  case class Cons[A](
//    elemento: A, 
//    resto: Lista[A]) extends Lista[A]

//type ListaStringMod =Lista[String]


  // (III) Estructuras modularizadas

  // 5.2. ... como a funciones.

  // (I) Funciones monolíticas
  def duplicateInt(l: List[Int]): List[Int] =
    l match {
      case Nil => Nil
      case head :: tail => 
        head :: head :: duplicateInt(tail)
    }

  def duplicateString(l: List[String]): List[String] = 
    l match {
      case Nil => Nil
      case head :: tail =>
        head :: head :: duplicateString(tail)
    }

  // (II) Abstracción

  // (III) Funciones modularizadas

}

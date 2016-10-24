
//Products
type T1=Tuple2[Int, Boolean]
type T2=(Int, Boolean)
val x:T1=(3,true)
val y:T2=(3,true)


//Sums
type T3 =Either[Int,Boolean]
val z:T3=Left(4)
val u:T3=Right(true)


// Sumn and Products
sealed trait Color
  case object Red extends Color
  case object Blue extends Color

sealed  trait Pet
  case class Cat (name:String) extends Pet
  case class Fish (name:String,color:Color) extends Pet
  case class Squid (name:String, age:Int) extends Pet

//uso1
val myCat:Pet =Cat("minino")
val myColor:Color=Red
val myFish:Pet =Fish("nemo",myColor)


//uso2
type mascota =List[(Color, Pet)]
//val myMascota:mascota= List(myColor,myCat)
// duda


//Option
// add nothing to an type, can be used to error control
val t3:Option[Int]=Some(8)
val t4:Option[Int]=None


type intNullable=Option[Int]
def divide (num:Int, deno:Int):intNullable = {
  if (deno == 0)
    None
  else
    Some(num / deno)
}
divide(3,0)


//Option + sealed trait Color
// DUDA
/*
sealed trait Option[Color]
  case class Red2 extends Option[Color]
  case class Blue2 extends Option[Color]
 */



package org.hablapps.fpinscala
package lenguajes.test

import org.scalatest._
import lenguajes.io._

class Ejercicio2Spec extends FlatSpec with Matchers {

  import IOState._

  "WriteANumber" should "leer el número 3 y decir que es impar" in {
    val ios = IOState("3" :: Nil, Nil)
    Program1.Pure.writeANumber[IOTrans].exec(ios) shouldBe IOState(Nil, "3 es un número impar" :: Nil)
  }

  it should "leer el número 4 y decir que es par" in {
    val ios = IOState("4" :: Nil, Nil)
    Program1.Pure.writeANumber[IOTrans].exec(ios) shouldBe IOState(Nil, "4 es un número par" :: Nil)
  }

  "WriteANumber2" should "leer el número 3 y decir que es impar" in {
    val ios = IOState("3" :: Nil, Nil)
    Program2.Pure.writeANumberBis[IOTrans].exec(ios) shouldBe IOState(Nil, "3 es un número impar" :: "Introduce un número por favor:" :: Nil)
  }

  it should "leer el número 4 y decir que es par" in {
    val ios = IOState("4" :: Nil, Nil)
    Program2.Pure.writeANumberBis[IOTrans].exec(ios) shouldBe IOState(Nil, "4 es un número par" :: "Introduce un número por favor:" :: Nil)
  }

  "ReadUntilExit" should "leer cadenas de texto hasta encontrar `exit`" in {
    val ios = IOState("uno" :: "dos" :: "tres" :: "exit" :: Nil, Nil)
    Program3.Pure.readUntilExit[IOTrans].exec(ios) shouldBe IOState(Nil, Nil)
  }

  it should "parar de leer cuando encuentra un `exit`" in {
    val ios = IOState("uno" :: "dos" :: "tres" :: "exit" :: "otro" :: Nil, Nil)
    Program3.Pure.readUntilExit[IOTrans].exec(ios) shouldBe IOState("otro" :: Nil, Nil)
  }

  "ReadUntilExit2" should "leer cadenas de texto hasta encontrar `exit`" in {
    val ios = IOState("uno" :: "dos" :: "tres" :: "exit" :: Nil, Nil)
    Program4.Pure.readUntilExit[IOTrans].exec(ios) shouldBe IOState(Nil, "exit" :: "tres" :: "dos" :: "uno" :: Nil)
  }

  it should "parar de leer cuando encuentra un `exit`" in {
    val ios = IOState("uno" :: "dos" :: "tres" :: "exit" :: "otro" :: Nil, Nil)
    Program4.Pure.readUntilExit[IOTrans].exec(ios) shouldBe IOState("otro" :: Nil, "exit" :: "tres" :: "dos" :: "uno" :: Nil)
  }

}

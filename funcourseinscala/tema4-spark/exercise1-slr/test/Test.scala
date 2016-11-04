package org.hablapps.fpinscala.spark
package test

import org.scalatest._
import org.apache.spark._

class SLRTest extends FlatSpec with Matchers {

  val conf = new SparkConf().setMaster("local").setAppName("Simple Linear Regression")
  val sc = new SparkContext(conf)

  val slr = new EjercicioSLR(sc)

  "SLR" should "calcular la ecuación de la recta correctamente" in {
    slr.a shouldBe 1.5
    slr.b shouldBe 2.0
  }

}

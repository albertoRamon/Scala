package org.hablapps.fpinscala.spark
package test

import org.scalatest._
import org.apache.spark._

class QuijoteTest extends FlatSpec with Matchers {

  val conf = new SparkConf().setMaster("local").setAppName("Quijote")
  val sc = new SparkContext(conf)

  val quijote = new EjercicioQuijote(sc)

  "Top ten" should "devolver las 10 palabras más usadas del quijote" in {
    quijote.topTen.toList shouldBe List((2975,"que"), (2780,"de"), (2479,"y"), (1406,"la"), (1401,"a"), (1177,"el"), (1119,"en"), (822,"no"), (753,"se"), (688,"los"))
  }

  "Top ten greater than 3" should "devolver las 10 palabras más usadas del quijote con longitud > 3" in {
    quijote.topTenGreaterThan3.toList shouldBe List((335,"como"), (197,"dijo"), (195,"porque"), (180,"para"), (175,"hab�a"), (162,"vuestra"), (159,"Quijote,"), (157,"respondi�"), (152,"bien"), (151,"todo"))
  }

}

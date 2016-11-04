package org.hablapps.fpinscala.spark
package test

import org.scalatest._
import org.apache.spark._

class HomeworkTest extends FlatSpec with Matchers {

  val conf = new SparkConf().setMaster("local").setAppName("Homework")
  val sc = new SparkContext(conf)

  val homework = new Homework(sc)

  "Three Main Interests" should "devolver las 3 palabras mas buscadas por cada usuario" in {
    homework.usersThreeMainInterestsInMem(5) shouldBe List("keyword23", "keyword100", "keyword48")
    homework.usersThreeMainInterestsInMem(10) shouldBe List("keyword48", "keyword23", "keyword21")
    homework.usersThreeMainInterestsInMem(15) shouldBe List("keyword80", "keyword23", "keyword100")
  }

}

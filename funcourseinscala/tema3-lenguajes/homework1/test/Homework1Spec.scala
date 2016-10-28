package org.hablapps.fpinscala.lenguajes
package test

import org.scalatest._
import Homework1._

class Homework1Spec extends FlatSpec with Matchers {

  "Move" should "mover un fichero de un path a otro" in {
    val fs = new MapFS(Map("file1" -> "contenido file1"))
    move[org.hablapps.fpinscala.typeclasses.Id]("file1", "file2")(fs, implicitly)
    fs.filesystem shouldBe Map("file2" -> "contenido file1")
  }

  "Merge2" should "juntar el contenido de dos ficheros en un tercero" in {
    val fs = new MapFS(Map(
      "file1" -> "contenido file1",
      "file2" -> "contenido file2"))
    merge2[org.hablapps.fpinscala.typeclasses.Id]("file1", "file2", "file3")(fs, implicitly)
    fs.filesystem shouldBe Map(
      "file1" -> "contenido file1",
      "file2" -> "contenido file2",
      "file3" -> "contenido file1contenido file2")
  }

  "Merge2Bis" should "juntar el contenido de dos ficheros en un tercero" in {
    val fs = new MapFS(Map(
      "file1" -> "contenido file1",
      "file2" -> "contenido file2"))
    merge2bis[org.hablapps.fpinscala.typeclasses.Id]("file1", "file2", "file3")(fs, implicitly) shouldBe 30
    fs.filesystem shouldBe Map(
      "file1" -> "contenido file1",
      "file2" -> "contenido file2",
      "file3" -> "contenido file1contenido file2")
  }

  "MergeN" should "juntar el contenido de N ficheros en un destino" in {
    val fs = new MapFS(Map(
      "file1" -> "contenido file1",
      "file2" -> "contenido file2",
      "file3" -> "contenido file3"))
    mergeN[org.hablapps.fpinscala.typeclasses.Id]("file1" :: "file2" :: "file3" :: Nil, "dest")(fs, implicitly)
    fs.filesystem shouldBe Map(
      "file1" -> "contenido file1",
      "file2" -> "contenido file2",
      "file3" -> "contenido file3",
      "dest" -> "contenido file1contenido file2contenido file3")
  }

}

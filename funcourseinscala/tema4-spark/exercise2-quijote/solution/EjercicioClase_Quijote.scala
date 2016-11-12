package org.hablapps.fpinscala.spark
package solution

import org.apache.spark._, rdd.RDD

class EjercicioQuijote(sc: SparkContext) extends Serializable {

  /**
   * Parte I.
   *
   * Crea un RDD a partir de un fichero que contiene "El Quijote",
   * cuyo path es "tema4-spark/data/quijote.txt"
   */
  val lines: RDD[String] = sc.textFile("tema4-spark/data/quijote.txt")

  /**
   * Parte II.
   *
   * Obtén un RDD con las palabras del quijote, que no estén vacías
   */
  val nonEmptyWords: RDD[String] =
    lines
      .flatMap(_.split(" "))
      .filter(!_.isEmpty)

  /**
   * Parte III.
   *
   * Crea un RDD de clave/valor, siendo la clave las palabras
   * y el valor, cuantas veces aparecen en el texto.
   */
  val wordCount: RDD[(String, Int)] =
    nonEmptyWords
      .map((_, 1))
      .reduceByKey(_ + _)

  /**
   * Parte IV.
   *
   * Ordena el conteo de palabras anterior de palabras más usadas
   * a palabras menos usadas
   */
  val mostUsedWords: RDD[(Int, String)] =
    wordCount
      .map(_.swap)
      .sortByKey(false)

  /**
   * Parte V.
   *
   * Calcula las 10 palabras más utilizadas de "El Quijote"
   */
  val topTen: Array[(Int, String)] = mostUsedWords take 10

  /**
   * Parte VI.
   *
   * Calcula las 10 palabras más utilizadas de "El Quijote" que
   * tengan más de 3 caracteres
   */
  val topTenGreaterThan3: Array[(Int, String)] = mostUsedWords filter (_._2.length > 3) take 10

  println(s"""|TOP TEN:
              |\t${topTen.mkString("\n\t")}
              |
              |TOP TEN (>3):
              |\t${topTenGreaterThan3.mkString("\n\t")}""".stripMargin)

  sc.stop
}

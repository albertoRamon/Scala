package org.hablapps.fpinscala.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.SparkContext

/* Ejecuta este ejercicio con `test-tema4-homework` */
class Homework(sc: SparkContext) extends Serializable {

  /**
   * Parte I
   * Lee el fichero 'PagesKeywords.csv' y conviértelo en un
   * RDD[(String, List[String])] donde a cada página se le asocia
   * su lista de keywords.
   */
  val pagesKeywordsLines: RDD[String] = ???
  val pagesKeywords: RDD[(String, List[String])] = ???

  /**
   * Parte II
   * Lee el fichero 'PagesVisits.csv' y conviértelo en un
   * RDD[(Int, String)] donde queden reflejadas las visitas
   * de los usuarios
   */
  val visitsToPagesLines: RDD[String] = ???
  val visitsToPages: RDD[(Int,String)] = ???

  /**
   * Parte III
   * Asociar a cada usuario (id) un map que, para cada keyword,
   * diga las veces que el usuario ha visitado una página con
   * esa keyword.
   */
  val pagesVisits: RDD[(String,Int)] = ???

  val usersKeywordsRepeated: RDD[(Int,List[String])] = ???

  val usersKeywordsGrouped: RDD[(Int,Iterable[List[String]])] = ???

  val usersKeywords: RDD[(Int, List[String])] = ???

  val usersKeywordsCounters: RDD[(Int, Map[String,Int])] = ???

  /**
   * Parte IV
   * A partir del 'userKeywordsCounters' anterior, decir para cada usuario qué
   * tres keywords le interesan más.
   */
  val usersThreeMainInterests: RDD[(Int, List[String])] = ???
  val usersThreeMainInterestsInMem: scala.collection.Map[Int, List[String]] = usersThreeMainInterests.collectAsMap()

  usersThreeMainInterestsInMem.keys.toList.sortWith(_ < _).foreach { id =>
    println(s"User $id main keywords: ${usersThreeMainInterestsInMem(id).mkString("[",",","]")}")
  }

  /**
   * Parte V
   * Detén Spark
   */
  sc.stop()
}

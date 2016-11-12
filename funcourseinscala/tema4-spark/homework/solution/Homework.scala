package org.hablapps.fpinscala.spark
package solution

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
  val pagesKeywordsLines: RDD[String] = sc.textFile("tema4-spark/data/PagesKeywords.csv")
  val pagesKeywords: RDD[(String, List[String])] = pagesKeywordsLines.map(_.split(",")).map(words => (words.head, words.tail.toList))

  /**
   * Parte II
   * Lee el fichero 'PagesVisits.csv' y conviértelo en un
   * RDD[(Int, String)] donde queden reflejadas las visitas
   * de los usuarios
   */
  val visitsToPagesLines: RDD[String] = sc.textFile("tema4-spark/data/PagesVisits.csv")
  val visitsToPages: RDD[(Int,String)] = visitsToPagesLines.map(_.split(",")).map(words => (words(0).toInt, words(1)))

  /**
   * Parte III
   * Asociar a cada usuario (id) un map que, para cada keyword,
   * diga las veces que el usuario ha visitado una página con
   * esa keyword.
   */
  val pagesVisits: RDD[(String,Int)] = visitsToPages.map{ case(id,page) => (page,id) } 

  val usersKeywordsRepeated: RDD[(Int,List[String])] = (pagesVisits join pagesKeywords) map (_._2)

  val usersKeywordsGrouped: RDD[(Int,Iterable[List[String]])] = usersKeywordsRepeated.groupByKey

  val usersKeywords: RDD[(Int, List[String])] = usersKeywordsGrouped mapValues (_.flatten.toList)

  val usersKeywordsCounters: RDD[(Int, Map[String,Int])] = usersKeywords mapValues {(l:List[String]) => 
    l.distinct.map(keyword => (keyword,l.filter(_ == keyword).size)).toMap
  }

  /**
   * Parte IV
   * A partir del 'userKeywordsCounters' anterior, decir para cada usuario qué
   * tres keywords le interesan más.
   */
  val usersThreeMainInterests: RDD[(Int, List[String])] = usersKeywordsCounters.mapValues{ (m:Map[String,Int]) =>
    m.toList.sortWith{(k1,k2) => k1._2 > k2._2}.take(3).map(_._1)
  }
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

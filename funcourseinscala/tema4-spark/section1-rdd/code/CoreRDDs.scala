package org.hablapps.fpinscala.spark

import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.storage.StorageLevel

object CoreRDDs {

  /*
   * TRANSFORMACIONES
   */

  // Creamos un Spark Context con el que trabajar
  val conf = new SparkConf()
    .setMaster("local")
    .setAppName("Intro")
  val sc = new SparkContext(conf)

  // Leemos las lineas de un archivo de texto
  val lines: RDD[String] = sc.textFile("tema4-spark/data/quijote.txt")

  // Separamos las líneas por palabras
  val words: RDD[String] = lines.flatMap(_.split(" "))

  // Filtramos las palabras vacías
  val nonEmptyWords: RDD[String] = words.filter(!_.isEmpty)

  // Aplicamos una transformación para quedarnos únicamente con la longitud de las palabras
  val wordsLength: RDD[Int] = nonEmptyWords.map(_.length)

  /*
   * ACCIONES
   */

  // ¿Cuántas palabras hay?
  val wordsNumber: Long = words.count

  // ¿Cuáles son sus tamaños?
  val wordsLengthMat: Array[Int] = wordsLength.collect

  // ¿Cuáles son las longitudes de las 5 primeras palabras?
  val firstFiveWords: Array[Int] = wordsLength take 5

  // ¿Y de la primera?
  val firstWord: Int = wordsLength.first

  // ¿Está vacío el conjunto?
  val isEmpty: Boolean = wordsLength.isEmpty

  // ¿Cuantas letras hay en total?
  val charsNumber: Int = wordsLength.reduce(_ + _)

  // De una manera más segura
  val charsNumberSafer: Int = wordsLength.fold(0)(_ + _)

  /*
   * TRANSFORMACIONES SOBRE CONJUNTOS
   */

  // Ahora vamos a ver algunas transformaciones con conjuntos
  val dice: RDD[Int] = sc.parallelize(1 to 6)
  val oddDice: RDD[Int] = dice.filter(_ % 2 == 1)

  // Subtract
  val evenDice: RDD[Int] = dice subtract oddDice

  // Intersection
  val empty: RDD[Int] = oddDice intersection evenDice

  // Union
  val wholeDice: RDD[Int] = oddDice union evenDice

  /*
   * AGGREGATE, DE LAS TRANSFORMACIONES MÁS POTENTES
   */

  val randomNumbers: RDD[Double] = sc.parallelize((1 to 1000).map(_ => math.random * 1000))

  val average: Double =
    randomNumbers.aggregate[(Double, Double)]((0, 0))(
      (acc, d) => (acc._1 + 1, acc._2 + d),  //funccion 1 del aggr
      (t1, t2) => (t1._1 + t2._1, t1._2 + t2._2)) match { //funcion 2 del aggr
        case (length, sum) => sum/length
      }

  /*
   * GROUPBY Y SORTBY
   */
    //a group by necesitamos pasarle una funcion que nos devuelva la key que suaremos para agrupar
    //en este caso hacemos un modulo 100
  val byEndings: RDD[(Int, Iterable[Double])] =
    randomNumbers.groupBy(_.toInt % 100)
    //para ordenar al reves sortBy(_.toInt % 100, false)

    //a sorft by hay que pasarle por lo que queremos ordenar, en este caso la funcion identidad (ellos mismos), es una funcion que si le pasas un 3 te devuelve un 3
  val sorted: RDD[Double] = randomNumbers.sortBy(identity)
  
  // cuando se trabaja con tuplas de int, podemos usar tupa lookup 23, para que nos devuelva el key 23

}

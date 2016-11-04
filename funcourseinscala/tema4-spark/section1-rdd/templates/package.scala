package org.hablapps.fpinscala.spark
package rdd

import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.storage.StorageLevel

package object templates {

  /*
   * TRANSFORMACIONES
   */

  // Creamos un Spark Context con el que trabajar
  val conf = new SparkConf()
    .setMaster("local")
    .setAppName("RDD")
  val sc = new SparkContext(conf)

  // 1. Leemos las lineas de un archivo de texto
  val lines: RDD[String] = ???

  // 2. Separamos las líneas por palabras
  val words: RDD[String] = ???

  // 3. Filtramos las palabras vacías
  val nonEmptyWords: RDD[String] = ???

  // 4. Aplicamos una transformación para quedarnos únicamente con la longitud de las palabras
  val wordsLength: RDD[Int] = ???

  /*
   * ACCIONES
   */

  // ¿Cuántas palabras hay?

  // ¿Cuáles son sus tamaños?

  // ¿Cuáles son las longitudes de las 5 primeras palabras?

  // ¿Y de la primera?

  // ¿Está vacío el conjunto?

  // ¿Cuantas letras hay en total?

  // De una manera más segura

  /*
   * TRANSFORMACIONES SOBRE CONJUNTOS
   */

  // Ahora vamos a ver algunas transformaciones con conjuntos
  val dice: RDD[Int] = sc.parallelize(1 to 6)
  val oddDice: RDD[Int] = dice.filter(_ % 2 == 1)

  // 5. Subtract
  val evenDice: RDD[Int] = ???

  // 6. Intersection
  val empty: RDD[Int] = ???

  // 7. Union
  val wholeDice: RDD[Int] = ???

  /*
   * AGGREGATE, DE LAS TRANSFORMACIONES MÁS POTENTES
   */

  val randomNumbers: RDD[Double] = sc.parallelize((1 to 1000).map(_ => math.random * 1000))

  // 8. Calculamos la media mediante el uso de aggregate
  val average: Double = ???

  /*
   * GROUPBY Y SORTBY
   */

  // 9. Agrupamos los números aleatorios por su terminación (2 últimas cifras)
  val byEndings: RDD[(Int, Iterable[Double])] = ???

  // 10. Por último los ordenamos
  val sorted: RDD[Double] = ???

  /*
   * PERSISTENCE
   */

}

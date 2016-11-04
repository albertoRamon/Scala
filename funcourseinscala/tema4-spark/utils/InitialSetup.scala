package org.hablapps.fpinscala.spark

import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.storage.StorageLevel

trait InitialSetup {

  /**
   * Parte I
   * Creando el 'spark context', necesario para poder hacer operaciones con Spark
   */
  val conf = new SparkConf().setMaster("local").setAppName("Spark Examples")
  val sc = new SparkContext(conf)

  /**
   * Parte II
   * Creación RDDs simples
   */
  // Básica (hardcoded)
  val dice: RDD[Int] = sc.parallelize(1 to 6)
  // A partir de ficheros
  val numsLocal: RDD[String] = sc.textFile("tema4-spark/data/NumsLocal.txt")
  val randomVals = for {
    _ <- (1 to 1000)
  } yield (math.random * 1000).toInt
  val randomNumbers: RDD[Int] = sc.parallelize(randomVals)
  val quijote: RDD[String] = sc.textFile("tema4-spark/data/quijote.txt")

}

package org.hablapps.fpinscala.spark

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

object Intro {

  // Creamos un Spark Context con el que trabajar
  val conf = new SparkConf()
    .setMaster("local")
    .setAppName("Example")
  val sc = new SparkContext(conf)

  // Creamos un RDD
  val dice: RDD[Int] = sc.parallelize(1 to 6)

  // Transformamos los datos
  val oddDice: RDD[Int] = dice.filter(_ % 2 != 0)

  // Materializamos los datos
  val res: Array[Int] = oddDice.collect()
}

package org.hablapps.fpinscala.spark
package intro

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

package object templates {

  // 1. Creamos un Spark Context con el que trabajar
  val conf = new SparkConf()
    .setMaster("local")
    .setAppName("Intro")
  val sc = new SparkContext(conf)

  // 2. Creamos un RDD
  val dice: RDD[Int] = ???

  // 3. Transformamos los datos
  val oddDice: RDD[Int] = ???

  // 4. Materializamos los datos
  val res: Array[Int] = ???
}

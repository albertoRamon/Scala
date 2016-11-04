package org.hablapps.fpinscala.spark

import org.apache.spark.rdd.RDD

object PairRDDs extends InitialSetup {

  /**
   * Parte I
   * Transformaciones sobre pares de RDDs (PairRDDFunctions)
   */
  val bills: RDD[(String, Int)] =
    sc.parallelize(List(
      ("Luis",  5),
      ("Pepe", 20),
      ("Jose", 30),
      ("Pepe", 10),
      ("Jose",  5)))

  // transformación de valores
  val amountsWithVAT: RDD[(String, Double)] = bills mapValues (_ * 1.2) // Asumiendo IVA 20%

  // agrupación de valores por clave
  val amountsPerUser: RDD[(String, Iterable[Int])] = bills groupByKey()

  // combinación de valores por clave, sin valor zero (neutro). Obliga a que el tipo retornado sea el de los valores del RDD
  val fullAmountPerUser: RDD[(String, Int)] = bills.reduceByKey(_ + _)

  // Esta vez de manera segura
  val fullAmountPerUserSafer: RDD[(String, Int)] = bills.foldByKey(0)(_ + _)

  /**
   * Parte II
   * Transformaciones sobre dos pares de RDDs (PairRDDFunctions),
   * son operaciones de conjuntos
   */
  val addresses: RDD[(String, String)] = sc.parallelize(("Luis", "Avd Alamo 5") :: ("Jose", "Calle Pez 12") :: Nil)

  // sustraer por clave
  val usersWithNoAddress: RDD[(String, Int)] = bills subtractByKey addresses

  // cogroup, aúna todos los valores por clave en ambos RDDs. Si una clave no está en uno de los RDDs el Iterable correspondiente estará vacío
  val usersBillsAndAddress: RDD[(String,(Iterable[Int], Iterable[String]))] = bills cogroup addresses

  // (inner) join, devuelve las keys que están en ambos RDDs, con sus valores. Si una clave está
  // repetida en alguno de los RDDs se hace producto cartesiano sobre los valores en ambos RDDs
  val billsWithAddress: RDD[(String, (Int, String))] = bills join addresses

  /**
   * Parte III
   * Acciones sobre pares de RDDs (PairRDDsFunctions)
   */

  // cuenta de valores por clave
  val billsPerUser: scala.collection.Map[String, Long] = bills countByKey()

  // retorna los valores para una clave dada
  val joseBillsValues: Seq[Int] = bills lookup "Jose"

}

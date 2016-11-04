package org.hablapps.fpinscala.spark

import org.apache.spark.rdd.RDD

object OrderedRDDs extends InitialSetup {

  /**
   * Parte I
   * Transformaciones sobre RDDs de pares con tipo K ordenado
   * (OrderedRDDFunctions)
   */
   //Cuando se crea el ordered RDD, no tenenos que pasarles los datos ordenados
  val users: RDD[(Int,String)] = sc.parallelize(List(
    (1,"Ana"),
    (2,"Pepe"),
    (3,"Rosa"),
    (4,"Javier"),
    (5,"Maria")))

  // filtrado por rango
  val users1To3: RDD[(Int, String)] = users.filterByRange(1, 3)

  // ordenación por clave
  val usersSorted: RDD[(Int, String)] = users.sortByKey()

}

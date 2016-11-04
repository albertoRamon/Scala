package org.hablapps.fpinscala.spark
package ordered

import org.apache.spark.rdd.RDD

package object templates extends InitialSetup {

  /**
   * Parte I
   * Transformaciones sobre RDDs de pares con tipo K ordenado
   * (OrderedRDDFunctions)
   */
  val users: RDD[(Int,String)] = sc.parallelize(List(
    (1,"Ana"),
    (2,"Pepe"),
    (3,"Rosa"),
    (4,"Javier"),
    (5,"Maria")))

  // filtrado por rango
  val users1To3: RDD[(Int, String)] = ???

  // ordenación por clave
  val usersSorted: RDD[(Int, String)] = ???

}

package org.hablapps.fpinscala.spark

object DoubleRDDs extends InitialSetup {

  // Calculamos la varianza
  val variance: Double = randomNumbers.variance

  // Calculamos la suma
  val sum: Double = randomNumbers.sum

  // Calculamos la media
  val mean: Double = randomNumbers.mean

  // Parando el 'spark context'
  sc.stop

}

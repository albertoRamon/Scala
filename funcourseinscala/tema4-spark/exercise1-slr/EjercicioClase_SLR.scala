package org.hablapps.fpinscala.spark

import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD

/**
 * Dados una serie de puntos por sus coordenadas (x, y) en el plano, se puede
 * calcular la ecuación de la recta, que tiene este formato:
 * y = a + bx
 * Aberigua las incógintas `a` y `b` utilizando el algoritmo de Simple Linear Regression
 *
 * No te preocupes por las fórmulas, en los enunciados se va diciendo
 * lo que hay que hacer. Para más información:
 * https://en.wikipedia.org/wiki/Simple_linear_regression
 *
 * Ejecuta este test con `test-tema4-slr`
 */
class EjercicioSLR(sc: SparkContext) extends Serializable {

  /**
   * Se proporciona el siguiente conjunto de puntos
   */
  val points: RDD[(Double, Double)] =
    sc.parallelize(
      (1.0, 3.5) :: (2.0, 5.5) :: (3.0, 7.5) :: (4.0, 9.5) :: (5.0, 11.5) ::
      (6.0, 13.5) :: (7.0, 15.5) :: (8.0, 17.5) :: (9.0, 19.5) :: (10.0, 21.5) :: Nil)

  /**
   * Parte I.
   *
   * Para calcular la ecuación de la recta necesitamos calcular la media de las x's e y's
   * Calcula la media de los x's e y's
   */
  val xs: RDD[Double] = point.map(_._1) // con pattern maching { case (x,_)=> x)}
  val ys: RDD[Double] = point.map(_._2)

  val meanX: Double = xs.mean
  val meanY: Double = ys.mean

  /**
   * Parte II.
   *
   * Lo siguiente que necesitamos es calcular dos sumatorios:
   * sum1 = (xi - meanX) * (yi - meanY)
   * sum2 = (xi - meanX)^2
   *
   * TIPS:
   * - 2^3 en Scala => math.pow(2, 3)
   */
  val sum1: Double = points.map((x,y)=>(x-meanX)*(y-meany)).sum
  val sum2: Double = points.map((x,y)=>math.pow((x-meanX)*x,2).sum

  /**
   * Parte III.
   *
   * Con los dos sumatorios anteriores ya podemos las incógnitas
   * de la ecuación: f(x) = a + bx
   * 
   * b = sum1 / sum2
   * a = meanY - b*meanX
   */
  val b: Double = sum1 / sum2
  val a: Double = meanY - b*meanX

  sc.stop()

}

package org.hablapps.fpinscala.typeclasses
package exercises
package solution

/**
 * El objetivo de este ejercicio es poder comparar las type classes de la PF
 * con distintos mecanismos que podrían plantearse para solucionar
 * problemas similares.
 *
 * En este caso se trata de implementar métodos estadísticos (sum, avg, variance)
 * sobre listas de números. Las listas de números pueden ser List[Int], o
 * List[Float], o List[Double]... con lo cual tenemos que ver cómo implementar
 * esa funcionalidad para cada uno de esos tipos. Se quiere poder evitar
 * tener que reimplementar cada función para cada tipo como se hace en 
 * 'object Monolitico'
 */
object HomeworkTypeClasses {

  object Monolitico {
    // A evitar!
  
    object StatisticsForDouble {
      def sum(ds: List[Double]): Double = ds.reduce(_ + _)
      def avg(ds: List[Double]): Double = ds.reduce(_ + _) / ds.size
      def variance(ds: List[Double]): Double = {
        val av = avg(ds)
        ds.map(d => math.pow(av - d, 2)).reduce( _ + _) / ds.size
      }
    }
  
    object StatisticsForInt {
      def sum(is: List[Int]): Int = is.reduce(_ + _)
      def avg(is: List[Int]): Double = is.reduce(_ + _).toDouble / is.size
      def variance(is: List[Int]): Double = {
        val av = avg(is)
        is.map(i => math.pow(av - i, 2)).reduce( _ + _) / is.size
      }
    }

    object StatisticsForLong {
      def sum(ls: List[Long]): Long = ls.reduce(_ + _)
      def avg(ls: List[Long]): Double = ls.reduce(_ + _).toDouble / ls.size
      def variance(ls: List[Long]): Double = {
        val av = avg(ls)
        ls.map(l => math.pow(av - l, 2)).reduce( _ + _) / ls.size
      }
    }
  
    object StatisticsForFloat {
      def sum(fs: List[Float]): Float = fs.reduce(_ + _)
      def avg(fs: List[Float]): Double = fs.reduce(_ + _).toDouble / fs.size
      def variance(fs: List[Float]): Double = {
        val av = avg(fs)
        fs.map(f => math.pow(av - f, 2)).reduce( _ + _) / fs.size
      }
    }
  
  }

  
  /**
   * Parte I.
   *
   * Solución mediante type classes. Dada la type class Number, definida
   * más abajo, proporciona instancias para los tipos numéricos necesarios.
   */
  object TypeClassParaEstadisticas {
  
    trait Number[T] {
      def toDouble(t: T): Double
      def plus(t1: T, t2: T): T
      def minus(t1: T, t2: T): T
      def divide(t1: T, i2: Int): Double
    }
  
    object Number {
      implicit object DoubleNumber extends Number[Double] {
        def toDouble(d: Double): Double = d
        def plus(d1: Double, d2: Double): Double = d1+d2
        def minus(d1: Double, d2: Double): Double = d1-d2
        def divide(d1: Double, i2: Int): Double = d1/i2
      }
      implicit object IntNumber extends Number[Int] {
        def toDouble(i: Int): Double = i
        def plus(i1: Int, i2: Int): Int = i1+i2
        def minus(i1: Int, i2: Int): Int = i1-i2
        def divide(i1: Int, i2: Int): Double = toDouble(i1)/i2
      }
      implicit object LongNumber extends Number[Long] {
        def toDouble(l: Long): Double = l
        def plus(l1: Long, l2: Long): Long = l1+l2
        def minus(l1: Long, l2: Long): Long = l1-l2
        def divide(l1: Long, i2: Int): Double = toDouble(l1)/i2
      }
      implicit object FloatNumber extends Number[Float] {
        def toDouble(f: Float): Double = f
        def plus(f1: Float, f2: Float): Float = f1+f2
        def minus(f1: Float, f2: Float): Float = f1-f2
        def divide(f1: Float, i2: Int): Double = toDouble(f1)/i2
      }
    }
  }


  /** 
   *  Parte II
   *  Implementa los métodos estadísticos usando la type class
   *  que has definido en la Parte I.
   */
  object ParteII {
  
    import TypeClassParaEstadisticas._

    def sum[T](ts: List[T])(implicit ev: Number[T]): T = 
      ts.reduce(ev.plus(_,_))

    def avg[T](ts: List[T])(implicit ev: Number[T]): Double = 
      ev.divide(sum(ts), ts.size)

    def variance[T](ts: List[T])(implicit ev: Number[T]): Double =
      avg(ts.map(f => math.pow(avg(ts) - ev.toDouble(f), 2)))
      
  }

}

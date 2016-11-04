package org.hablapps.fpinscala.spark

import org.apache.spark._
import org.apache.spark.rdd.RDD
import scala.reflect.ClassTag

object Language {

  // LANGUAGE SYNTAX
  implicit class TLangSyntax[T[_, _], A, B](t: T[A, B])(implicit T: TLang[T]) {
    def andThen[C](t2: T[B, C]): T[A, C] = T.andThen(t)(t2)
  }

  // LANGUAGE
  trait TLang[T[_, _]] {
    type Info[A]
    def expand[A, B: Info](f: A => TraversableOnce[B]): T[A, B]
    def filter[A](f: A => Boolean): T[A, A]
    def andThen[A, B, C](t1: T[A, B])(t2: T[B, C]): T[A, C]
    def apply[A, B: Info](f: A => B): T[A, B]
    def reduceByKey[K: Info, V: Info](f: (V, V) => V): T[(K, V), (K, V)]
    def sortBy[A, B: Info](f: A => B, asc: Boolean = true)(implicit O: Ordering[B]): T[A, A]

    // Derivadas
    def sortByKey[K: Info, V](asc: Boolean = true)(implicit O: Ordering[K]): T[(K, V), (K, V)] =
      sortBy(_._1, asc)
  }

  // GENERIC PROGRAM
  trait WordCount[T[_, _]] extends TLang[T] {
    implicit val _this = this
    def apply(implicit
        I1: Info[String],
        I2: Info[(String, Int)],
        I3: Info[(Int, String)],
        I4: Info[Int]): T[String, (Int, String)] =
      expand[String, String](_.split(" ")) andThen
      filter(!_.isEmpty) andThen
      apply((_, 1)) andThen
      reduceByKey(_ + _) andThen
      apply(_.swap) andThen
      sortByKey(false)
  }

  // LIST INSTANCE
  type ListT[A, B] = List[A] => List[B]
  trait ListTInstance extends TLang[ListT] {
    type Info[A] = Option[A]

    def expand[A, B: Info](f: A => TraversableOnce[B]): ListT[A, B] = _ flatMap (f andThen (_.toList))
    def filter[A](f: A => Boolean): ListT[A, A] = _.filter(f)
    def andThen[A, B, C](t1: ListT[A, B])(t2: ListT[B, C]): ListT[A, C] = t1 andThen t2
    def apply[A, B: Info](f: A => B): ListT[A, B] = _ map f
    def reduceByKey[K: Info, V: Info](f: (V, V) => V): ListT[(K, V), (K, V)] = fa => {
      val r1: Map[K, List[(K, V)]] = fa.groupBy(_._1)
      val r2: Map[K, V] = r1.mapValues(_.map(_._2).reduce(f))
      r2.toList
    }
    def sortBy[A, B: Info](f: A => B, asc: Boolean = true)(implicit O: Ordering[B]): ListT[A, A] = as =>
      if (asc) as.sortBy(f)
      else as.sortBy(f).reverse
  }

  val listTInstance = new WordCount[ListT] with ListTInstance
  val wordCountListT = listTInstance(None, None, None, None)(List(
    "En un lugar de la mancha",
    "de cuyo nombre no quiero acordarme",
    "no ha mucho tiempo que vivía"))

  // SPARK INSTANCE
  type RDDT[A, B] = RDD[A] => RDD[B]
  trait RDDTInstance extends TLang[RDDT] {
    type Info[A] = ClassTag[A]

    def expand[A, B: Info](f: A => TraversableOnce[B]): RDDT[A, B] = _ flatMap f
    def filter[A](f: A => Boolean): RDDT[A, A] = _ filter f
    def andThen[A, B, C](t1: RDDT[A, B])(t2: RDDT[B, C]): RDDT[A, C] = t1 andThen t2
    def apply[A, B: Info](f: A => B): RDDT[A, B] = _ map f
    def reduceByKey[K: Info, V: Info](f: (V, V) => V): RDDT[(K, V), (K, V)] = _ reduceByKey f
    def sortBy[A, B: Info](f: A => B, asc: Boolean = true)(implicit O: Ordering[B]): RDDT[A, A] = _.sortBy(f, asc)
  }

  val conf = new SparkConf().setMaster("local").setAppName("Spark Examples")
  implicit val sc = new SparkContext(conf)

  val rddTInstance = new WordCount[RDDT] with RDDTInstance
  val wordCountRDDT = rddTInstance(implicitly, implicitly, implicitly, implicitly)(sc.parallelize(List(
    "En un lugar de la mancha",
    "de cuyo nombre no quiero acordarme",
    "no ha mucho tiempo que vivía")))

}

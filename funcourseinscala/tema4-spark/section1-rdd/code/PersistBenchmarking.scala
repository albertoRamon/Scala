package org.hablapps.fpinscala.spark

import scala.collection.JavaConversions._

import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.storage.StorageLevel

object PersistenceBenchmarking {

  def main(args: Array[String]): Unit = {
    
    val conf = new SparkConf().setMaster("local").setAppName("Persistence Benchmarking")
    val sc = new SparkContext(conf)

    def bigOhSquaredFunc(i: Int): Int = {
      (for {
        i0 <- 1 to i
        i1 <- 1 to i
      } yield i0+i1).head
    }

    println("Starting warming up...")
    val warmingUpStart: Long = java.lang.System.currentTimeMillis
    sc.parallelize(1 to 500).map(bigOhSquaredFunc(_)).cache().collect()
    val warmingUpEnd: Long = java.lang.System.currentTimeMillis
    println(s"Warming up done in ${warmingUpEnd - warmingUpStart}")

    val toCompute: RDD[Int] = sc.parallelize(1 to 500)

    println("Defining computations")
    val withCache: RDD[Int] = toCompute.map(bigOhSquaredFunc(_)).cache()
    val withoutCache: RDD[Int] = toCompute.map(bigOhSquaredFunc(_))

    println("Computing")
    for(i <- 1 to 5) {
      val start: Long = java.lang.System.currentTimeMillis
      withCache.collect()
      val end: Long = java.lang.System.currentTimeMillis
      println(f"Cached $i:\t${end-start}%4d (ms)")
    }
    for(i <- 1 to 5) {
      val start: Long = java.lang.System.currentTimeMillis
      withoutCache.collect()
      val end: Long = java.lang.System.currentTimeMillis
      println(f"Not cached $i:\t${end-start}%4d (ms)")
    }

    sc.stop()

  }

}

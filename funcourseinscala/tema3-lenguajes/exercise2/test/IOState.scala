package org.hablapps.fpinscala.lenguajes
package test

import org.scalatest._

import _root_.scalaz.{State, Monad}

// IO Utils
case class IOState(reads: List[String], writes: List[String])

object IOState{

  type IOTrans[A] = State[IOState, A]

  implicit object IOTrans extends io.IO[IOTrans] {
    
    def read: IOTrans[String] =
      for {
        s <- State.get
        _ <- State.put(s.copy(reads = s.reads.tail))
      } yield s.reads.head

    def write(msg: String): IOTrans[Unit] =
      State.modify{ s => 
        s.copy(writes = msg :: s.writes) 
      }
      // Equivalently: 
      // for {
      //   s <- State.get
      //   _ <- State.put(s.copy(writes = msg :: s.writes))
      // } yield ()

    def doAndThen[A, B](f: IOTrans[A])(cont: A => IOTrans[B]): IOTrans[B] = 
      Monad[IOTrans].bind(f)(cont)
    
    def returns[A](a: A): IOTrans[A] =
      Monad[IOTrans].pure(a)
  }
}
  
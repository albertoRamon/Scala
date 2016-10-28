package org.hablapps.fpinscala
package lenguajes
package test

import _root_.scalaz.State

import IOState._

object IOTrans extends IO[IOTrans] {

  def read: IOTrans[String] =
    for {
      s <- State.get
      _ <- State.put(s.copy(reads = s.reads.tail))
    } yield s.reads.head

  def write(msg: String): IOTrans[Unit] =
    for {
      s <- State.get
      _ <- State.put(s.copy(writes = msg :: s.writes))
    } yield ()

}

package org.hablapps.fpinscala
package lenguajes
package test

import org.scalatest._

import _root_.scalaz.{State, StateT, MonadState}

case class FSS(map: Map[String, String])

// FS Utils
object FSS{

  def apply(pairs: (String, String)*): FSS = FSS(Map(pairs: _*))

  type FSState[A] = State[FSS, A]

  implicit object PureFS extends FS[FSState] {
    implicit val fsStateMonad: MonadState[FSState, FSS] = 
      StateT.stateMonad[FSS]

    def deleteFile(path: String): FSState[Unit] =
      fsStateMonad.modify(fss => FSS(fss.map - path))

    def readFile(path: String): FSState[String] =
      fsStateMonad.get map (_.map(path))

    def writeFile(path: String)(contents: String): FSState[Unit] =
      fsStateMonad.modify(fss => FSS(fss.map + (path -> contents)))
  }

}
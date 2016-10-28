package org.hablapps.fpinscala
package lenguajes
package test

import org.scalatest._

import _root_.scalaz.{State, StateT, MonadState, Monad => MonadZ}


// Terminal Utils

case class TerminalState(io: IOState, fs: FSS)

object TerminalState{

  type TState[A] = State[TerminalState, A]

  implicit object PureFSTerminal extends FS[TState] {
    implicit val terminalStateMonad = StateT.stateMonad[TerminalState]
    
    def deleteFile(path: String): TState[Unit] =
      terminalStateMonad modify {
        case TerminalState(io, fs) => TerminalState(io, FSS.PureFS.deleteFile(path).exec(fs))
      }

    def readFile(path: String): TState[String] =
      for {
        ts <- terminalStateMonad.get
      } yield FSS.PureFS.readFile(path).eval(ts.fs)

    def writeFile(path: String)(contents: String): TState[Unit] =
      terminalStateMonad modify {
        case TerminalState(io, fs) => TerminalState(io, FSS.PureFS.writeFile(path)(contents).exec(fs))
      }
  }

  implicit object PureIOTerminal extends IO[TState] {
    implicit val terminalStateMonad = StateT.stateMonad[TerminalState]
    
    def read: TState[String] =
      for {
        ts <- terminalStateMonad.get
        (ios, res) = IOState.IOTrans.read.run(ts.io)
        _ <- terminalStateMonad.put(ts.copy(io = ios))
      } yield res

    def write(msg: String): TState[Unit] =
      terminalStateMonad modify {
        case TerminalState(io, fs) => TerminalState(IOState.IOTrans.write(msg).exec(io), fs)
      }
  }

}

package org.hablapps.fpinscala
package lenguajes
package test

import org.scalatest._

import _root_.scalaz.{State, StateT, MonadState, Monad => MonadZ}

// Monad utils
object Monads{

  implicit def scalazMonadMonad[M[_]: MonadZ] = new lenguajes.Monad[M] {
    def flatMap[A, B](ma: M[A])(f: A => M[B]): M[B] = 
      MonadZ[M].bind(ma)(f)

    def returns[A](value: A): M[A] = 
      MonadZ[M].point(value)
  }

}
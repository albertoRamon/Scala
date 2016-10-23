package org.hablapps.fpinscala.hofs
package diagrams
package code

object Transformations {

  case class Pos(x: Double, y: Double) {
    def +(other: Pos): Pos = Pos(x+other.x, y+other.y)
  }

  // ADT
  sealed trait Transform
  case object Identity extends Transform
  case class Translate(p: Pos) extends Transform
  case class Compose(t1: Transform, t2: Transform) extends Transform

  // SYNTAX
  implicit class TransformOps(t: Transform) {
    def fold[B](id: B, trans: Pos => B, comp: (B, B) => B): B =
      t match {
        case Identity => id
        case Translate(p) => trans(p)
        case Compose(t1, t2) => comp(t1.fold(id, trans, comp), t2.fold(id, trans, comp))
      }
  }

  // CONSUMERS
  def transformPos(t: Transform): Pos => Pos = t match {
    case Identity => identity
    case Translate(p) => p + _
    case Compose(t1, t2) => transformPos(t1) compose transformPos(t2)
  }

  def transformPos2(t: Transform): Pos => Pos =
    t.fold[Pos => Pos](identity, p => p + _, _ compose _)

}
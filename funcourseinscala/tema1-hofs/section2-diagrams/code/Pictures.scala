package org.hablapps.fpinscala.hofs
package diagrams
package code

object Pictures {
  import StyleSheets._, Shapes._

  // ADT
  sealed trait Picture
  case class Place(style: StyleSheet, shape: Shape) extends Picture
  case class Above(top: Picture, bottom: Picture) extends Picture
  case class Beside(left: Picture, right: Picture) extends Picture

  // SYNTAX
  implicit class PictureOps(p: Picture) {
    def above(other: Picture): Picture =
      Above(p, other)
    def beside(other: Picture): Picture =
      Beside(p, other)
    def *(other: Picture): Picture = beside(other)
  }

}
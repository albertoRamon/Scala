package org.hablapps.fpinscala.hofs
package diagrams
package code

object Drawings {
  import Shapes._, StyleSheets._, Transformations._, Pictures._

  // Drawing (We'll transform our Picture hierarchy to a List of shapes)

  type Drawing = List[(Transform, StyleSheet, Shape)]

  implicit class DrawingOps(d: Drawing) {
    def above(bottom: Drawing): Drawing = {
      val (Pos(_, toplly), _) = extent
      val (_, Pos(_, bottomury)) = bottom.extent

      transformDrawing(Translate(Pos(0, bottomury)))(d) :::
      transformDrawing(Translate(Pos(0, toplly)))(bottom)
    }
    def beside(right: Drawing): Drawing = {
      val (_, Pos(lefturx, _)) = extent
      val (Pos(rightllx, _), _) = right.extent

      transformDrawing(Translate(Pos(rightllx, 0)))(d) :::
      transformDrawing(Translate(Pos(lefturx, 0)))(right)
    }

    def extent: Extent = {
      def getExtent(t: (Transform, StyleSheet, Shape)): Extent = {
        val (ll, ur) = shapeExtent(t._3)
        (transformPos(t._1)(ll), transformPos(t._1)(ur))
      }
      d.foldLeft((Pos(Double.MaxValue, Double.MaxValue), Pos(Double.MinValue, Double.MinValue))) { (acc, x) =>
        unionExtent(acc, getExtent(x))
      }
    }

    def sizeD: (Double, Double) = extent match {
      case (Pos(llx, lly), Pos(urx, ury)) => (urx - llx, ury - lly)
    }
  }

  type Extent = (Pos, Pos) // lower left and upper right corners

  def unionExtent(e1: Extent, e2: Extent): Extent =
    (
      Pos(
        math.min(e1._1.x, e2._1.x),
        math.min(e1._1.y, e2._1.y)),
      Pos(
        math.max(e1._2.x, e2._2.x),
        math.max(e1._2.y, e2._2.y))
    )

  def shapeExtent(s: Shape): Extent = s match {
    case Rectangle(width, height) => (Pos(-width/2, -height/2), Pos(width/2, height/2))
    case Circle(radius) => (Pos(-radius, -radius), Pos(radius, radius))
    case Triangle(width) => (Pos(-width/2, -math.sqrt(3)*(width/4)), Pos(width/2, math.sqrt(3)*(width/4)))
  }

  def drawShape(ss: StyleSheet, s: Shape): Drawing = (Identity, ss, s) :: Nil

  def transformDrawing(t: Transform): Drawing => Drawing =
    _ map {
      case (t2, ss, s) => (Compose(t, t2), ss, s)
    }

  def drawPicture(p: Picture): Drawing = p match {
    case Place(ss, s) => drawShape(ss, s)
    case Above(top, bottom) => drawPicture(top) above drawPicture(bottom)
    case Beside(left, right) => drawPicture(left) beside drawPicture(right)
  }

}

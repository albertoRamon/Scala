package org.hablapps.fpinscala.hofs
package diagrams
package code

// Figuras
object Shapes {

  // ADT
  sealed abstract class Shape
  case class Rectangle(width: Double, height: Double) extends Shape
  case class Circle(radius: Double) extends Shape
  case class Triangle(width: Double) extends Shape

  // SYNTAX
  implicit class ShapeOps(s: Shape) {
    def fold[B](rectangle: (Double, Double) => B, circle: Double => B, triangle: Double => B): B =
      s match {
        case Rectangle(width, height) => rectangle(width, height)
        case Circle(radius) => circle(radius)
        case Triangle(width) => triangle(width)
      }
  }

}
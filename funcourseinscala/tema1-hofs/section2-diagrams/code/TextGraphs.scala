package org.hablapps.fpinscala.hofs
package diagrams
package code

object TextGraphs {
  import Drawings._, Shapes._, StyleSheets._, Pictures._

  val innerSpace: Picture =
    Place(FillColor(Alpha) :: Nil, Rectangle(2, 10))

  val a: Picture =
    Place(FillColor(Alpha) :: Nil, Rectangle(2, 2))  beside
    Place(FillColor(Blue)  :: Nil, Rectangle(5, 2))  beside
    Place(FillColor(Alpha) :: Nil, Rectangle(2, 2))  above
   (Place(FillColor(Blue)  :: Nil, Rectangle(2, 8))  beside
    Place(FillColor(Blue)  :: Nil, Rectangle(5, 2))  beside
    Place(FillColor(Blue)  :: Nil, Rectangle(2, 8)))

  val b: Picture =
    Place(FillColor(Blue)  :: Nil, Rectangle(7,  2))  beside
    Place(FillColor(Alpha) :: Nil, Rectangle(2,  2))  above
   (Place(FillColor(Blue)  :: Nil, Rectangle(2,  2))  beside
    Place(FillColor(Alpha) :: Nil, Rectangle(3,  2))  beside
    Place(FillColor(Blue)  :: Nil, Rectangle(2,  2))  beside
    Place(FillColor(Alpha) :: Nil, Rectangle(2,  2))) above
    Place(FillColor(Blue)  :: Nil, Rectangle(9,  2))  above
   (Place(FillColor(Blue)  :: Nil, Rectangle(2,  2))  beside
    Place(FillColor(Alpha) :: Nil, Rectangle(5,  2))  beside
    Place(FillColor(Blue)  :: Nil, Rectangle(2,  2))) above
    Place(FillColor(Blue)  :: Nil, Rectangle(9,  2))

  val c: Picture =
    Place(FillColor(Blue)  :: Nil, Rectangle(2, 10)) beside
   (Place(FillColor(Blue)  :: Nil, Rectangle(7,  2)) above
    Place(FillColor(Alpha) :: Nil, Rectangle(7,  6)) above
    Place(FillColor(Blue)  :: Nil, Rectangle(7,  2)))

  val d: Picture =
    Place(FillColor(Blue)  :: Nil, Rectangle(2, 10))  beside
   (Place(FillColor(Blue)  :: Nil, Rectangle(5,  2))  above
    Place(FillColor(Alpha) :: Nil, Rectangle(5,  6))  above
    Place(FillColor(Blue)  :: Nil, Rectangle(5,  2))) beside
    Place(FillColor(Blue)  :: Nil, Rectangle(2,  6))

  val e: Picture =
    Place(FillColor(Blue)  :: Nil, Rectangle(2, 10))  beside
   (Place(FillColor(Blue)  :: Nil, Rectangle(3,  2))  above
    Place(FillColor(Alpha) :: Nil, Rectangle(3,  2))  above
    Place(FillColor(Blue)  :: Nil, Rectangle(3,  2))  above
    Place(FillColor(Alpha) :: Nil, Rectangle(3,  2))  above
    Place(FillColor(Blue)  :: Nil, Rectangle(3,  2))) beside
   (Place(FillColor(Blue)  :: Nil, Rectangle(4,  2))  above
    Place(FillColor(Alpha) :: Nil, Rectangle(4,  6))  above
    Place(FillColor(Blue)  :: Nil, Rectangle(4,  2)))

  val h: Picture =
    Place(FillColor(Blue) :: Nil, Rectangle(2, 10)) beside
    Place(FillColor(Blue) :: Nil, Rectangle(5,  2)) beside
    Place(FillColor(Blue) :: Nil, Rectangle(2, 10))

  val i: Picture =
    Place(FillColor(Blue) :: Nil, Rectangle(9, 2)) above
    Place(FillColor(Blue) :: Nil, Rectangle(2, 6)) above
    Place(FillColor(Blue) :: Nil, Rectangle(9, 2))

  val k: Picture =
    Place(FillColor(Blue)  :: Nil, Rectangle(2, 4)) beside
    Place(FillColor(Alpha) :: Nil, Rectangle(3, 4)) beside
    Place(FillColor(Blue)  :: Nil, Rectangle(2, 4)) beside
    Place(FillColor(Alpha) :: Nil, Rectangle(2, 4)) above
    Place(FillColor(Blue)  :: Nil, Rectangle(9, 2)) above
   (Place(FillColor(Blue)  :: Nil, Rectangle(2, 4)) beside
    Place(FillColor(Alpha) :: Nil, Rectangle(5, 4)) beside
    Place(FillColor(Blue)  :: Nil, Rectangle(2, 4)))

  val l: Picture =
    Place(FillColor(Blue)  :: Nil, Rectangle(2, 8)) beside
    Place(FillColor(Alpha) :: Nil, Rectangle(7, 8)) above
    Place(FillColor(Blue)  :: Nil, Rectangle(9, 2))

  val n: Picture =
    Place(FillColor(Blue)  :: Nil, Rectangle(2, 10))  beside
   (Place(FillColor(Blue)  :: Nil, Rectangle(5,  2))  above
    Place(FillColor(Alpha) :: Nil, Rectangle(5,  8))) beside
   (Place(FillColor(Alpha) :: Nil, Rectangle(2,  2))  above
    Place(FillColor(Blue)  :: Nil, Rectangle(2,  8)))

  val p: Picture =
    Place(FillColor(Blue)  :: Nil, Rectangle(9, 2))  above
   (Place(FillColor(Blue)  :: Nil, Rectangle(2, 3))  beside
    Place(FillColor(Alpha) :: Nil, Rectangle(5, 3))  beside
    Place(FillColor(Blue)  :: Nil, Rectangle(2, 3))) above
    Place(FillColor(Blue)  :: Nil, Rectangle(9, 2))  above
   (Place(FillColor(Blue)  :: Nil, Rectangle(2, 3))  beside
    Place(FillColor(Alpha) :: Nil, Rectangle(7, 3)))

  val whiteSpace: Picture =
    Place(FillColor(Alpha) :: Nil, Rectangle(9, 10))

  val notFound: Picture =
    Place(FillColor(Blue) :: Nil, Rectangle(9, 10))

  val abc: Map[Char, Picture] = Map(
    'a' -> a,
    'b' -> b,
    'c' -> c,
    'd' -> d,
    'e' -> e,
    'h' -> h,
    'i' -> i,
    'k' -> k,
    'l' -> l,
    'n' -> n,
    'p' -> p,
    ' ' -> whiteSpace)

  def write(s: String): Picture =
    s map { c => abc.getOrElse(c.toLower, notFound) } reduce[Picture] {
      case (left, right) => left * innerSpace * right
    }

}

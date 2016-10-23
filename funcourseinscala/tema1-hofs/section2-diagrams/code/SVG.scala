package org.hablapps.fpinscala.hofs
package diagrams
package code

object SVG {
  import StyleSheets._, Drawings._, Shapes._, Transformations._

  def styleToSVG(ss: StyleSheet): String =
    ss.foldLeft("") {
      case (acc, FillColor(Bisque)) => acc + """fill="#000" """
      case (acc, FillColor(Red)) => acc + """fill="#f00" """
      case (acc, FillColor(Blue)) => acc + """fill="#00f" """
      case (acc, FillColor(Alpha)) => acc + """fill="none" """
      case (acc, _) => acc
    }

  def toSVG(d: Drawing): String = {
    val (width, height) = d.sizeD
    val header =
      s"""|<svg width="$width" height="$height" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink">
          |  <g transform="translate(${width/2}, ${height/2})">
          |    <g transform="scale(1, -1)">""".stripMargin
    val draw = d.foldLeft("") {
      case (acc, (t, ss, Rectangle(width, height))) =>
        val Pos(x, y) = transformPos(t)(Pos(-width/2, -height/2))
        acc + s"""<rect x="$x" y="$y" width="$width" height="$height" ${styleToSVG(ss)} />"""
      case (acc, (t, ss, Circle(radius))) =>
        val Pos(x, y) = transformPos(t)(Pos(0, 0))
        acc + s"""<circle r="$radius" cx="$x" cy="$y" ${styleToSVG(ss)} />"""
      case (acc, (t, ss, Triangle(width))) =>
        val height = math.sqrt(3) * width / 2
        val Pos(x, y) = transformPos(t)(Pos(-width/2, -height/2))
        acc + s"""<polygon points="$x,$y ${x+(width/2)},${y+height} ${x+width},$y" ${styleToSVG(ss)} />"""
    }
    val footer =
      s"""|    </g>
          |  </g>
          |</svg>""".stripMargin

    header ++ draw ++ footer
  }

}
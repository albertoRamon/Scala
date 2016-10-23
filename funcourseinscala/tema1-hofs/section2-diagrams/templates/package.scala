package org.hablapps.fpinscala.hofs
package diagrams

package object templates {
  import code._, Pictures._, StyleSheets._, Shapes._, TextGraphs._, SVG._, Drawings._

  // Instancias de ejemplo
  val slideExample: Picture =
    Place(FillColor(Yellow) :: Nil, Circle(4)) above
    Place(FillColor(Blue) :: Nil, Triangle(10)) beside
    Place(FillColor(Red) :: Nil, Rectangle(20, 7))

  val body: Picture =
    Place(StrokeWidth(0.1) :: FillColor(Bisque) :: Nil, Circle(3))        above
    Place(StrokeWidth(0)   :: FillColor(Red)    :: Nil, Rectangle(10, 1)) above
    Place(StrokeWidth(0)   :: FillColor(Red)    :: Nil, Triangle(10))     above
   (Place(StrokeWidth(0)   :: FillColor(Blue)   :: Nil, Rectangle(1, 5))  beside
    Place(StrokeWidth(0)   :: FillColor(Alpha)  :: Nil, Rectangle(2, 5))  beside
    Place(StrokeWidth(0)   :: FillColor(Blue)   :: Nil, Rectangle(1, 5))) above
   (Place(StrokeWidth(0)   :: FillColor(Blue)   :: Nil, Rectangle(2, 1))  beside
    Place(StrokeWidth(0)   :: FillColor(Alpha)  :: Nil, Rectangle(2, 1))  beside
    Place(StrokeWidth(0)   :: FillColor(Blue)   :: Nil, Rectangle(2, 1)))

  def pictureToSVG(p: Picture): String = toSVG(drawPicture(p))

  val sampleSVG = pictureToSVG(slideExample)
  val bodySVG = pictureToSVG(body)

  // Textos de ejemplo
  val sampleTextPicture = write("Hello world!")
  val sampleTextSVG = pictureToSVG(sampleTextPicture)

}

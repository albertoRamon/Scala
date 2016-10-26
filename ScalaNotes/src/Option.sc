
def funcion1 (s:String):Option[String] = s match {
      case "A" => Option("es A")
      case "B" => Option("es B")
      case _  => None
  }

  funcion1("A")
  funcion1("Z")
  //ver que devuelve un Option [String]

// Tambien se admite
//  case _ => Option.empty[String]
//  case _ => None:Option[String]


def funcion2 (s:String):Option[String] = s match {
  case "A" => Some("es A")
  case "B" => Some("es B")
}

  funcion2("A")
  funcion2("Z")
  // se recomienda trabajar con option y no con none, q admite Null


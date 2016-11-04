package org.hablapps.sparkexamples

import org.scalacheck.Gen

object VisitsPagesGenerator extends App {

  val keywords: Set[String] = (1 to 100).map("keyword"+_).toSet
  val keywordsSetGen: Gen[Set[String]] = 
    for {
      amount <- Gen.choose(1,10)
      keywords <- Gen.containerOfN[Set,String](amount, Gen.oneOf(keywords.toList))
    } yield keywords
    
  val pages: Set[String] = (1 to 500).map("page"+_).toSet
  val pageGen: Gen[String] = Gen.oneOf(pages.toList)

  val ids = (1 to 100)
  val idGen: Gen[Int] = Gen.oneOf(ids)

  val pageVisitGen: Gen[(Int,String)] =
    for {
      id <- idGen
      page <- pageGen
    } yield (id, page)
  val pagesVisits: List[(Int,String)] = Gen.listOfN[(Int,String)](100000, pageVisitGen).sample.get

  val pagesKeywords: Set[(String,Set[String])] = pages map ((_, keywordsSetGen.sample.get))


  import java.io._
  val pVpW = new PrintWriter("PagesVisits.csv")
  pagesVisits foreach { pageVisit =>
    pVpW.println(pageVisit._1 + "," + pageVisit._2)
  }
  pVpW.close()

  val pKpW = new PrintWriter("PagesKeywords.csv")
  pagesKeywords foreach { pageKeyworkdSet => 
    pKpW.println(pageKeyworkdSet._1+","+ pageKeyworkdSet._2.mkString(","))
  }
  pKpW.close()
  
}

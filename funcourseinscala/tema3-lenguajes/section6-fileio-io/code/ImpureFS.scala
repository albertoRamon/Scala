package org.hablapps.fpinscala
package lenguajes


object ImpureFS{

  // API

  trait FS{
    def readFile(path: String): String
    def writeFile(path: String)(contents: String): Unit
    def deleteFile(path: String): Unit
  }


  // Interpreter
  
  object ScalaIOFS extends FS{
    import scala.io._, java.io._
    
    def readFile(path: String): String =
      Source.fromFile(path).mkString

    def writeFile(path: String)(contents: String): Unit = {
      val pw = new PrintWriter(path)
      pw.print(contents)
      pw.close
    }
    
    def deleteFile(path: String): Unit = {
      val f = new File(path)
      f.delete
      ()
    }
  }

}
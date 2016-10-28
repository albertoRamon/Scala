package org.hablapps.fpinscala
package lenguajes

/**
 * Se proporciona la clase de los lenguajes de FS (FileSystem)
 */
trait FS[F[_]]{
  def readFile(path: String): F[String]
  def writeFile(path: String)(contents: String): F[Unit]
  def deleteFile(path: String): F[Unit]
}

object FS{

  object Syntax{
    def readFile[F[_]](path: String)(implicit FS: FS[F]): F[String] = 
      FS.readFile(path)
    def writeFile[F[_]](path: String)(contents: String)(implicit FS: FS[F]): F[Unit] = 
      FS.writeFile(path)(contents)
    def deleteFile[F[_]](path: String)(implicit FS: FS[F]): F[Unit] =
      FS.deleteFile(path)
  }

  import typeclasses.Id
  
  implicit object ScalaIOFS extends FS[Id] {
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
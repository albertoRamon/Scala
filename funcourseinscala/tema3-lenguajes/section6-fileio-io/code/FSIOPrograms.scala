package org.hablapps.fpinscala.lenguajes

object FSIOPrograms{

  // Impure version
  trait Impure{ S: ImpureFS.FS with io.ImpureIO.IO => 
    import S._

    def copy(orig: String, dest: String): Unit = {
      write(s"copying $orig into $dest")
      val content: String = readFile(orig)
      writeFile(dest)(content)
    }
  }


  // Pure version
  object Pure{
    import FS.Syntax._, IO.Syntax._, Monad.Syntax._

    def copy[F[_]: FS: IO: Monad](orig: String, dest: String): F[Unit] =
      // write(s"copying $orig into $dest") flatMap { _ => 
      //   readFile(orig) flatMap { content => 
      //     writeFile(dest)(content)
      //   }
      // }
      for {
        _ <- write(s"copying $orig into $dest")
        content <- readFile(orig)
        _ <- writeFile(dest)(content)
      } yield ()
  }
}
  
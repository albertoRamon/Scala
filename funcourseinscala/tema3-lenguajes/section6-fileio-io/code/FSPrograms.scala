package org.hablapps.fpinscala.lenguajes

object FSPrograms{

  // Impure version
  trait Impure{ FS: ImpureFS.FS => 
    import FS._

    def copy(orig: String, dest: String): Unit = {
      val content: String = readFile(orig)
      writeFile(dest)(content)
    }
  }


  // Pure version
  object Pure{
    import FS.Syntax._, Monad.Syntax._

    def copy[F[_]: FS: Monad](orig: String, dest: String): F[Unit] =
      readFile(orig) flatMap (content => 
        writeFile(dest)(content)
      )
      // for {
      //   contents <- readFile(orig)
      //   _ <- writeFile(dest)(contents)
      // } yield ()
  }
}
  
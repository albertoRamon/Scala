package org.hablapps.fpinscala
package lenguajes

object Homework1{
  import FS.Syntax._, Monad.Syntax._

  /** 
   * Parte I.
   *
   * Implementa un programa que mueve un fichero de un sitio a otro.
   */
  import FSPrograms.Pure.copy 

  def move[F[_]: FS: Monad](orig: String, dest: String): F[Unit] = ???

  /** 
   * Parte II.
   *
   * Implementa un programa que junta el contenido de dos archivos en un tercero.
   */
  def merge2[F[_]: FS: Monad](orig1: String, orig2: String, dest: String): F[Unit] = ???

  /** 
   * Parte III.
   *
   * Implementa un programa que junta el contenido de dos archivos en un tercero,
   * y además devuelve la longitud del archivo resultante.
   */
  def merge2bis[F[_]: FS: Monad](orig1: String, orig2: String, dest: String): F[Int] = ???

  /** 
   * Parte IV.
   *
   * Implementa un programa similar a `merge2`, pero que junte N archivos.
   */
  def mergeN[F[_]: FS: Monad](origs: Seq[String], dest: String): F[Unit] = ???

}
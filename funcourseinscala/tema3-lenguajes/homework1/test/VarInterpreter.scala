package org.hablapps.fpinscala
package lenguajes
package test

/** 
 * Para que podáis probar las siguientes funciones, podéis utilizar el siguiente
 * intérprete "puro" en el que se manipula un `Map` inmutable.
 */
import typeclasses.Id

class MapFS(var filesystem: Map[String, String]) extends FS[Id] {
  // Operaciones propias de FS
  def readFile(path: String): String = filesystem(path)
  def writeFile(path: String)(contents: String): Unit = filesystem += (path -> contents)
  def deleteFile(path: String): Unit = filesystem -= path
}
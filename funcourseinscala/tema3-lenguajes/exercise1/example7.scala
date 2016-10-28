package org.hablapps.fpinscala
package lenguajes


// Different executions of this method can return different results.
// Every pure function can be cached, this is not the case.
object Example7{
  import java.io.{BufferedReader, FileReader}

  def fileFirstLine(path: String): String = {
    val br = new BufferedReader(new FileReader(path))
    val line = br.readLine()
    br.close()
    line
  }
}


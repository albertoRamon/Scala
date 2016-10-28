package org.hablapps.fpinscala
package lenguajes


// We are doing more things than declared by the signature.
// The execution of this function could potentially change the behaviour 
// of other functions.
object Example1{
  var log: Seq[String] = Seq.empty

  def add(a: Int, b: Int): Int = {
    log = s"Adding $a + $b" +: log
    a+b
  }
}


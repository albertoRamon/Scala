package org.hablapps.fpinscala
package lenguajes

// The signature doesn't lie anymore :)
object Example5{
  
  def divide(dividend: Int, divisor: Int): Option[Int] =
    if (divisor == 0)
      None
    else
      Option(dividend/divisor)
}


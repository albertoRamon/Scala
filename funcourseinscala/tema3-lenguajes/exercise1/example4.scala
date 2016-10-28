package org.hablapps.fpinscala
package lenguajes

object Example4{
  // If the divisor is 0 we are not returning an Int, so the signature lies.

  def divide(dividend: Int, divisor: Int): Int =
    if (divisor == 0)
      throw new RuntimeException("Sorry, can't divide by 0 :(")
    else
      dividend/divisor
}


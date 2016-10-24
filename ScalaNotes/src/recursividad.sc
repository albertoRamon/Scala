
// intuitive definition of recusice
def factorial1 (n:Int):Int =
 if (n>1)
    n* factorial1 (n-1)
 else
   1

factorial1 (3)


//with tail recusrion

def factorial2 (n:Int):Int ={
  def go (acu:Int, n:Int):Int =
    if (n>1)
      n* go (n*acu,n-1)
    else
      acu
  go (1,n)
}
factorial2 (3)



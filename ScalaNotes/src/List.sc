

//Example:Implement Generic List filter

def fileterAnyList [A](l:List[A])(cond: A =>Boolean):List[A] =
  l match {
    case Nil => Nil
    case head :: tail =>
        if (cond(head)) head::fileterAnyList(tail)(cond)
        else fileterAnyList(tail)(cond)
  }


fileterAnyList(List(1,3,4,5))(s => s<4)

//Duda
//Example: Implementar Fold sobre lista
def myFold [A](l:List[A])(op: (A,A) => A )(ini:A):A = {

  def myFoldAux [A] (l:List[A])(acu:A)(op: (A,A) => A ):A =
  {
    l match {
       case Nil => Nil
       case head :: tail => myFoldAux (tail)(op(head,acu))(op)
    }
  }


  l match {       //Primer paso
    case Nil => Nil
    case head :: tail => myFoldAux (l)(ini)(op)

  }

}


//DUDA que son los catamorfirmos
//DUDA el ejemplo creo que esta mal
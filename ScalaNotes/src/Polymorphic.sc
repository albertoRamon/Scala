// for functions

def xx[A](a:A) =List(a)                 //OK
//definimos el tipo [A] al vuelo
// como parametro entrada tiene a, que es de tipo A

//def yy[A,B](f:A=>List[A],b:B)=f(b)
// ERROR: pq la firma de f dice que tiene que recibir un tipo A, y esta recibiendo  un tipo B

def zz[A,B](f:A=>List[A],b:B, a:A)=f(a) // OK

//Duplicar elementos de una lista
def duplicateList [A](lista:List[A]):List[A]=
lista match {
  case Nil => Nil
  case head :: tail => head :: head :: duplicateList (tail)
}

duplicateList (List (1,2,3))
duplicateList (List ('A','B','C'))

def duplicateListInt (lista:List[Int]):List[Int]=
  duplicateList (lista)
duplicateListInt (List (1,2,3))

//for Class

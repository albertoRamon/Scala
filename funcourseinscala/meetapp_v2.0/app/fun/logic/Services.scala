package org.hablapps.meetup
package fun
package logic

import scalaz.syntax.monad._

import common.logic.Domain._

object ServicesWithoutSugar{ 
  
  //para cualuiqer F ya puedo tener estos metodos
  def join[F[_]](request: JoinRequest)(implicit S: Store[F]): F[JoinResponse] =
    S.doAndThen(S.getUser(request.uid)){ _ => 
      S.doAndThen(S.getGroup(request.gid)){ group => 
        if (group.must_approve)
          S.doAndThen(S.putJoin(request)){ request => 
            S.returns(Left(request))
          }
        else 
          S.doAndThen(S.putMember(Member(None, request.uid, request.gid))){ member => 
            S.returns(Right(member))
          }
      }
    }
    
}


// En esta versión utilizamos context-bounds y los operadores infijos
object ServicesWithSomeSugar{  
//es una version mejorada de lo anterior:
//  sustituir doAndThen por flatmap
//  flatmap + return =map
  import Store.Syntax._

  def join[F[_]: Store](request: JoinRequest): F[JoinResponse] =
    getUser(request.uid) flatMap { _ => 
      getGroup(request.gid) flatMap { group => 
        if (group.must_approve)
          putJoin(request) map { Left(_) }
        else 
          putMember(Member(None, request.uid, request.gid)) map {Right(_)}
      }
    }

}


/** 
 * Para terminar con el azúcar sintáctico, aprovechamos la sintaxis
 * especial que ofrece Scala para los operadores `flatMap` y `map`: 
 * las for-comprehensions.
 */
object ServicesWithForComprehensions{ 
//es una version mejorada de lo anterior:
// variable a la que asignar A <- la fdA :f[A]  la flechita desenvuelve
  import Store.Syntax._

  def join[F[_]: Store](request: JoinRequest): F[JoinResponse] = for{
    _ <- getUser(request.uid)
    group <- getGroup(request.gid)
    result <- 
        if (group.must_approve)
          putJoin(request) map { Left(_) }
        else 
          putMember(Member(None, request.uid, request.gid)) map {Right(_)}
  } yield result

}



//Por último, utilizamos el operador derivado `cond`
object Services{ 
  
  import Store.Syntax._

  def join[F[_]: Store](request: JoinRequest): F[JoinResponse] =
    for {  //forComprehensions, OJO no es un bucle,1 desenvuelve
      _      <- getUser(request.uid) //2procesamos datos 
      group  <- getGroup(request.gid) 
      result <- cond(
        test = group.must_approve, 
        left = putJoin(request), 
        right = putMember(Member(None, request.uid, request.gid)) 
      )
    } yield result // empaquetamos
    
    
    
    /*
    for {
     a<-Option(3)       //ejecuta flatmap
     b<-Option(11)      //ejecuta flatmap
     c<-Option(3)       //en el elutmimo siempre ejecuta map
    } yield 
    
    */

}


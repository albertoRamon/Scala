package org.hablapps.meetup
package fun
package mysql
package templates

import scala.concurrent.{Await, Future, duration, ExecutionContext}
import ExecutionContext.Implicits.global
import duration._

import logic._
import common.logic.Domain._
import common.mysql.Domain._

/**
 * Las modificaciones a realizar en el intérprete para adaptarse
 * a la versión asíncrona de Slick, no afectan a la API.
 */
object FutureStore extends oo.logic.Store{
  import dbConfig._, driver.api._
  
  // Operadores de Store

  def getGroup(gid: Int): Future[Group] =
    db.run(group_table.byID(Some(gid)).result.head)  

  def getUser(uid: Int): Future[User] =
    db.run(user_table.byID(Some(uid)).result.head)
  
  def putJoin(join: JoinRequest): Future[JoinRequest] =
    db.run((join_table returning join_table.map(_.jid)
      into ((req,id) => req.copy(jid = id))) += join)  

  def putMember(member: Member): Future[Member] =
    db.run((member_table returning member_table.map(_.mid)
      into ((mem,id) => mem.copy(mid = id))) += member)

  // Operadores de composición
  
}

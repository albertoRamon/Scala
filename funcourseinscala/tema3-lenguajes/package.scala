package org.hablapps.fpinscala.lenguajes{

    object SprayUtils{
        import scala.concurrent.{Future, Await, duration}, duration._
        import akka.actor.ActorSystem
        import akka.io.IO
        import akka.pattern.ask
        import akka.util.Timeout
        import spray.http._
        import spray.can.Http
        import spray.client.pipelining._

        implicit val system = ActorSystem()
        import system.dispatcher
        implicit val timeout = Timeout(1, SECONDS)

        def send(request: HttpRequest)(
            host: String = "localhost",
            port: Int = 80): Future[HttpResponse] = {
          val pipeline: Future[SendReceive] =
            for {
              Http.HostConnectorInfo(connector, _) <-
                IO(Http) ? Http.HostConnectorSetup(host, port)
            } yield sendReceive(connector)
          pipeline flatMap (_.apply(request))
        }
    }
}
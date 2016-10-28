package org.hablapps.fpinscala
package lenguajes


// Different executions of this method can return different results
// Every pure function can be cached, this is not the case.
object Example6{
  import spray.http.HttpResponse
  import spray.client.pipelining.Get
  import scala.concurrent.Future
  import org.hablapps.fpinscala.lenguajes.SprayUtils.send

  def getPhotos: Future[HttpResponse] =
    send(Get("http://example.com/photos"))("localhost",80)
}


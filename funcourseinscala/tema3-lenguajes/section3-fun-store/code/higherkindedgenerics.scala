package org.hablapps.fpinscala
package lenguajes

object HigherKindGenerics{
  
  object GenericAPIs{
    // APIs monolíticas

    trait ApiInt{
      def insert(t: Int): Option[Int]
    }

    trait ApiBoolean{
      def insert(t: Boolean): Option[Boolean]
    }

    // Abstraemos las diferencias

    trait Api[T]{
      def insert(t: T): Option[T]
    }

    // Modularización

    object Modularizacion{
      trait APIInt extends Api[Int]
      trait APIBoolean extends Api[Boolean]
    }
  }

  object HigherKindedAPIs{
    // APIs monolíticas

    trait ApiOption{
      def insert[T](t: T): Option[T]
    }
    
    trait ApiList{
      def insert[T](t: T): List[T]
    }
    
    // Abstraemos las diferencias

    trait Api[C[_]]{
      def insert[T](t: T): C[T]
    }

    // Modularización
    object Modularizacion{

      trait ApiOption extends Api[Option]{
        def insert[T](t: T): Option[T]
      }

      object ApiList extends Api[List]{
        def insert[T](t: T): List[T] = 
          List(t)
      }

      type Id[T]=T 

      trait ApiId extends Api[Id]{
        def insert[T](t: T): T
      }

      import scala.concurrent.Future
      trait ApiFuture extends Api[Future]{
        def insert[T](t: T): Future[T]
      }
    }
    
        
  }


}
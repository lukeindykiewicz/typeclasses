package typeclass.eq

import shapeless.{ HList, HNil, :: }
import shapeless.{ Coproduct, CNil, :+:, Inl, Inr }
import shapeless.Generic
import shapeless.Lazy

trait Eq[T] {
  def equ(a: T, b: T): Boolean
}

object Eq {

  def apply[T](implicit eq: Eq[T]) = eq

  object ops {
    implicit class EqOps[T: Eq](a: T) {
      def |=|(b: T): Boolean = Eq[T].equ(a, b)
    }
  }

  implicit val intEq: Eq[Int] =
    (a: Int, b: Int) => a == b

  implicit val stringEq: Eq[String] =
    (a: String, b: String) => a == b

  implicit val booleanEq: Eq[Boolean] =
    (a: Boolean, b: Boolean) => a == b

  // This is not production code!
  import scala.concurrent.Future
  implicit def futureEq[T, R](
    implicit
    eq: Lazy[Eq[R]]
  ): Eq[Future[R]] = {
    import scala.concurrent.Await
    import scala.concurrent.duration._
    import scala.concurrent.ExecutionContext.Implicits.global
    (aFuture: Future[R], bFuture: Future[R]) =>
      Await.result(
        for {
          a <- aFuture
          b <- bFuture
        } yield (eq.value.equ(a, b)),
      100 millis)
  }

  implicit def genericEq[T, R](
    implicit
    gen: Generic.Aux[T, R],
    eq: Lazy[Eq[R]]
  ): Eq[T] =
    (a: T, b: T) => eq.value.equ(gen.to(a), gen.to(b))

  implicit val hnilEq: Eq[HNil] =
    (a: HNil, b: HNil) => true

  implicit def hconsEq[H, T <: HList](
    implicit
    eqH: Lazy[Eq[H]],
    eqT: Lazy[Eq[T]]
  ): Eq[H :: T] =
    (a: H :: T, b: H :: T) =>
      eqH.value.equ(a.head, b.head) && eqT.value.equ(a.tail, b.tail)

  implicit val cnilEq: Eq[CNil] =
    (a: CNil, b: CNil) => true

  implicit def cconsEq[H, T <: Coproduct](
    implicit
    eqH: Lazy[Eq[H]],
    eqT: Lazy[Eq[T]]
  ): Eq[H :+: T] =
    (a: H :+: T, b: H :+: T) => (a, b) match {
      case (Inl(ah), Inl(bh)) => eqH.value.equ(ah, bh)
      case (Inr(at), Inr(bt)) => eqT.value.equ(at, bt)
      case _ => false
    }

}

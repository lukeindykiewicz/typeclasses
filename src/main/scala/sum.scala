package sum

import shapeless.{ HList, HNil, :: }
import shapeless.{ Coproduct, :+:, Inl, Inr, CNil }
import shapeless.Generic
import shapeless.Lazy

trait Sum[T] {
  def sum(a: T, b: T): T
}

object Sum {

  def apply[T](implicit sum: Sum[T]) = sum

  object ops {
    implicit class SumOps[T: Sum](a: T) {
      def |+|(b: T): T = Sum[T].sum(a, b)
    }
  }

  implicit val intSum: Sum[Int] =
    (a: Int, b: Int) => a + b

  implicit val stringSum: Sum[String] =
    (a: String, b: String) => a + b

  implicit def genericSum[T, R](
    implicit
    gen: Generic.Aux[T, R],
    sum: Lazy[Sum[R]]
  ): Sum[T] =
    (a: T, b: T) =>
      gen.from(sum.value.sum(gen.to(a), gen.to(b)))

  implicit val hnilSum: Sum[HNil] =
    (a: HNil, b: HNil) => a

  implicit def hconsSum[H, T <: HList](
    implicit
    hSum: Lazy[Sum[H]],
    tSum: Lazy[Sum[T]]
  ): Sum[H :: T] =
    (a: H :: T, b: H :: T) =>
      hSum.value.sum(a.head, b.head) :: tSum.value.sum(a.tail, b.tail)

}

trait Sum2[T, U] {
  type Res
  def sum(a: T, b: U): Res
}

object Sum2 {

  object ops {
    implicit class Sum2FunctionsOps[A, B, C]
      (a: A => B)
      (implicit sum2: Sum2[A => B, B => C] {type Res = A => C}) {
        def |+|(b: B => C): A => C = sum2.sum(a, b)
    }

    implicit class Sum2Ops[A, B, C]
      (a: A)
      (implicit sum2: Sum2[A, B] {type Res = C}) {
        def |+|(b: B): C = sum2.sum(a, b)
    }
  }

  implicit val intStringBooleanSum: Sum2[Int, String] {type Res = Boolean} =
    new Sum2[Int, String] {
      type Res = Boolean
      def sum(a: Int, b: String): Boolean =
        b.length == a
    }

  implicit def functionSum[A, B, C]: Sum2[A => B, B => C] {type Res = A => C} =
    new Sum2[A => B, B => C] {
      type Res = A => C
      def sum(a: A => B, b: B => C): A => C = a andThen b
    }

}

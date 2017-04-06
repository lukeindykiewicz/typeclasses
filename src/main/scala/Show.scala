package show

import shapeless.{ HNil, HList, :: }
import shapeless._
import shapeless.labelled.{ field, FieldType }
import shapeless.Generic
import shapeless.LabelledGeneric
import shapeless.{Coproduct, :+:, CNil, Inl, Inr}
import shapeless.Witness
import scala.reflect.runtime.universe.TypeTag

trait Show[A] {
  def show(a: A): String
}

object Show {

  def apply[A](implicit sh: Show[A]): Show[A] = sh

  object ops {
    def show[A: Show](a: A) = Show[A].show(a)

    implicit class ShowOps[A: Show](a: A) {
      def show = Show[A].show(a)
    }
  }

  implicit val intCanShow: Show[Int] =
    int => s"int $int"

  implicit val stringCanShow: Show[String] =
    str => s"string $str"

  implicit val hnilCanShow: Show[HNil] =
    hnil => ""

  implicit def hlistCanShow[H, T <: HList](
    implicit
    headCanShow: Show[H],
    tailCanShow: Show[T]
  ): Show[H :: T] =
    { case h :: t => s"${headCanShow.show(h)}, ${tailCanShow.show(t)}" }

  implicit def genericCanShow[A, R](
    implicit
    gen: Generic.Aux[A,R],
    canShow: Show[R]
  ): Show[A] =
    a => canShow.show(gen.to(a))

}

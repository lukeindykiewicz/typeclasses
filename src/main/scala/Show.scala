package show

trait Show[A] {
  def show(a: A): String
}

object Show {

  def apply[A](implicit sh: Show[A]): Show[A] = sh

  def show[A: Show](a: A) = Show[A].show(a)

  implicit class ShowOps[A: Show](a: A) {
    def show = Show[A].show(a)
  }

  def instance[A](func: A => String): Show[A] =
      new Show[A] {
        def show(a: A): String = func(a)
      }

  implicit val intCanShow: Show[Int] =
      instance(int => s"int $int")

  implicit val stringCanShow: Show[String] =
      instance(str => s"string $str")

}

package show

trait Show[A] {
  def show(a: A): String
}

object Show {

  def apply[A](implicit sh: Show[A]): Show[A] = sh

  def show[A: Show](a: A) = Show[A].show(a)

  implicit class ShowOps[A: Show](a: A) {
    def show = Show[A].show(a)
    def showExp(implicit sh: Show[A]) = sh.show(a)
  }

  implicit val intCanShow: Show[Int] =
    int => s"int $int"

  implicit val stringCanShow: Show[String] =
    str => s"string $str"

}

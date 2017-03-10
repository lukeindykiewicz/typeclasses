package show

trait Show[A] {
  def show(a: A): String
}

object Show {

  def apply[A](implicit sh: Show[A]): Show[A] = sh

  def show[A: Show](a: A) = Show[A].show(a)

  implicit val intCanShow: Show[Int] =
    new Show[Int] {
      def show(int: Int): String = s"int $int"
    }

}

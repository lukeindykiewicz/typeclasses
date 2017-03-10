package show

trait Show[A] {
  def show(a: A): String
}

object Show {

  def show[A](a: A)(implicit sh: Show[A]) = sh.show(a)

  implicit val intCanShow: Show[Int] =
    new Show[Int] {
      def show(int: Int): String = s"int $int"
    }

}

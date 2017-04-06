package mainshow

import show.Show
import show.Show.ops._

//for simulacrum based implementation
import showsimulacrum.ShowSim
import showsimulacrum.ShowSim.ops._

object MainShow extends App {

  case class Foo(foo: Int)
  case class Bar(b: Int, a: String, r: Int)

  println(show(20))
  println(30.show)
  println("foofoo".show)
  println(Foo(42).show)

  println("bar".showSim)

  println("baz".show)

  {
    implicit val hipsterString: Show[String] =
      str => s"""hipster string $str."""

    println("bazbaz".show)
  }

  println(Bar(123, "11", 42))
  println(Bar(123, "11", 42).show)

}

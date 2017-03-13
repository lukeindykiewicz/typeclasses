package mainshow

import show.Show
import show.Show._

//for simulacrum based implementation
import showsimulacrum.ShowSim
import showsimulacrum.ShowSim.ops._

object MainShow extends App {

  case class Foo(foo: Int)

  implicit val fooShow: Show[Foo] =
    foo => s"case class Foo(foo: ${foo.foo})"

  println(show(20))
  println(30.show)
  println("foofoo".show)
  println(Foo(42).show)

  println("bar".showSim)

}

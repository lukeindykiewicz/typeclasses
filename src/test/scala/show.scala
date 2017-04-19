package show

import org.scalatest._

import show.Show.ops._

class ShowSpec extends FlatSpec with Matchers {

  case class Foo(foo: Int)
  case class Bar(b: Int, a: String, r: Int)

  "show" should "work when called as function" in {
    show(20) shouldBe "int 20"
  }

  it should "work when called with dot notation" in {
    20.show shouldBe "int 20"
  }

  it should "work with strings" in {
    "foofoo".show shouldBe "string foofoo"
  }

  it should "work with case classes (products)" in {
    Foo(42).show shouldBe "ShowSpec.this.Foo :: foo: int 42, "
    Bar(123, "11", 42).show shouldBe "ShowSpec.this.Bar :: b: int 123, a: string 11, r: int 42, "
  }

  it should "allow instance override by implicit precedence" in {
    implicit val hipsterString: Show[String] =
      str => s"""hipster string $str"""

    "bazbaz".show shouldBe "hipster string bazbaz"
  }

  "show implemented with Simulacrum" should "just work" in {
    import showsimulacrum.ShowSim.ops._
    "bar".showSim shouldBe "simulacrum string bar"
  }

}

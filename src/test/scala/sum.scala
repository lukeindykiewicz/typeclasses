package sum

import org.scalatest._

import sum.Sum.ops._
import sum.Sum2.ops._

import typeclass.eq.Eq
import typeclass.eq.Eq.ops._

class SumSpec extends FlatSpec with Matchers {

  "sum of ints" should "sum two ints" in {
    1 |+| 2 shouldBe 3
  }

  it should "sum many ints" in {
    1 |+| 2 |+| 3 shouldBe 6
  }

  it should "sum list of ints" in {
    List(1,2,3).foldLeft(0)(_ |+| _) shouldBe 6
  }

  it should "map over list of ints" in {
    List(1,2,3).map(_ |+| 2) shouldBe List(3,4,5)
  }

  it should "work with equals" in {
    1 |+| 2 |+| 3 |=| 6 shouldBe true
  }

  "sum of strings" should "concatenate strings" in {
    "hello" |+| "world" shouldBe "helloworld"
  }

  "sum different types" should "do wired things" in {
    5 |+| "hello" shouldBe true
  }

  "sum of functions" should "behave like andThen" in {
    val f1: String => Int = _.length
    val f2: Int => String = n => "hello" * n
    val f3: String => String = f1 |+| f2
    f3("..") shouldBe "hellohello"
  }

  "sum of more functions" should "behave like andThen" in {
    val f1: String => Int = _.length
    val f2: Int => String = n => "hello" * n
    val f3: String => Int = f1 |+| f2 |+| f1
    f3("..") shouldBe 10
  }

  "product types" should "add each elemment respectively" in {
    case class Foo(a: Int, b: Int, c: String)

    Foo(10, 100, "foo") |+| Foo(1, 11, "bar") shouldBe Foo(11, 111, "foobar")
  }

  "coproduct types" should "add values in option" in {
    Some(2) |+| Some(5) shouldBe Some(7)
  }

}

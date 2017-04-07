package mainshow

import eq.Eq
import eq.Eq.ops._

object MainEq extends App {

  sealed trait Base
  sealed trait Baz extends Base
  case class Foo1(foo: Int) extends Baz
  case class Bar(b: Int, a: String, r: Int) extends Baz
  case class Foo2(foo: Int) extends Base

  // nice joke :)
  // implicit val intJokeEq: Eq[Int] =
  //   (a: Int, b: Int) => if (b == 13) a == b else a != b

  println(14 === 13)
  println(14 === 14)

  println(Foo1(10) === Foo1(20))
  println(Foo1(20) === Foo1(20))
  println(Bar(20, "bar", 30) === Bar(20, "bar", 30))


  val foo1: Base = Foo1(10)
  val foo2: Base = Foo1(20)
  val foo3: Base = Foo2(10)
  println(foo1 === foo2)
  println(foo1 === foo3)

  println("aa" == "bb")
  println(true === false === false)

  val s10: Option[Int] = Some(10)
  val s2 = Some(2)
  val n: Option[Int] = None
  println(s10 === n)
  println(s10 === s2)
  println(s10 === s10)

  val a: Either[String, Int] = Right(10)
  val b: Either[String, Int] = Left("error")
  val c: Either[String, Int] = Right(20)
  val d: Either[String, Int] = Right(10)
  println(a === b)
  println(a === c)
  println(a === d)

  import scala.concurrent.Future
  import scala.concurrent.ExecutionContext.Implicits.global

  val f1: Future[Int] = Future(10)
  val f2: Future[Int] = Future(20)
  val f3: Future[Int] = Future(10)
  println(f1 === f2)
  println(f1 === f3)
}

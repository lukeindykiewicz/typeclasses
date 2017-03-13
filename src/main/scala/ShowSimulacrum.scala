package showsimulacrum

import simulacrum._

@typeclass trait ShowSim[A] {
  def showSim(a: A): String
}

object ShowSim {
  implicit val stringCanShow: ShowSim[String] =
    str => s"simulacrum string $str"
}

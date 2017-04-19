name := """typeclasses"""

version := "0.1"

scalaVersion := "2.12.1"

libraryDependencies ++= Seq(
  "com.chuusai"     %% "shapeless"      % "2.3.2",
  "org.scala-lang"  %  "scala-reflect"  % scalaVersion.value,
  "org.scalactic"   %% "scalactic"      % "3.0.1" % "test",
  "org.scalatest"   %% "scalatest"      % "3.0.1" % "test"
)

//for simulacrum
addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full)
libraryDependencies += "com.github.mpilquist" %% "simulacrum" % "0.10.0"

//for shapeless
resolvers ++= Seq(
  Resolver.sonatypeRepo("releases"),
  Resolver.sonatypeRepo("snapshots"),
  "Artima Maven Repository" at "http://repo.artima.com/releases"
)

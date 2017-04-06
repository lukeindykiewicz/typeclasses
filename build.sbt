name := """typeclasses"""

version := "0.1"

scalaVersion := "2.12.1"

// libraryDependencies ++= {
//   val circeVersion = "0.7.0"
//   Seq(
//     "io.circe"        %% "circe-core"       % circeVersion,
//     "io.circe"        %% "circe-generic"    % circeVersion,
//     "io.circe"        %% "circe-parser"     % circeVersion
//   )
// }

libraryDependencies ++= Seq(
  "com.chuusai"     %% "shapeless"    % "2.3.2",
  "org.scala-lang"  % "scala-reflect" % scalaVersion.value
)

//for simulacrum
addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full)
libraryDependencies += "com.github.mpilquist" %% "simulacrum" % "0.10.0"

//for shapeless
resolvers ++= Seq(
  Resolver.sonatypeRepo("releases"),
  Resolver.sonatypeRepo("snapshots")
)

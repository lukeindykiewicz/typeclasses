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

//for simulacrum
addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full)
libraryDependencies += "com.github.mpilquist" %% "simulacrum" % "0.10.0"

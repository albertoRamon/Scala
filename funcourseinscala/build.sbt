name := "funcourseinscala"

scalaVersion := "2.11.7"

organization := "org.hablapps"

unmanagedSourceDirectories in Compile ++= Seq(
  baseDirectory.value / "tema1-hofs"
)

unmanagedSourceDirectories in Test ++= Seq(
  baseDirectory.value / "tema1-hofs" / "exercise1-tetris" / "test",
  baseDirectory.value / "tema1-hofs" / "exercise2-composicion" / "test",
  baseDirectory.value / "tema1-hofs" / "exercise3-fold" / "test",
  baseDirectory.value / "tema1-hofs" / "homework1" / "test",
  baseDirectory.value / "tema1-hofs" / "homework2" / "test",
  baseDirectory.value / "tema1-hofs" / "homework3" / "test"
)

resourceDirectory in Compile := baseDirectory.value / "resources"

resolvers ++= Seq(
  "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/",
  "rediscala" at "http://dl.bintray.com/etaty/maven",
  "spray repo" at "http://repo.spray.io",
  Resolver.sonatypeRepo("releases")
)

addCompilerPlugin("org.spire-math" %% "kind-projector" % "0.7.1")

libraryDependencies ++= Seq(
  "org.spire-math" %% "cats" % "0.3.0",
  "org.scalaz" %% "scalaz-core" % "7.2.0",
  "org.scalaz" %% "scalaz-scalacheck-binding" % "7.2.0",
  "com.typesafe.akka" %% "akka-actor" % "2.3.2",
  "com.etaty.rediscala" %% "rediscala" % "1.3.1",
  "io.spray" %% "spray-client" % "1.3.3",
  "io.spray" %% "spray-json" % "1.3.2",
  "org.apache.spark" %% "spark-core" % "1.6.1",
  "org.scalatest" %% "scalatest" % "2.2.6"
)

scalacOptions ++= Seq(
  "-unchecked",
  "-deprecation",
  "-feature",
  // "-Xprint:typer",
  // "-Xlog-implicit-conversions",
  "-language:postfixOps",
  "-language:higherKinds")

initialCommands in console := """
  | import org.hablapps.fpinscala._
  | import hofs.funciones.templates._
  | import hofs.diagrams.code._
  | import hofs.diagrams.templates._
  | import hofs.modularidad.templates._
  | import hofs.hofs.templates._
  |""".stripMargin

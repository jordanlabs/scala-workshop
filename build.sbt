organization := "com.example"

name := "Rest Api"

val unusedWarnings = (
  "-Ywarn-unused" ::
  "-Ywarn-unused-import" ::
  Nil
)

scalacOptions ++= PartialFunction.condOpt(CrossVersion.partialVersion(scalaVersion.value)){
  case Some((2, v)) if v >= 11 => unusedWarnings
}.toList.flatten

Seq(Compile, Test).flatMap(c =>
  scalacOptions in (c, console) --= unusedWarnings
)

scalacOptions ++= "-deprecation" :: "unchecked" :: "-feature" :: Nil

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.12.4"

val unfilteredVersion = "0.9.1"
val argonautVersion = "6.2"

libraryDependencies ++= Seq(
   "ws.unfiltered" %% "unfiltered-netty-server" % unfilteredVersion,
   "net.databinder.dispatch" %% "dispatch-core" % "0.13.2",
   "ws.unfiltered" %% "unfiltered-specs2" % unfilteredVersion % "test",
   "io.argonaut" %% "argonaut" % argonautVersion,
   "io.argonaut" %% "argonaut-scalaz" % argonautVersion,
   "io.argonaut" %% "argonaut-monocle" % argonautVersion,
   "io.argonaut" %% "argonaut-cats" % argonautVersion
)

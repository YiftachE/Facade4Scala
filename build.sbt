name := "Facade4Scala"

version := "0.0"

libraryDependencies ++= Seq(
  "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.7.2",
  "com.eed3si9n" %% "treehugger" % "0.4.1",
  "org.scalaj" %% "scalaj-http" % "2.3.0",
  "org.scalatest" %% "scalatest" % "3.0.0" % "test"
)
resolvers += Resolver.sonatypeRepo("public")


scalaVersion := "2.11.8"

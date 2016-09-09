name := "Facade4Scala"

version := "0.0"

libraryDependencies ++= Seq(
  "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.7.2",
  "com.eed3si9n" %% "treehugger" % "0.4.1")
resolvers += Resolver.sonatypeRepo("public")


scalaVersion := "2.11.8"

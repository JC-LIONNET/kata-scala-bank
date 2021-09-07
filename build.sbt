import Dependencies._
name := "kata-scala-bank"

version := "0.1"

scalaVersion := "2.13.6"

lazy val root = (project in file("."))
  .settings(
    name := s"${name}",
    scalaVersion := s"${scalaVersion}",
    libraryDependencies += "ch.qos.logback" % "logback-classic" % logbackVersion,
    libraryDependencies += "org.scalatest" %% "scalatest" % scalaTestVersion % Test,
    libraryDependencies += "io.cucumber" %% "cucumber-scala" % cucumberScalaVersion % Test,
    libraryDependencies += "io.cucumber" % "cucumber-junit" % cucumberJUnitScalaVersion % Test,
    libraryDependencies += "junit" % "junit" % jUnitVersion,
    libraryDependencies += "com.novocode" % "junit-interface" % jUnitInterfaceVersion % Test,
    libraryDependencies += "org.mockito" % "mockito-core" % "3.12.4" % Test
  )
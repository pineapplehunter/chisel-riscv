name := "chisel-riscv"

version := "0.1"

scalaVersion := "2.12.12"

scalacOptions ++= Seq(
  "-Xsource:2.11"
)

libraryDependencies += "edu.berkeley.cs" %% "chisel3" % "3.4.4"
libraryDependencies += "edu.berkeley.cs" %% "chiseltest" % "0.3.4" % "test"
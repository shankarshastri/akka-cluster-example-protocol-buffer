name := "akka-cluster-example-protocol-buffer"

version := "0.1"

scalaVersion := "2.13.0"


libraryDependencies ++= Seq("com.typesafe.akka" %% "akka-actor" % "2.5.25",
                            "com.typesafe.akka" %% "akka-cluster" % "2.5.25",
                            "com.thesamet.scalapb" %% "scalapb-runtime" % scalapb.compiler.Version.scalapbVersion % "protobuf",
                            "io.kamon" % "sigar-loader" % "1.6.6-rev002")

PB.targets in Compile := Seq(
  scalapb.gen() -> (sourceManaged in Compile).value
)
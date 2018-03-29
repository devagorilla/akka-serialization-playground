name := "akka-serialization-playground"

version := "1.0"

scalaVersion := "2.12.4"

lazy val commonV = "0.0.7"
lazy val akkaVersion = "2.5.3"
lazy val avro4sVersion = "1.8.3"

resolvers += "Envision Snapshots" at "s3://s3-us-west-2.amazonaws.com/snapshots.envision-diagnostics.com"
resolvers += "Envision Releases" at "s3://s3-us-west-2.amazonaws.com/releases.envision-diagnostics.com"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
  "com.typesafe.akka" %% "akka-persistence" % akkaVersion,
  "com.typesafe.akka" %% "akka-stream" % akkaVersion,
  "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
  "com.sksamuel.avro4s" %% "avro4s-core" % avro4sVersion,
  "com.endiag"                    %% "csr-common"           % commonV,
  "com.typesafe.akka" %% "akka-stream-testkit" % akkaVersion % Test,
  "com.typesafe.akka" %% "akka-testkit"        % akkaVersion % Test
)

fork in Test := true

scalacOptions ++= Seq("-feature", "-language:higherKinds", "-language:implicitConversions", "-deprecation", "-target:jvm-1.8")

javaOptions in Test ++= Seq("-Xms30m", "-Xmx30m")

name := "PrimeTest"

version := "1.0"

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  "net.debasishg" %% "redisclient" % "2.15",
  "junit" % "junit" % "4.8.1" % "test"
)


resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

scalacOptions in Test ++= Seq("-Yrangepos")

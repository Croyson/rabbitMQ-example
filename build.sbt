name := "rabbitMQTest"

version := "1.0"

scalaVersion := "2.12.2"

libraryDependencies ++= Seq(

  "com.rabbitmq" % "amqp-client"  % "3.6.3"

)

resolvers ++= Seq(

  "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
)
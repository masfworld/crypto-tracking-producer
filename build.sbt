name := "crypto-tracking-producer"

version := "0.1"

scalaVersion := "2.12.8"

libraryDependencies += "javax.ws.rs" % "javax.ws.rs-api" % "2.1"

// https://mvnrepository.com/artifact/info.bitrich.xchange-stream/xchange-stream-core
libraryDependencies += "info.bitrich.xchange-stream" % "xchange-stream-core" % "4.3.15"

// https://mvnrepository.com/artifact/info.bitrich.xchange-stream/xchange-bitstamp
libraryDependencies += "info.bitrich.xchange-stream" % "xchange-bitstamp" % "4.3.15"

// https://mvnrepository.com/artifact/info.bitrich.xchange-stream/xchange-coinbasepro
libraryDependencies += "info.bitrich.xchange-stream" % "xchange-coinbasepro" % "4.3.15"

// https://mvnrepository.com/artifact/info.bitrich.xchange-stream/xchange-gdax
libraryDependencies += "info.bitrich.xchange-stream" % "xchange-gdax" % "4.3.11"

// https://mvnrepository.com/artifact/info.bitrich.xchange-stream/xchange-bitfinex
libraryDependencies += "info.bitrich.xchange-stream" % "xchange-bitfinex" % "4.3.16"


// https://mvnrepository.com/artifact/org.apache.kafka/kafka
libraryDependencies += "org.apache.kafka" %% "kafka" % "2.2.1"

libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2"
libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.2.3"

// also add ScalaTest as a framework to run the tests
libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.8"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.8" % Test

// Add the ScalaMock library (versions 4.0.0 onwards)
libraryDependencies += "org.scalamock" %% "scalamock" % "4.3.0" % Test

// https://mvnrepository.com/artifact/org.mockito/mockito-scala
libraryDependencies += "org.mockito" %% "mockito-scala" % "1.5.16" % Test

// https://mvnrepository.com/artifact/io.spray/spray-json
libraryDependencies += "io.spray" %% "spray-json" % "1.3.5"

// Configuration library
libraryDependencies += "com.typesafe" % "config" % "1.3.4"

// Akka
libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.5.25"
libraryDependencies += "com.typesafe.akka" %% "akka-stream" % "2.5.25"

libraryDependencies += "com.typesafe.akka" %% "akka-stream" % "2.5.25"


name := "crypto-tracking-producer"

version := "0.1"

scalaVersion := "2.12.8"

// https://mvnrepository.com/artifact/info.bitrich.xchange-stream/xchange-stream-core
libraryDependencies += "info.bitrich.xchange-stream" % "xchange-stream-core" % "4.3.15"

// https://mvnrepository.com/artifact/info.bitrich.xchange-stream/xchange-bitstamp
libraryDependencies += "info.bitrich.xchange-stream" % "xchange-bitstamp" % "4.3.15"

// https://mvnrepository.com/artifact/info.bitrich.xchange-stream/xchange-coinbasepro
libraryDependencies += "info.bitrich.xchange-stream" % "xchange-coinbasepro" % "4.3.15"

// https://mvnrepository.com/artifact/info.bitrich.xchange-stream/xchange-gdax
libraryDependencies += "info.bitrich.xchange-stream" % "xchange-gdax" % "4.3.11"

// https://mvnrepository.com/artifact/org.apache.kafka/kafka
libraryDependencies += "org.apache.kafka" %% "kafka" % "2.2.1"

libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2"
libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.2.3"
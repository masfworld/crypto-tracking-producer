package com.sidesna.crypto.producer.helpers

import java.util.Properties

import com.typesafe.config.{Config, ConfigFactory}

trait Configuration {

  private val defaultConfig = ConfigFactory.parseResources("defaults.conf")

  private val fallbackConfig = ConfigFactory.parseResources("overrides.conf")
    .withFallback(defaultConfig)
    .resolve()

  /**
    * Converting config to Java Properties object
    *
    * @param config Configuration object
    * @return Properties Java Object
    **/
  private def propsFromConfig(config: Config): Properties = {
    import scala.collection.JavaConverters._

    val props = new Properties()

    // putAll method Failing with JDK 9
    /*val map: Map[String, Object] = config.entrySet().asScala.map({ entry =>
      entry.getKey -> entry.getValue.unwrapped()
    })(collection.breakOut)
    props.putAll(map.asJava)*/

    config.entrySet().asScala.foreach( x => props.put(x.getKey, x.getValue))

    props
  }

  /**
    * Loading Kafka Properties from configuration
    *
    * @return Properties Java Object containing Kakfa information
    **/
  def loadKakfaProperties: Properties = {
    val kafkaConfig = fallbackConfig.getConfig("kafka")
    propsFromConfig(kafkaConfig)
  }

}

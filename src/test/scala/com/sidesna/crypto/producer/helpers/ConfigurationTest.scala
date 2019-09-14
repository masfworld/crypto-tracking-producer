package com.sidesna.crypto.producer.helpers


import java.util.Properties

import org.scalatest.{FeatureSpec, GivenWhenThen, Matchers}

class ConfigurationTest extends FeatureSpec
  with GivenWhenThen with Matchers
  with Configuration {

  feature("Configuration testing") {
    scenario("Overrides configuration for Kakfa") {

      val kafkaProps = loadKakfaProperties

      val props = new Properties()
      props.put("bootstrap.servers", "localhost:9092")
      props.put("client.id", "gdaxProducer")
      props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
      props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")


      kafkaProps should equal (props)

    }
  }
}

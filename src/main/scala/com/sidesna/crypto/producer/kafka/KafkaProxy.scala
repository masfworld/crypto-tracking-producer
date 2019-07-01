package com.sidesna.crypto.producer.kafka

import java.util.Properties

import com.typesafe.scalalogging.LazyLogging
import org.apache.kafka.clients.producer.{Callback, KafkaProducer, ProducerRecord, RecordMetadata}

import scala.concurrent.Promise

object KafkaProxy extends LazyLogging{

  private val producer = initKafka()

  private def initKafka(): KafkaProducer[String, String] = {
    val props = new Properties()
    props.put("bootstrap.servers", "192.168.1.5:9092")
    props.put("client.id", "gdaxProducer")
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    new KafkaProducer[String, String](props)
  }

  def sendTo(topic: Topic, key: Key, msg: String) = {
    val data = new ProducerRecord[String, String](topic.name, key.name, msg)
    logger.debug(s"Sending to topic ${topic.name} - $msg")
    val p = Promise[(RecordMetadata, Exception)]()
    producer.send(data, new Callback {
      override def onCompletion(metadata: RecordMetadata, exception: Exception): Unit = {
        p.success((metadata, exception))
      }
    })
  }

}
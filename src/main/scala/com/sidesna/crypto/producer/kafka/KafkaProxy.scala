package com.sidesna.crypto.producer.kafka

import com.sidesna.crypto.producer.helpers.Configuration
import com.typesafe.scalalogging.LazyLogging
import org.apache.kafka.clients.producer.{Callback, KafkaProducer, ProducerRecord, RecordMetadata}

import scala.concurrent.Promise


trait KafkaProxy {
  def sendTo(topic: Topic, key: Key, msg: String)
}

object KafkaProxy extends LazyLogging with KafkaProxy with Configuration {

  private val producer = initKafka()

  private def initKafka(): KafkaProducer[String, String] = {
    new KafkaProducer[String, String](loadKakfaProperties)
  }

  /**
    * Sending message to kafka topic
    *
    * @param topic Kafka topic to send message
    * @param key   Kafka Key to be sent
    * @param msg   Message to be sent
    **/
  def sendTo(topic: Topic, key: Key, msg: String) = {
    val data = new ProducerRecord[String, String](topic.name, key.name, msg)
    logger.debug(s"Sending to topic ${topic.name} - $msg")
    val p = Promise[(RecordMetadata, Exception)]()
    producer.send(data, new Callback {
      override def onCompletion(metadata: RecordMetadata, exception: Exception): Unit = {
        if (exception != null) throw exception
        p.success((metadata, exception))
      }
    })
  }

}
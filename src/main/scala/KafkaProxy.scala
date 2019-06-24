import java.util.Properties

import org.apache.kafka.clients.producer.{Callback, KafkaProducer, ProducerRecord, RecordMetadata}

import scala.concurrent.Promise

object KafkaProxy {

  private val producer = initKafka()

  private def initKafka(): KafkaProducer[String, String] = {
    val props = new Properties()
    props.put("bootstrap.servers", "localhost:9092")
    props.put("client.id", "gdaxProducer")
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    new KafkaProducer[String, String](props)
  }

  def sendTo(topic: Topic, key: Key, msg: String) = {
    val data = new ProducerRecord[String, String](topic.name, key.name, msg)
    val p = Promise[(RecordMetadata, Exception)]()
    producer.send(data, new Callback {
      override def onCompletion(metadata: RecordMetadata, exception: Exception): Unit = {
        p.success((metadata, exception))
      }
    })
  }

}

sealed trait Topic {
  def name: String
}

case object TicksTopic extends Topic {
  val name = "ticks"
}

sealed trait Key {
  def name: String
}

case object TicksKey extends Key {
  val name = "gdax_ticks"
}
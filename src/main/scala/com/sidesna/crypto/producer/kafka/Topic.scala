package com.sidesna.crypto.producer.kafka

sealed trait Topic {
  def name: String
}

case object TopicTicks extends Topic {
  val name = "ticks"
}


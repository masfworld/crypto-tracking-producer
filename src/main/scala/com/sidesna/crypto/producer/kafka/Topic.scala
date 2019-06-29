package com.sidesna.crypto.producer.kafka

sealed trait Topic {
  def name: String
}

case object TopicTicks extends Topic {
  val name = "ticks"
}

sealed trait Key {
  def name: String
}

case object KeyTicks extends Key {
  val name = "gdax_ticks"
}

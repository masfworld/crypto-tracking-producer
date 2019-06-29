package com.sidesna.crypto.producer.kafka

sealed trait Key {
  def name: String
}

case object KeyTicks extends Key {
  val name = "gdax_ticks"
}

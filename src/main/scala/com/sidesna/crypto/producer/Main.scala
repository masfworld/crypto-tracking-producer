package com.sidesna.crypto.producer

//import info.bitrich.xchangestream.gdax.GDAXStreamingExchange
import akka.actor.ActorSystem
import com.sidesna.crypto.producer.actors.ExchangeActor._
import com.sidesna.crypto.producer.actors.{ExchangeActor, ScalpingBaseActor}
import info.bitrich.xchangestream.bitfinex.BitfinexStreamingExchange
import org.knowm.xchange.currency.CurrencyPair

object Main {

  def main(args: Array[String]): Unit = {

    implicit val actorSystem = ActorSystem("ExchangePublisher")

    val scalpingBaseActor =
      actorSystem.actorOf(ScalpingBaseActor.props(CurrencyPair.BTC_USD))

    val exchangeActor =
      actorSystem.actorOf(ExchangeActor.props[BitfinexStreamingExchange](CurrencyPair.BTC_USD, scalpingBaseActor))

    exchangeActor ! ConsumingTicks

    exchangeActor ! ConsumingOrderBooks

    // Create the 'helloAkka' actor system
    //val system: ActorSystem = ActorSystem("algorithmAkka")

    //ExchangeProxy[GDAXStreamingExchange](CurrencyPair.BTC_EUR).start
    //ExchangeProxy[BitfinexStreamingExchange](CurrencyPair.BTC_EUR).start

    println("Starting")
    System.in.read()

  }
}

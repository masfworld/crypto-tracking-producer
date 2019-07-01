package com.sidesna.crypto.producer

import com.typesafe.scalalogging.LazyLogging
import info.bitrich.xchangestream.core.{ProductSubscription, StreamingExchange, StreamingExchangeFactory}
import org.knowm.xchange.currency.CurrencyPair

import scala.reflect.runtime.universe._
import com.sidesna.crypto.producer.kafka._
import com.sidesna.crypto.producer.helpers.TickerCustom

/**
  * A class to represent a ''Exchange proxy''.
  *
  * Specify the `name`, `age`, and `weight` when creating a new `ExchangeProxy`,
  * then execute like this:
  *
  * {{{
  * ExchangeProxy[GDAXStreamingExchange](CurrencyPair.BTC_EUR).start
  * }}}
  *
  * Did you know: The [[info.bitrich.xchangestream.core.StreamingExchange]] is the interface to support generic class.
  *
  * @param currencyPair Currency to ingest. BTC/EUR, BTC/USD, ...
  * @author Miguel Sotomayor
  * @version 1.0
  * @todo Add more functionality.
  */
case class ExchangeProxy[S <: StreamingExchange : TypeTag](currencyPair: CurrencyPair) extends LazyLogging{

  /**
    * @return Returns generic class name
    */
  private def getClassName = typeOf[S].typeSymbol.fullName.toString

  /**
    * @return Returns [[info.bitrich.xchangestream.core.ProductSubscription]]
    */
  private def getProductSubscription: ProductSubscription = {
    ProductSubscription.create()
      .addAll(currencyPair)
      .build()
  }


  /**
    * @param exchange StreamingExchange created previously linked to a specific exchange service
    */
  private def sendTicks(exchange: StreamingExchange) = {
    try {
      exchange
        .getStreamingMarketDataService
        .getTicker(currencyPair)
        .subscribe(ticker => {
          logger.debug(s"Sending to Kafka: ${TickerCustom.formatTicker(ticker)}")
          KafkaProxy.sendTo(TopicTicks, KeyTicks, TickerCustom.formatTicker(ticker))
        })
    }
    catch { case _: Throwable => logger.error("Error in subscription")}
  }

  /**
    * Starting de connection with the exchange and sending data to Kafka server
    */
  def start = {
    val exchange = StreamingExchangeFactory
      .INSTANCE
      .createExchange(getClassName)

    // Connect to the Exchange WebSocket API. Blocking wait for the connection.
    exchange
      .connect(getProductSubscription)
      .blockingAwait()

    sendTicks(exchange)

  }

  /*val subscription = exchange
  .getStreamingMarketDataService
  .getOrderBook(CurrencyPair.BTC_USD)
  .subscribe(orderBook => {
    println(
      s""" Orderbook:
         | $orderBook
    """.stripMargin)
  })
*/

}

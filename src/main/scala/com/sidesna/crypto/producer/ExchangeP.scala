package com.sidesna.crypto.producer

import akka.stream.scaladsl.Source
import info.bitrich.xchangestream.core.{ProductSubscription, StreamingExchange, StreamingExchangeFactory}
import io.reactivex.BackpressureStrategy
import org.knowm.xchange.currency.CurrencyPair

import scala.reflect.runtime.universe._

abstract class ExchangeP[S <: StreamingExchange : TypeTag](currencyPair: CurrencyPair) {

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

  val exchange = StreamingExchangeFactory
    .INSTANCE
    .createExchange(getClassName)

  private def start = {
    exchange
      .connect(getProductSubscription)
      .blockingAwait()
  }

  start

  protected val publisherTick = exchange
    .getStreamingMarketDataService
    .getTicker(currencyPair)
    .toFlowable(BackpressureStrategy.MISSING)

  protected val publisherOrderBook = exchange
    .getStreamingMarketDataService
    .getOrderBook(currencyPair)
    .toFlowable(BackpressureStrategy.MISSING)

  protected val sourceTicket = Source
    .fromPublisher(publisherTick)

  protected val sourceOrderBook = Source
    .fromPublisher(publisherOrderBook)

}

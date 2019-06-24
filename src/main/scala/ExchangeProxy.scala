import com.sun.tools.javac.code.TypeTag
import info.bitrich.xchangestream.core.{ProductSubscription, StreamingExchange, StreamingExchangeFactory}
import org.knowm.xchange.currency.CurrencyPair

import scala.reflect.runtime.universe.typeOf


case class ExchangeProxy[S <: StreamingExchange](currencyPair: CurrencyPair) {

  private def name[T: TypeTag] = typeOf[T].typeSymbol.name.toString

  private def getProductSubscription: ProductSubscription = {
    ProductSubscription.create()
      .addAll(currencyPair)
      .build()
  }

  private def sendTicks(exchange: S) = {
    exchange
      .getStreamingMarketDataService
      .getTicker(currencyPair)
      .subscribe(ticker => {
        //println(s"Ticker: $ticker")
        KafkaProxy.sendTo(TicksTopic, TicksKey, TickerCustom.formatTicker(ticker))
      })
  }

  def start = {
    val exchange = StreamingExchangeFactory
      .INSTANCE
      .createExchange(name[S])

    // Connect to the Exchange WebSocket API. Blocking wait for the connection.
    exchange
      .connect(getProductSubscription)
      .blockingAwait()

    sendTicks(exchange.asInstanceOf[S])

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

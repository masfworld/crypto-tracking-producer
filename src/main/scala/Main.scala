import org.knowm.xchange.currency.CurrencyPair
import info.bitrich.xchangestream.gdax.GDAXStreamingExchange


object Main {

  def main(args: Array[String]): Unit = {

    ExchangeProxy[GDAXStreamingExchange](CurrencyPair.BTC_EUR).start

    System.in.read()

  }
}

package com.sidesna.crypto.producer

import info.bitrich.xchangestream.gdax.GDAXStreamingExchange
import org.knowm.xchange.currency.CurrencyPair

object Main {

  def main(args: Array[String]): Unit = {

    ExchangeProxy[GDAXStreamingExchange](CurrencyPair.BTC_EUR).start

    System.in.read()

  }
}

package com.sidesna.crypto.producer.helpers

import org.knowm.xchange.currency.CurrencyPair
import org.scalatest.{FeatureSpec, GivenWhenThen, Matchers}
import java.util.GregorianCalendar

import org.knowm.xchange.dto.marketdata.Ticker.Builder

class TickerCustomTest extends FeatureSpec
  with GivenWhenThen with Matchers {

  feature("Ticker built"){
    scenario("usual ticket format"){

      val date = new GregorianCalendar(2019, 9, 1, 21, 0)

      //val ticker = new Ticker(CurrencyPair.BTC_EUR, 9200.0, 9300.0, 9250.0, 9200.0, 9500.0, 9100.0, null, 450.0, null, date, null, null)
      val ticker = new Builder()
        .currencyPair(CurrencyPair.BTC_EUR)
        .open(new java.math.BigDecimal(9291.25))
        .last(new java.math.BigDecimal(9353.22))
        .bid(new java.math.BigDecimal(9342.17))
        .ask(new java.math.BigDecimal(9353.22))
        .high(new java.math.BigDecimal(9410.0))
        .low(new java.math.BigDecimal(9217.24))
        .vwap(null)
        .volume(new java.math.BigDecimal(475.95625228))
        .quoteVolume(null)
        .timestamp(date.getGregorianChange)
        .bidSize(null)
        .askSize(null)
        .build()


      val tickerString = TickerCustom.formatTicker(ticker)

      val expected =
        """{"ask":9353.219999999999345163814723491668701171875,"askSize":null,"avg":null,"bid":9342.170000000000072759576141834259033203125,"bidSize":null,"currencyPair":"BTC/EUR","high":9410,"last":9353.219999999999345163814723491668701171875,"low":9217.239999999999781721271574497222900390625,"open":9291.25,"quoteVolume":4451723.53795034129828559194575063814533694419250074714167197953429422341287136077880859375,"timestamp":"1582-10-15T01:00:00.000Z","volume":475.95625228000000106476363725960254669189453125}"""


      tickerString should equal (expected)

    }
  }

}

package com.sidesna.crypto.producer.helpers

import java.text.SimpleDateFormat
import java.util.Date

import org.knowm.xchange.dto.marketdata.Ticker
import spray.json._

object TickerCustom {

  val DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"

  private def getDateAsString(d: Date): String = {
    val dateFormat = new SimpleDateFormat(DATE_FORMAT)
    dateFormat.format(d)
  }

  private def bigDecimalToJsValue(input: java.math.BigDecimal) = {
    Option(input).map(JsNumber(_)).getOrElse(JsNull)
  }

  def formatTicker(ticker: Ticker): String = {

    implicit val tickerJsonWriter: JsonWriter[Ticker] = new JsonWriter[Ticker] {
      override def write(ticker: Ticker): JsValue = JsObject(
        "currencyPair" -> JsString(ticker.getCurrencyPair.toString),
        "open" -> bigDecimalToJsValue(ticker.getOpen),
        "last" -> bigDecimalToJsValue(ticker.getLast),
        "bid" -> bigDecimalToJsValue(ticker.getBid),
        "ask" -> bigDecimalToJsValue(ticker.getAsk),
        "high" -> bigDecimalToJsValue(ticker.getHigh),
        "low" -> bigDecimalToJsValue(ticker.getLow),
        "avg" -> bigDecimalToJsValue(ticker.getVwap),
        "quoteVolume" -> bigDecimalToJsValue(ticker.getQuoteVolume),
        "bidSize" -> bigDecimalToJsValue(ticker.getBidSize),
        "volume" -> bigDecimalToJsValue(ticker.getVolume),
        "askSize" -> bigDecimalToJsValue(ticker.getAskSize),
        "timestamp" -> JsString(getDateAsString(ticker.getTimestamp)),
      )
    }

    ticker.toJson.compactPrint
  }

}

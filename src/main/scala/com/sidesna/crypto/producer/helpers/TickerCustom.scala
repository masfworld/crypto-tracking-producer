package com.sidesna.crypto.producer.helpers

import org.knowm.xchange.dto.marketdata.Ticker

object TickerCustom {

  def formatTicker(ticker: Ticker): String = {
    s"""{
       |"timestamp":"${ticker.getTimestamp}",
       |"currencyPair":"${ticker.getCurrencyPair}",
       |"open":"${ticker.getOpen}",
       |"last":"${ticker.getLast}",
       |"bid":"${ticker.getBid}",
       |"ask":"${ticker.getAsk}",
       |"high":"${ticker.getHigh}",
       |"low":"${ticker.getLow}",
       |"avg":"${ticker.getVwap}",
       |"volume":"${ticker.getVolume}",
       |"quoteVolume":"${ticker.getQuoteVolume}",
       |"bidSize":"${ticker.getBidSize}",
       |"askSize":"${ticker.getAskSize}"
       |}""".stripMargin.replaceAll("\n", "")}

}

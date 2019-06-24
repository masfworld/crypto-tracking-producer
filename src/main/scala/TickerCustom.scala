import org.knowm.xchange.dto.marketdata.Ticker

object TickerCustom {

  def formatTicker(ticker: Ticker): String = {
    s"""
       |currencyPair=${ticker.getCurrencyPair},
       |open=${ticker.getOpen},
       |last=${ticker.getLast},
       |bid=${ticker.getBid},
       |ask=${ticker.getAsk},
       |high=${ticker.getHigh},
       |low=${ticker.getLow},
       |avg=${ticker.getVwap},
       |volume=${ticker.getVolume},
       |quoteVolume=${ticker.getQuoteVolume},
       |timestamp=${ticker.getTimestamp},
       |bidSize=${ticker.getBidSize},
       |askSize=${ticker.getAskSize}
       |""".stripMargin}

}

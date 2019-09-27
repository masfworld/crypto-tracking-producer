package com.sidesna.crypto.producer.algorithm

import org.knowm.xchange.dto.marketdata.OrderBook
import scala.collection.JavaConverters._

// https://github.com/wavesplatform/demo-python-trading-bot/blob/master/SimpleBot.py

object ScalpingBase {

  def applyAlgorithm( orderBook: OrderBook) = {

    val price_step = 0.00000001

    val best_bid = math.BigDecimal(orderBook.getBids.asScala.minBy(_.getOriginalAmount).getOriginalAmount)
    val best_ask = math.BigDecimal(orderBook.getBids.asScala.maxBy(_.getOriginalAmount).getOriginalAmount)

    val spread_mean_price = (best_bid + best_ask) / 2

    val bid_price = spread_mean_price * (1 - price_step)
    val ask_price = spread_mean_price * (1 + price_step)

    //val bid_amount = ((btc))

  }

}

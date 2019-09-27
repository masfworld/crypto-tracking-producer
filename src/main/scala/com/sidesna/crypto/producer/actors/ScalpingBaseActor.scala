package com.sidesna.crypto.producer.actors

import akka.actor.{Actor, ActorLogging, Props}
import org.knowm.xchange.currency.CurrencyPair
import org.knowm.xchange.dto.marketdata.{OrderBook, Ticker}

object ScalpingBaseActor {
  def props(currencyPair: CurrencyPair) = Props(new ScalpingBaseActor(currencyPair))
  final case class Ticking(tick: Ticker)
  final case class OrderBooking(orderBook: OrderBook)
  case object StreamCompleted
}

class ScalpingBaseActor(currencyPair: CurrencyPair) extends Actor with ActorLogging{
  import ScalpingBaseActor._

  var tick: Option[Ticker] = None

  override def receive: Receive = {
    case Ticking(t) =>
      tick = Some(t)
      log.info(s"Tick received (from $sender) $t")
    case OrderBooking(ob) =>
      log.info(s"OrderBook received (from $sender) $ob")
      applyAlgorithm(ob)
    case StreamCompleted => "Stream finished"
    case _ => log.warning("Message not recognized")
  }

  private def applyAlgorithm(orderBook: OrderBook) = ???
}

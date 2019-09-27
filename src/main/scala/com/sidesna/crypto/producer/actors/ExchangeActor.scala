package com.sidesna.crypto.producer.actors

import akka.actor.{Actor, ActorLogging, ActorRef, Props}
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.Sink
import com.sidesna.crypto.producer.ExchangeP
import com.sidesna.crypto.producer.actors.ExchangeActor.{ConsumingOrderBooks, ConsumingTicks}
import com.sidesna.crypto.producer.actors.ScalpingBaseActor.{OrderBooking, Ticking}
import info.bitrich.xchangestream.core.StreamingExchange
import org.knowm.xchange.currency.CurrencyPair

import scala.reflect.runtime.universe._

object ExchangeActor {
  def props[S <: StreamingExchange : TypeTag](currencyPair: CurrencyPair, scalpingBaseActor: ActorRef) = Props(new ExchangeActor[S](currencyPair, scalpingBaseActor))

  case object ConsumingTicks

  case object ConsumingOrderBooks

}

class ExchangeActor[S <: StreamingExchange : TypeTag](currencyPair: CurrencyPair, scalpingBaseActor: ActorRef)
  extends ExchangeP(currencyPair)
    with Actor
    with ActorLogging {

  implicit val mat = ActorMaterializer()

  protected val actorSink = Sink
    .actorRef(scalpingBaseActor, onCompleteMessage = ScalpingBaseActor.StreamCompleted)

  override def receive: Receive = {

    case ConsumingTicks =>
      sourceTicket
        .map(x => Ticking(x))
        .runWith(actorSink)

    case ConsumingOrderBooks =>
      sourceOrderBook
        .map(x => OrderBooking(x))
        .runWith(actorSink)
  }
}

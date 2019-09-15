package com.sidesna.crypto.producer

import com.sidesna.crypto.producer.kafka._
import info.bitrich.xchangestream.core.ProductSubscription
import info.bitrich.xchangestream.gdax.GDAXStreamingExchange
import org.knowm.xchange.currency.CurrencyPair
import org.mockito.ArgumentMatchers._
import org.mockito._
import org.scalatestplus.mockito.MockitoSugar
import org.scalatest.{FeatureSpec, GivenWhenThen, Matchers, PrivateMethodTester}

import scala.concurrent.TimeoutException

class ExchangeProxyTest extends FeatureSpec
  with GivenWhenThen with MockitoSugar
  with Matchers with PrivateMethodTester {

  private val exchangeProxy = ExchangeProxy[GDAXStreamingExchange](CurrencyPair.BTC_EUR)

  feature("Product Subscription") {
    scenario("ExchangeProxy is created") {

      val decorateProductSubscriptionValue = PrivateMethod[ProductSubscription]('getProductSubscription)

      val productSubscription = exchangeProxy invokePrivate decorateProductSubscriptionValue()

      productSubscription shouldBe a [ProductSubscription]
    }
  }

  feature("Generic class name") {
    scenario("ExchangeProxy is created") {

      val decorateClassNameValue = PrivateMethod[String]('getClassName)

      val className = exchangeProxy invokePrivate decorateClassNameValue()

      className should equal ("info.bitrich.xchangestream.gdax.GDAXStreamingExchange")
    }
  }

  feature("Starting process to gather information from XChange") {

    scenario("Process with GDAX and BTC_EUR") {

      val kafkaMock = mock[KafkaProxy]

      Mockito.doNothing().when(kafkaMock).sendTo(any(), any(), anyString())

      ExchangeProxy[GDAXStreamingExchange](CurrencyPair.BTC_EUR).start

    }
  }

}

package com.sidesna.crypto.producer

import info.bitrich.xchangestream.core.ProductSubscription
import info.bitrich.xchangestream.gdax.GDAXStreamingExchange
import org.knowm.xchange.currency.CurrencyPair
import org.scalamock.scalatest.MockFactory
import org.scalatest.{FeatureSpec, GivenWhenThen, Matchers, PrivateMethodTester}

class ExchangeProxyTest extends FeatureSpec
  with GivenWhenThen with MockFactory
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


}

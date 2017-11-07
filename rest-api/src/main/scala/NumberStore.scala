package com.example

import scala.collection.concurrent.TrieMap
import scala.util.Try
import argonaut._, Argonaut._

case class NaturalNumber(number: Int) {

  require(number >= 0, s"expected non-negative integer, not '$number'")

  val fizzbuzz = 
    if (number % 3 == 0 && number % 5 == 0)
      "FizzBuzz"
    else if (number % 3 == 0)
      "Fizz"
    else if (number % 5 == 0)
      "Buzz"
    else
      number.toString
}

object NaturalNumber {
  implicit def NaturalNumberCodecJson: CodecJson[NaturalNumber] =
    casecodec1(NaturalNumber.apply, NaturalNumber.unapply)("number")
}

class NumberStore {

  private val store = TrieMap.empty[String, NaturalNumber]

  // give us something to play with
  store ++= List(
    "1" -> NaturalNumber(1),
    "2" -> NaturalNumber(2),
    "3" -> NaturalNumber(3),
    "4" -> NaturalNumber(4),
    "5" -> NaturalNumber(5)
  )

  def listNumbers() = store.values.toList
  def sumNumbers() = store.map{ case (_, natural) => natural.number }.sum
  def getNumber(num: String) = store.get(num)
}

object NumberStore {

  def parseInt(number: String) = {
    if (number.isEmpty)
      None
    else
      Try(number.toInt).toOption
  }
}

package com.chriscalderonh.andersonscodechallenge

import java.util.*
import java.util.concurrent.ThreadLocalRandom

object RandomValuesFactory {
    fun generateString(): String = UUID.randomUUID().toString()
    fun generateInt(): Int = ThreadLocalRandom.current().nextInt(0, 1000 + 1)
}
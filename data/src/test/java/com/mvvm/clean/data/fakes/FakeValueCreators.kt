package com.mvvm.clean.data.fakes

import java.util.*
import kotlin.random.Random

object FakeValueCreators {

    fun randomString(): String {
        return UUID.randomUUID().toString()
    }

    fun randomInt(): Int {
        return Random.nextInt()
    }

    fun randomDouble(): Double {
        return Random.nextDouble()
    }

    fun randomBoolean(): Boolean {
        return Random.nextBoolean()
    }

}
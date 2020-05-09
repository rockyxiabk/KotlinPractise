package com.example.myapplication.coroutine

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlin.system.measureTimeMillis

//组合挂起函数
suspend fun main(args: Array<String>) {
    val sum = measureTimeMillis {
        val one = numOne()
        val two = numTwo()
        println("$one + $two = ${one + two}")
    }
    println("times:$sum ms")
    val sum2 = measureTimeMillis {
        val one = numOneSync()
        val two = numTwoSync()
        println("${one.await()} + ${two.await()} =${one.await() + two.await()}")
    }
    println("times:$sum2 ms")
}

suspend fun numOne(): Int {
    delay(1000L)
    return 10
}

suspend fun numTwo(): Int {
    delay(1000L)
    return 20
}

suspend fun numOneSync() = GlobalScope.async {
    numOne()
}

suspend fun numTwoSync() = GlobalScope.async {
    numTwo()
}
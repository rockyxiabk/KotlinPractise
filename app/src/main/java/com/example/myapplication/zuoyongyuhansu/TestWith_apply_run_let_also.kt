package com.example.myapplication.zuoyongyuhansu

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.flow.*
import kotlin.random.Random

fun main(args: Array<String>) {
    var number = mutableListOf("one", "two", "three", "four", "five")
    number.also {
        println("this is a number $it")
    }.add("seven")
    number.let {
        it.removeAt(3)
        println("this is a number list $it")
    }
    with(number) {
        println(this)
    }
    number.run {
        println(this)
        println("the list length:$size")
    }
    number.apply {
        add("nine")
    }
    println("this is a number list  final show $number")

    val value = Random.nextInt(100)
    val takeIf = value.takeIf {
        it % 2 == 0
    }
    val takeUnless = value.takeUnless {
        it % 2 == 0
    }
    println("takeIf：$takeIf   takeUnless:$takeUnless")
    runBlocking {
        events().onEach { println("----->$it") }
            .collect {
                println("-----over")
            }
        println("Done")
    }
    runBlocking {
        events().onEach { println("------launcher：$it") }
            .launchIn(this)
        println("---launcher done")
    }
    runBlocking {
        val produce = produceNumbers()
        repeat(5) {
            launchProcessor(it, produce)
        }
        delay(950)
        produce.cancel()
    }
}

fun CoroutineScope.produceNumbers() = produce<Int> {
    var x = 1
    // 从 1 开始
    while (true) {
        send(x++)
        // 产⽣下⼀个数字
        delay(100)
        // 等待 0.1 秒
    }
}

fun CoroutineScope.launchProcessor(id: Int, channel: ReceiveChannel<Int>) = launch {
    for (msg in channel) {
        println("Processor #$id received $msg")
    }
}

private fun events(): Flow<Int> = (1..3).asFlow().onEach {
    delay(100)
}
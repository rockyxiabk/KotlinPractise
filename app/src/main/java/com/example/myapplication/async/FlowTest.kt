package com.example.myapplication.async

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

private fun foo(): List<Int> = listOf(1, 2, 3)

//序列构造器
fun foo1(): Sequence<Int> = sequence {
    for (i in 1..3) {
        Thread.sleep(100)
        yield(i)
    }
}

//挂起函数
suspend fun foo2(): List<Int> {
    delay(100)
    return listOf(1, 2, 3)
}

//流flow
fun foo3(): Flow<Int> = flow {
    for (i in 1..3) {
        delay(100)
        emit(i)
    }
}

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
suspend fun main(args: Array<String>) {
    foo().forEach { println(it) }
    println("---------------序列构造器-----------")
    foo1().forEach { println(it) }
    println("---------------挂起函数-----------")
    foo2().forEach { println(it) }
    println("---------------flow--------------")
    runBlocking {
        GlobalScope.launch {
            for (i in 1..3) {
                delay(100)
                println("I'm not blocked $i")
            }
        }
        foo3().collect { value -> println(value) }
    }
    println("-------------asFlow--------")
    (1..5).asFlow().filter {
        println("filter $it")
        it % 2 == 0
    }.map { "String $it" }.collect { println("Collect $it") }
    println("-------------zip组合------")
    val num = (1..5).asFlow()
    val str = flowOf("one", "two", "three", "four", "five")
    num.zip(str) { a, b -> "$a=$b" }.collect { println(it) }
    println("-------------combine-------")
    val nums = (1..5).asFlow().onEach { delay(300) }
    val strs= flowOf("one","two","three").onEach { delay(400) }
    nums.combine(strs) { a, b -> "$a-->$b" }.collect { println(it) }

}
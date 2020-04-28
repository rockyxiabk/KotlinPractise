package com.example.myapplication.lambda

import com.example.myapplication.sum

class IntTransformer {
    var aa = { i: Int -> i * i }
}

val sumT = { a: Int, b: Int -> a + b }

fun main(args: Array<String>) {
    println("${setOf(IntTransformer().aa)}")
    val sum1 = sumT(3, 5)
    println("$sum1")
}
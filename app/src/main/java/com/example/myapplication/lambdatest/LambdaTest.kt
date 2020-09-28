package com.example.myapplication.lambdatest

fun main() {
    val num = test(2, 3, ::add)
    println("$num")
}

fun test(a: Int, b: Int, f: (Int, Int) -> Int): Int {
    return f(a, b)
}

fun add(a: Int, b: Int): Int {
    return a + b
}
package com.example.myapplication.async

private fun foo(): List<Int> = listOf(1, 2, 3)
fun main(args: Array<String>) {
    foo().forEach { println(it) }

}
package com.example.myapplication.coroutine

import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

fun main(args: Array<String>) {
    newSingleThreadContext("ctx1").use { ctx1 ->
        newSingleThreadContext("ctx2").use { ctx2 ->
            runBlocking(ctx1) {
                println("-----ctx1 runBlocking")
                withContext(ctx2) {
                    println("-----work on ctx2")
                }
                println("-----work end")
            }
        }
    }
}
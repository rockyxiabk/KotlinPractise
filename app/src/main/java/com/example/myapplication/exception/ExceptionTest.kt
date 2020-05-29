package com.example.myapplication.exception

import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.system.measureTimeMillis

private val mutex = Mutex()
private var count = 0
fun main(args: Array<String>) {
    println("this is a test of Exception flow class")
    runBlocking {
        withContext(Dispatchers.Default) {
            massiveRun {
                mutex.withLock {
                    count++
                    println("---$count")
                }
            }
        }
        println("---$count")
    }
}

suspend fun massiveRun(action: suspend () -> Unit) {
    val n = 100
    // 启动的协程数量
    val k = 1000
    // 每个协程重复执⾏同⼀动作的次数
    val time =
        measureTimeMillis {
            coroutineScope {
                // 协程的作⽤域
                repeat(n) {
                    launch {
                        repeat(k) {
                            action()
                        }
                    }
                }
            }
        }
    println("Completed ${n * k} actions in $time ms")
}

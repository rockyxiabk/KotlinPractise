package com.example.myapplication.coroutine

import kotlinx.coroutines.*
import java.lang.Exception

//test1
//fun main(args: Array<String>) {
//    GlobalScope.launch {
//        delay(1000L)
//        println("this is a delay print!")
//    }
//    print("hello ,")
////    Thread.sleep(2000)
//    runBlocking { delay(2000L) }
//}

//test2
//suspend fun main(args: Array<String>) {
//    val  job=GlobalScope.launch {
//        delay(1000L)
//        println("world")
//    }
//    println("hello,")
//    job.join()//等待直到子协程执行结束
//}

//test3
//fun main() = runBlocking {
//    launch {
//        delay(1000L)
//        println("this is runBlocking luncher")
//    }
//    println("hello ,")
//}

//test4
//fun main()= runBlocking {
//    launch {
//        delay(1000L)
//        println("----launch")
//    }
//    coroutineScope {
//        launch {
//            delay(1000L)
//            println("----coroutineScope launch")
//        }
//        delay(1000L)
//        println("----coroutineScope")
//    }
//    println("-----runblocking")
//}
/**
test4 out:
----coroutineScope
----launch
----coroutineScope launch
-----runblocking
 */

//test5
//fun main(args: Array<String>) = runBlocking{
//    launch { downlaod() }
//    println("------main")
//}
//suspend fun downlaod(){//挂起函数
//    delay(1000L)
//    println("-----download finished")
//}
/**
 * tests5 out:
------main
-----download finished
 */
//test6
//fun main(args: Array<String>) = runBlocking{
//    //启动大量协程
//    repeat(1_000_000){
//        launch { delay(1000L)
//        println("---launcher--$it")}
//    }
//}

//test7
//suspend fun main(args: Array<String>) {
//    GlobalScope.launch {
//        repeat(1000){
//            println("i'm sleep $it times")
//            delay(500L)
//        }
//    }
//    println("-------")
//    delay(1300L)
//    //启动后的协程活动会执行下去，类似守护线程
//}

//test8
//suspend fun main(args: Array<String>) {
//    val startTime = System.currentTimeMillis()
//    val job=GlobalScope.launch(Dispatchers.Default){
//        var t=startTime
//        var i=0
//        //i<5 替换为 isAcive->可以被取消的计算循环
//        while (isActive){
//            if (System.currentTimeMillis()>=t){
//                println("i'm sleep ${i++}...")
//                t+=500
//            }
//        }
//    }
//    delay(1300L)
//    println("main I'm tired  of waiting")
//    job.cancelAndJoin()//取消协程作业 并等待它执行完成
//    println("main now T can quit")
//}

//test9
//suspend fun main(args: Array<String>) {
//    val job = GlobalScope.launch {
//        try {
//            repeat(1000) {
//                println("I'm sleep $it times")
//                delay(500L)
//            }
//        } catch (e: Exception) {
//            println("----${e.toString()}")
//        } finally {
//            println("-----finally")
//            withContext(NonCancellable){
//                println(" job: I'm runing in finally")
//                delay(1000L)
//                println("job: I'm just delay a second because  i'm non-cancelable")
//            }
//        }
//    }
//    delay(1300L)
//    println("-----now I'm tried for waiting")
//    job.cancelAndJoin()//取消并等待结束协程 会抛出CancelationException异常 在finally中打印
//    println("-----now  I can quit")
//}

//test10
suspend fun main(args: Array<String>) {
//    withTimeout(1300L){
//        repeat(1000){
//            delay(500L)
//            println("--this times:$it")
//        }
//    }
    val  res= withTimeoutOrNull(1300L){
        repeat(1000){
            delay(500L)
            println("I'm sleep $it times")
        }
        "Done"
    }
    println("----res:$res")
}
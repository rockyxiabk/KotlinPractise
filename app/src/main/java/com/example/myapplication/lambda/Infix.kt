package com.example.myapplication.lambda

import kotlin.math.abs
import kotlin.math.cos


/**
 * 中缀表示法  成员函数 或者扩展函数 只有一个参数
 */
infix fun Int.abs(x: Int): Int = -x

class Test {
    /**
     * 成员函数-中缀表示法
     */
    infix fun add(x: String) {
        println("infix add:$x")
    }

    fun build() {
        this add "jack"
        add("rocky")
    }
}

fun main(args: Array<String>) {
    val i = 1 abs 2
    println("$i")

    val test = Test()
    test.build()

    var list= listOf(1,2,3,4,5)
    val fold = list.fold(50, Int::compareTo)
    println("$fold")
    println("$list")
}

val eps = 1E-10

/**
 * 尾递归函数  tailrec
 */
tailrec fun findFixPoint(x: Double = 1.0): Double =
    if (abs(x - cos(x)) < eps) x else findFixPoint(cos(x))


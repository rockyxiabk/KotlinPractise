package com.example.myapplication

import com.example.myapplication.`class`.Factory
import com.example.myapplication.`class`.MyClass
import com.example.myapplication.data.User
import com.example.myapplication.enum.Week
import com.example.myapplication.pojo.A
import com.example.myapplication.pojo.Connection
import com.example.myapplication.pojo.Host
import com.example.myapplication.pojo.getLongestString
import java.util.*
import kotlin.math.max

fun main() {
    println("hello world")
}

fun sum(a: Int, b: Int): Int {
    return a + b;
}

fun sum1(a: Int, b: Int) = a + b

fun sum2(a: Int, b: Int): Unit {
    println("${a}+${b}=${a + b}")
}

fun str(): Unit {
    var a = 1
    val s = "a is $a"
    a = 2
    // 模板中的任意表达式：
    val s2 = "${s.replace("is", "was")}, but now is $a"
    println("$s")
    println("$s2")
}

fun maxOf(a: Int, b: Int): Int {
    if (a > b)
        return a
    else
        return b
}

fun strToInt(x: String): Int? {
    return try {
        x.toInt()
    } catch (e: Exception) {
        null
    }
}

fun listFor(): Unit {
    val list = listOf("apple", "banana", "orange", "watermollen")
    for (item in list) {
        println("$item")
    }
    for (index in list.indices) {
        println("$index->${list[index]}")
    }
    var index = 0
    while (index < list.size) {
        println("index=$index-->${list[index]}")
        index++
    }
}

fun switch(x: Any): Any {
    return when (x) {
        1 -> "one"
        "one" -> 1
        else -> "error"
    }
}

fun getStrLength(x: Any): Int? {
    return if (x is String) {
        x.length
    } else {
        null
    }
//    if (x is String) {
//        return x.length
//    }
//    return null
}

fun lambdaTest() {
    val fruits = listOf("banana", "avocado", "apple", "kiwifruit")
    fruits.filter { it.contains("a") }
        .sortedBy { it }
        .map { it.toUpperCase(Locale.ROOT) }
        .forEach { print("$it\t") }
}

fun xFun() {
    println("------------------------------------------------------")
    x@ for (i in 1..9) {
        y@ for (j in 1..9) {
            print("$i*$j=${i * j}\t")
            if (i == j) {
                println()
                break@y
            }
        }
    }
    println("-------------------------------------------------------")
}

// lambda或者内联函数 加以限制 返回
fun returnList() {
    listOf(1, 2, 3, 4, 5).forEach list@{
        if (it == 4) {
            return@list
        }
        println("------in")
    }
    println("--------out")
}

//函数返回
fun returnList1() {
    listOf(1, 2, 3, 4, 5).forEach {
        if (it == 4) {
            return
        }
        println("------in")
    }
    println("--------out")
}

/**
 * 扩展函数，可以自定义
 */
//fun MutableList<Int>.swap(index1: Int, index2: Int) {
//    val tmp = this[index1]
//    this[index1] = this[index2]
//    this[index2] = tmp
//}

/**
 * 加入泛型，适应所有类型
 */
fun <T> MULT<T>.swap(index1: Int, index2: Int) {
    val tmp = this[index1]
    this[index1] = this[index2]
    this[index2] = tmp
}

/**
 * 类型别名的使用
 */
typealias  MULT<T> = MutableList<T>

/**
 * 扩展属性
 */

val <T> List<T>.lastIndex: Int
    get() = size - 1

fun copy(to: Array<Any>, from: Array<Any>) {
    assert(to.size == from.size)
    for (i in from.indices) {
        to[i] = from[i]
    }
}

fun main(args: Array<String>) {
    main()
    println("sum=${sum(4, 9)}")
    println("sum=${sum1(9, 4)}")
    sum2(4, 9)
    str()
    val max = maxOf(19, 89)
    val autoMax = max(90, 83)
    println("max is $max")
    val value = strToInt("")
    println("value= $value")
    listFor()
    println("switch->${switch("asdf")}")
    println("str length:${getStrLength("24rf")}")
    lambdaTest()
    val fromColor = ColorInstance.fromColor(0x0000, "black")

    println()
    //[-127,127]
    for (i in -127..127) {
        print("$i->${i.toChar()}\t")
        if (i > 10 && i % 10 == 0) {
            println()
        }
    }
    println()
    //[10,1]
    for (i in 10 downTo 1) {
        println("$i")
    }
    //[10,15)
    for (i in 10 until 15) {
        println("$i")
    }
    val price = " ${'$'}9.99 "
    println(price)
    xFun()
    returnList()
    returnList1()
    val list = mutableListOf(1, 2, 3)
    list.swap(0, 1)
    println("$list")
    println("扩展属性 last index：${list.lastIndex}")
    //companion的使用
    var a = A
    a.getLongestString("haha")
    A.getLongestString("test haha")
    Connection(Host("baidu.com"), 443).connect()
    var user = User()
    user.apply {
        name = "jack"
        age = 18
        height = 189f
        width = 10
    }
    with(user) {
        eat()
        sleep()
        buy()
    }
    println("----------------------------------")
    val ints: Array<Int> = arrayOf(6, 2, 8)
    with(ints) {
        sort()
        forEach {
            print("$it")
        }
    }
    println("------------enum----------------------")
    println(enumValueOf<Week>("SATURDAY"))
    println(enumValues<Week>())
    println("--------------对象表达式----------------------")
    val myClass = MyClass.create()
    val b: Factory<MyClass> = MyClass
}

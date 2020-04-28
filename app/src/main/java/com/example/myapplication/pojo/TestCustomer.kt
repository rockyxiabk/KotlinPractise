package com.example.myapplication.pojo

import androidx.constraintlayout.solver.widgets.Rectangle
import java.io.File

fun main(args: Array<String>) {
    val customer = Customer("javk", 49)
    println(customer.toString())
    customer.age = 18
    println(customer.toString())

    val files = File("Test").listFiles()
    println(files?.size)
    println(files?.size ?: "empty")

    val fruits = listOf("banana", "avocado", "apple", "kiwifruit")
    fruits?.let { println("${it[3]}") }
    val tt = Trutle(4, "ss")
    with(tt) {
        penDown()
        penUp()
        for (i in 1..4) {
            penUp()
            turn(90.3)
            forward(58.3)
        }
        penUp()
    }

    Rectangle().apply {
        x = 4
        y = 8
        width = 10
        height = 39
    }
    val person = Person(1, "beijing", 18)
    person.eat()
    person.happyTime()
    person.move()


    val ints = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 0)
    ints.filter { it > 4 }.forEach { println(it) }
    ints.forEach lit@{
        println(it)
    }
}

// 文档注释说明使用[],而非 @params @returns
/**
 * Returns the absolute value of the given [number].
 */
fun abs(number: Int) {

}
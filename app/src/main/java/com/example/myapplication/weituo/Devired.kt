package com.example.myapplication.weituo

/**
 * 委托形式 关键字 by
 */
class Derived(b: Base) : Base by b {
    override fun printLine() {
        println("-----derived")
    }
}

/**
 * test
 */
fun main(args: Array<String>) {
    val a = BaseImpl(10)
    Derived(a).print()
    Derived(a).printLine()
}
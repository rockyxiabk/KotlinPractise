package com.example.myapplication.`class`

class Outer {
    private val bar: Int = 10

    inner class Bar {
        fun test() = bar
    }

    class Nested {
        fun foo() = 2
    }
}

fun main() {
    var test = Outer().Bar().test()
    println("inner class:$test")
    var test1 = Outer.Nested().foo()
    println("contain class:$test1")
}
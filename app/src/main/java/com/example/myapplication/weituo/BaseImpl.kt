package com.example.myapplication.weituo

class BaseImpl(val x: Int) : Base {
    override fun print() {
        print("--$x")
    }

    override fun printLine() {
        println("--$x")
    }
}
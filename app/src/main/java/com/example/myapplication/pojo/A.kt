package com.example.myapplication.pojo

class A {
    companion object
}

fun A.Companion.getLongestString(str: String) {
    println("test companion fun:$str")
}
package com.example.myapplication.range

fun main(args: Array<String>) {
    for (i in 1..4) {//[1,4]
        print("$i\t")
    }
    println()
    for (i in 1 until 4) {//[1,4)
        print("$i\t")
    }
    println()
    for (i in 4 downTo 1) {//[4,1]
        print("$i\t")
    }
    println()
    for (i in 4 downTo 1 step 2) {//[4,1]
        print("$i\t")
    }
    println()
    for (i in 1..4 step 2) {//[1,4]
        print("$i\t")
    }
}
package com.example.myapplication.conllection

val list = listOf("1", "2", "3", "4", "a", "b", "c", "aa", "bb", "cc")
val newNumber = listOf(8, 20, 32, 4, 52, 6, 15)
fun main(args: Array<String>) {
    sort()
}

fun sort() {
    println(list.sorted())//正序
    println(list.sortedDescending())//倒序
    println(list.sortedDescending().asReversed())//反转
    println(list.shuffled())//随机打乱顺序
    println("------------集合的聚合------------")
    println(newNumber.min())
    println(newNumber.max())
    println(newNumber.average())
    println(newNumber.sum())
    println(newNumber.count())
    println(newNumber.minBy { it % 2 })
    println(newNumber.sumBy { it / 2 })
    println(newNumber.sumByDouble { it.toDouble() / 2 })
    println("---------------fold reduce------")
    println(newNumber.fold(0) { sum, element -> sum + element * 1 })//开始可以给定一个初始值 0或者其他
    println(newNumber.reduce { ss, ele -> ss + ele })
    println(newNumber.foldRight(0) { element, sum -> sum + element })
}
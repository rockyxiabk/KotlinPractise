package com.example.myapplication.conllection

import android.os.Build
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.N)
fun main(args: Array<String>) {
    test1()

    test2()

    test3()

}

@RequiresApi(Build.VERSION_CODES.N)
fun test3() {
    var map = mutableMapOf("one" to 1, "two" to 2, "three" to 3)
    println(map)
    println(map.getOrDefault("two", 0))
    println(map["three"])
    println(map["five"])//null
    println(map.keys)//map of keys
    println(map.values)//map of values
    val filter = map.filter { (k, v) -> k.length > 3 && v > 2 }//根据条件过滤
    println(filter)
    val filterKeys = map.filterKeys { it.contains("t") }
    println(filterKeys)
    val filterValues = map.filterValues { it < 2 }
    println(filterValues)
}

fun test2() {
    var set = mutableSetOf("one", "two", "three", "four", "five", "six", "1", "3", "5")
    var set1 = mutableSetOf("1", "2", "3", "4", "5", "six", "seven", "eight", "nine", "zoro")
    val set2 = set union set1//合并
    println(set2)
    val intersect = set.intersect(set2)//交集
    println(intersect)
    val set3 = set2 subtract set//差集
    println(set3)
}

fun test1() {
    var list = mutableListOf(1, 3, 4, 6, 7)
//    list.fill(66)// 替换所以元素
//    println(list)
    list.add(0, 10)//add
    println(list)
    list.addAll(0, listOf(20, 30)) //addall
    println(list)
    list[0] = 100//update
    println(list)
    list.remove(66)//delete element ,if more than one ,remove first
    println(list)
    list.removeAt(2)//delete index element
    println(list)
    list.shuffle()//random  order
    println(list)
    list.reverse()//反转顺序
    println(list)

}
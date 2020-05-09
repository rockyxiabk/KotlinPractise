package com.example.myapplication.conllection

val number = listOf("one", "two", "three", "four", "five")
fun main(args: Array<String>) {
    // + -
    val listNumber = number + "six" //类似于add
    println(listNumber)
    val minusListNumber = listNumber - listOf("one", "five")
    println(minusListNumber)
    //group by 分组
    println(number.groupBy { it.first().toUpperCase() }
        .toSortedMap()) //{F=[four, five], O=[one], T=[two, three]}
    println(number.groupingBy { it.first() }.eachCount()) //{o=1, t=2, f=2}
    getElment()
}

fun getElment() {
    println("----slice 取集合中的元素")
    println(number.slice(1..3))
    println(number.slice(1..4 step 2))
    println(number.slice(setOf(3, 4, 1)))
    println("----take drop 元素")
    println(number.take(3))//起点开始
    println(number.takeLast(2))//倒序
    println(number.drop(2))//弃用前2个
    println(number.dropLast(2))//弃用后两个
    println("-------chunked 切块")
    val numbers = (0..13).toList();
    println(numbers.chunked(3))//[[0, 1, 2], [3, 4, 5], [6, 7, 8], [9, 10, 11], [12, 13]]
    println(numbers.chunked(3) { it.sum() })//[3, 12, 21, 30, 25]
    println("-------windows 窗口")
    println(number.windowed(3))//[[one, two, three], [two, three, four], [three, four, five]]
    println("-------获取元素")
    println(number.elementAt(2))
    println(number.elementAtOrNull(6))//安全变体
    println(number.sorted().first())
    println(number.sorted().last())
    println(number.random())
    println("-------检测元素是有含有")
    println(number.contains("three"))
    println(number.containsAll(listOf("two", "three")))
    println(numbers.isEmpty())
    println(numbers.isNotEmpty())
}
package com.example.myapplication.conllection

import com.example.myapplication.str

fun printAll(string: Collection<Any>) {
    for (i in string) print("$i\t")
    println()
}

fun main(args: Array<String>) {
    val list = mutableListOf(1, 2, 3, 4, 5, 6)
    list.add(0)
//    list= mutableListOf(4,5,6) //error
    list.addAll(mutableListOf(4, 5, 6))
    list[0] = 10
    list.shuffle()//打乱集合元素的位置
    printAll(list)

    val list1 = listOf(1, 2, 3, 4, 5)
    printAll(list1)
    val asSequence = list1.asSequence()//转换为迭代器方式
    println("as sequence:--${asSequence.iterator().next()}")
    val set1 = setOf(5, 6, 7, 8, 9)
    printAll(set1)

    val numbers = setOf(1, 2, 3, 4)
    val numbersBacks = setOf(4, 3, 2, 1)
    println("${numbers.first() == numbersBacks.first()}")
    println("${numbers.first() == numbersBacks.last()}")
    println("${numbers == numbersBacks}")

    val maps = mapOf(3 to 1, 6 to 2, 8 to 3, 9 to 4)
    println("all keys:${maps.keys}")//[3, 6, 8, 9]
    println("all value:${maps.values}")//[1, 2, 3, 4]

    //和元素顺序无关
    val numbersMap = mapOf("key1" to 1, "key2" to 2, "key3" to 3, "key4" to 1)
    val anotherMap = mapOf("key2" to 2, "key1" to 1, "key4" to 1, "key3" to 3)
    println("The maps are equal: ${numbersMap == anotherMap}")

    val sourceList = mutableListOf(1, 2, 3)
//    val referenceList = sourceList
    var referenceList1: List<Int> = sourceList
//    referenceList.add(4)
    sourceList.add(6)
    println("size:${sourceList.size}")//4
    println("size:${referenceList1.size}")

    val numberList = listOf("one", "two", "three", "four", "five", "six")
    val filterList = numberList.filter { it.length > 3 }
    printAll(filterList)
    //迭代器的使用
    println("迭代器的使用:")
    print("第一种：")
    val iterator = numberList.iterator()
    while (iterator.hasNext()) {
        print("${iterator.next()}\t")
    }
    print("第二种：")
    for (i in numberList) {
        print("$i\t")
    }
    print("第三种：")
    numberList.forEach {
        print("$it\t")
    }

    println()
    println("--------双路合并--------")
    val colors = listOf("red", "brown", "grey")
    val animals = listOf("fox", "bear", "wolf")
    println(colors zip animals)
    val twoAnimals = listOf("fox", "bear")
    println(colors.zip(twoAnimals))
    val numberPairs = listOf("one" to 1, "two" to 2, "three" to 3, "four" to 4)
    val unzip = numberPairs.unzip()
    println(unzip.first)
    println(unzip.second)
    println("-------------flat map")
    val containers = listOf(
        (listOf("one", "two", "three")),
        (listOf("four", "five", "six")),
        (listOf("seven", "eight"))
    )
    println("------------join to-------")
    val numbers11 = listOf("one", "two", "three", "four")
    println(numbers11)
    println(numbers11.joinToString())
    val listString = StringBuffer("The list of numbers: ")
    numbers11.joinTo(listString)
    println(listString)

    println("------------filter-------")
    val filterMap = mapOf("key1" to 1, "key2" to 2, "key3" to 3, "key11" to 11)
    //filer 获取该条件下的集合 filterNot 获取否定该条件下的集合
    val filter = filterMap.filterNot { (key, value) -> (key.endsWith("1") && value > 10) }
    println(filter)
    println("-------------filter not null---------")
    val listNull = listOf("test", null, "wfr", "24gf", null)
    listNull.filterNotNull().forEach { println("$it--${it.length}") }
    println("-------------划分 partition---------")
    val listPartion = listOf("test", "24r", "wfr", "24gf", "2gfasdv")
    val (partition,secondP) = listPartion.partition { it.length > 3 }
    println(partition)
    println(secondP)
}
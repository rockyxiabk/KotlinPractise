package com.example.myapplication.instance

fun main(args: Array<String>) {
    TestSingleton.f1()
//    Test2Singleton.getInstance(null).f1()
    Test3Singleton.INSTANCE.f1()
}
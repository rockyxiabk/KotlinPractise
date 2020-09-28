package com.example.myapplication.lazytest

class LazyTest {
    //懒加载 方式1-val-lazy
    private val UserData by lazy {
    }
    //方式二 -var-lateinit
    private lateinit var userData: UserData
}
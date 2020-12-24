package com.lup.hilt.data

import javax.inject.Inject

class Driver @Inject constructor() {
    var name: String = "null"
    var height: Int = 170
    var age: Int = 30
    fun info() {
        println("info--name:$name,height:$height,age:$age")
    }
}
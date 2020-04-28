package com.example.myapplication.pojo

data class Customer(val name: String, var age: Int) {
    override fun toString(): String {
        return "name=$name\nage=$age"
    }
}
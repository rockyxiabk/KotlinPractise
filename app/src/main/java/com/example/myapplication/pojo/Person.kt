package com.example.myapplication.pojo

class Person(sex: Int, city: String, age: Int) : Human(id = 0, name = ""), PersonActive {
    var phone: String = ""
        get() = this.toString()
        set(value) {
            /**/
            field = value
        }

    override fun move() {
    }

    override fun eat() {
    }

    override fun happyTime() {
    }

    override fun draw() {
        super.draw()
    }
}
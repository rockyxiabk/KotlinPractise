package com.example.myapplication.pojo

class Host(val host: String) {
    fun printHost() {
        print("$host")
    }
}

class Connection(val host: Host, val port: Int) {
    fun printPort() {
        print("$port")
    }

    fun Host.printMsg() {
        printHost()
        print(":")
        printPort()
    }

    fun connect() {
        host.printMsg()
    }

}
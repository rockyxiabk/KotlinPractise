package com.example.myapplication.pojo

class Host(val host: String) {
    fun printHost() {
        print("$host")
    }
}

class Connection(private val host: Host, private val port: Int) {
    private fun printPort() {
        print("$port")
    }

    private fun Host.printMsg() {
        printHost()
        print(":")
        printPort()
    }

    fun connect() {
        host.printMsg()
    }

}
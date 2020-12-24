package com.lup.hilt.data.engine

import javax.inject.Inject

class ElectricEngine @Inject constructor() : Engine {
    override fun start() {
        println("----start Electric engine")
    }

    override fun shutdown() {
        println("----shutdown Electric engine")
    }
}
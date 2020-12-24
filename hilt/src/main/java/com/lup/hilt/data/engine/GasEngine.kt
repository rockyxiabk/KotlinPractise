package com.lup.hilt.data.engine

import javax.inject.Inject

class GasEngine @Inject constructor() : Engine {
    override fun start() {
        println("----start gas engine")
    }

    override fun shutdown() {
        println("----shutdown gas engine")
    }
}
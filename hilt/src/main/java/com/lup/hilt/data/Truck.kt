package com.lup.hilt.data

import com.lup.hilt.data.engine.BindElectricEngine
import com.lup.hilt.data.engine.BindGasEngine
import com.lup.hilt.data.engine.Engine
import javax.inject.Inject

class Truck @Inject constructor(val driver: Driver) {
    @BindGasEngine
    @Inject
    lateinit var engine: Engine

    @BindElectricEngine
    @Inject
    lateinit var engine2: Engine
    fun deliver() {
        engine.start()
        println("----deliver-driver:${driver}--engine:$engine")
        engine.shutdown()

        engine2.start()
        println("----deliver-driver:${driver}--engine:$engine2")
        engine2.shutdown()
    }
}
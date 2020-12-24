package com.example.myapplication.instance

import android.content.Context

class Test2Singleton private constructor(ctx: Context) {
    companion object {
        @Volatile
        private var instance: Test2Singleton? = null
        fun getInstance(ctx: Context) = instance ?: synchronized(this) {
            instance ?: Test2Singleton(ctx).also { instance = it }
        }
    }
    fun f1(){

    }
    fun f2(){

    }
}
package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

class MainActivity : AppCompatActivity() {

    private val main = MainScope()

    private var liveData = MutableLiveData<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        main.coroutineContext.apply {

        }
        main.let {
            liveData.postValue(10)
            Transformations.map(liveData) { it * it }.observe(this, {
                Log.d("tag", "$it")
            })
            Transformations.switchMap(liveData) { MutableLiveData("${it * 5}") }.observe(this, {
                Log.d("tag", it)
            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        main.cancel()
    }
}

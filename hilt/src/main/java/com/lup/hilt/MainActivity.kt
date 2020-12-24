package com.lup.hilt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lup.hilt.data.Truck
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Retrofit
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var truck: Truck

    @Inject
    lateinit var network: Retrofit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        truck.deliver()
    }
}

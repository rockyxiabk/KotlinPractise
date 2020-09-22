package com.example.myapplication.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.rocky.core.extention.putBoolean

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        putBoolean(this,"setting","init",true)
    }

    override fun onResume() {
        super.onResume()
    }

}

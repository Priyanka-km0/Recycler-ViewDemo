package com.happiestminds.recyclerviewdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.util.*
import kotlin.concurrent.schedule

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Timer().schedule(5000){
            val intent = Intent(this@SplashActivity,MainActivity::class.java)
            startActivity(intent)
        }
    }
}
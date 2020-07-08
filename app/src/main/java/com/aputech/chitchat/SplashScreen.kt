package com.aputech.chitchat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        val In: Intent = Intent(this,MainActivity::class.java)
        Handler(Looper.myLooper()!!).postDelayed(Runnable {
            startActivity(In)
        },3000)
    }
}
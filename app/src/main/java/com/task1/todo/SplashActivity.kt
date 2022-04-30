package com.task1.todo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        splash()


    }


    fun moveToMainActivity() {

        var intent: Intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun splash() {

        Handler(Looper.getMainLooper()).postDelayed({ moveToMainActivity() }, 2000)
    }
}
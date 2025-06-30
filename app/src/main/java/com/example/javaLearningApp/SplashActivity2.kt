package com.example.javaLearningApp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.RelativeLayout


class SplashActivity2 : AppCompatActivity() {
    private lateinit var lay:RelativeLayout
    private val handler = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash2)
        supportActionBar?.hide()
        handler.postDelayed({
            val intent = Intent(this@SplashActivity2, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 1000)
    }
}
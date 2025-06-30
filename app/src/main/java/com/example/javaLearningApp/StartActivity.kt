package com.example.javaLearningApp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        supportActionBar?.hide()
        var start = findViewById<AppCompatButton>(R.id.login_btn_start)
        start.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }


    }

}
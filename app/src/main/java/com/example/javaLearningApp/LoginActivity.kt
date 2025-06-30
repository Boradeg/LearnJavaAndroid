package com.example.javaLearningApp

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()

        val btnlogin = findViewById<AppCompatButton>(R.id.login_btn)
        val sign_up_textview = findViewById<TextView>(R.id.sign_up_textview)
        btnlogin.setOnClickListener { startActivity(Intent(this,MainActivity::class.java)) }
        sign_up_textview.setOnClickListener { startActivity(Intent(this,RegisterActivity::class.java)) }

    }
}
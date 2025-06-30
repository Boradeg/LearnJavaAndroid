package com.example.javaLearningApp

import android.content.Intent
import android.os.Bundle
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton


class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        supportActionBar?.hide()

        val register = findViewById<AppCompatButton>(R.id.sign_up_si)
        register.setOnClickListener(){
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        val back = findViewById<RelativeLayout>(R.id.back_btn_user_reg)
        back.setOnClickListener(){
            val intent = Intent(applicationContext,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        val txtloginuser = findViewById<TextView>(R.id.sign_up_tv_si)
        txtloginuser.setOnClickListener(){
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}
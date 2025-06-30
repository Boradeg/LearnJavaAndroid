package com.example.javaLearningApp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.javaLearningApp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("result","main activity onCreate")


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        navView.setupWithNavController(navController)

        binding.chatFabButton.setOnClickListener{
            startActivity(Intent(this,ChabotActivity::class.java))
        }
        binding.profileIcon.setOnClickListener {
            startActivity(Intent(this,ProfileActivity::class.java))
        }

    }

    override fun onStart() {

        super.onStart()
        Log.e("result","main activity onStart")
    }
    override fun onRestart() {

        super.onRestart()
        Log.e("result","main activity onRestart")
    }
    override fun onResume() {

        super.onResume()
        Log.e("result","main activity onResume")
    }
    override fun onPause() {

        super.onPause()
        Log.e("result","main activity onPause")
    }
    override fun onStop() {

        super.onStop()
        Log.e("result","main activity onStop")
    }
    override fun onDestroy() {

        super.onDestroy()
        Log.e("result","main activity onDestroy")
    }




}

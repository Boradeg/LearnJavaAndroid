package com.example.javaLearningApp
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class SplashActivity : AppCompatActivity() {

    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()
        val bouncingBall = findViewById<TextView>(R.id.bouncingBall)
        val animation2 = AnimationUtils.loadAnimation(this, R.anim.move)

        bouncingBall.startAnimation(animation2)
        // Start a new activity after a delay
        handler.postDelayed({
            // Finish this activity so that the user cannot navigate back to it
            val animation = AnimationUtils.loadAnimation(this, R.anim.scale)
            bouncingBall.startAnimation(animation)
            handler.postDelayed({
                val intent = Intent(this@SplashActivity, SplashActivity2::class.java)
                startActivity(intent)
                finish()
            }, 1300)
        }, 3000)

    }


}

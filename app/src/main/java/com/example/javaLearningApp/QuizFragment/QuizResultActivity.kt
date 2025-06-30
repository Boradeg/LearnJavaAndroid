package com.example.javaLearningApp.QuizFragment

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.javaLearningApp.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class QuizResultActivity() : AppCompatActivity() {
    val PREF_NAME = "quiz_data"
    val KEY_DATE = "date"
    val KEY_BEGINNER_SCORE = "beginner_score"
    val KEY_INTERMEDIATE_SCORE = "intermediate_score"
    val KEY_EXPERT_SCORE = "expert_score"
    val KEY_BEGINNER_COUNT = "beginner_count"
    val KEY_INTERMEDIATE_COUNT = "intermediate_count"
    val KEY_EXPERT_COUNT = "expert_count"



    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_result)


        val totalQuestions = intent.getIntExtra("totalQuestions", 0)
        val correctAnswers = intent.getIntExtra("correctAnswers", 0)
        val que_level = intent.getStringExtra("que_level")

        // Calculate percentage
        val percentage = (correctAnswers.toFloat() / totalQuestions.toFloat()) * 100

        // Display the quiz result
        // For example, set the percentage to a TextView
        val resultTextView: TextView = findViewById(R.id.textV)
        resultTextView.text = "Your Score: ${correctAnswers} / ${totalQuestions} (${percentage}%)"
        updateQuizData(this, "$que_level", percentage.toInt())
    }
    fun updateQuizData(context: Context, level: String, score: Int) {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        // Get today's date
        val currentDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())

        // Retrieve existing data or default values

        val beginnerCount = sharedPreferences.getInt(KEY_BEGINNER_COUNT, 0)
        val intermediateCount = sharedPreferences.getInt(KEY_INTERMEDIATE_COUNT, 0)
        val expertCount = sharedPreferences.getInt(KEY_EXPERT_COUNT, 0)

        // Update scores based on the level
        when (level) {
            "easy" -> {
                val newBeginnerScore = score
                editor.putInt(KEY_BEGINNER_SCORE, newBeginnerScore)
                editor.putInt(KEY_BEGINNER_COUNT, beginnerCount + 1)
            }
            "medium" -> {
                val newIntermediateScore =score
                editor.putInt(KEY_INTERMEDIATE_SCORE, newIntermediateScore)
                editor.putInt(KEY_INTERMEDIATE_COUNT, intermediateCount + 1)
            }
            "hard" -> {
                val newExpertScore = score
                editor.putInt(KEY_EXPERT_SCORE, newExpertScore)
                editor.putInt(KEY_EXPERT_COUNT, expertCount + 1)
            }
        }

        // Update or set the date
        editor.putString(KEY_DATE, currentDate)

        // Apply changes
        editor.apply()
    }

}
package com.example.javaLearningApp.ProgramAndSyntaxFragment

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.appcompat.app.AppCompatActivity
import com.example.javaLearningApp.databinding.ActivityShowProgramSyntaxBinding

class ShowProgramSyntaxActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShowProgramSyntaxBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowProgramSyntaxBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Retrieve the extra value from the intent
        val programText ="vbhjk"
        val kotlinCodeBuilder = StringBuilder()

        kotlinCodeBuilder.append("class TestSyntaxHighlighting {\n")
        kotlinCodeBuilder.append("    fun main(args: Array<String>) {\n")
        kotlinCodeBuilder.append("        // This is a comment\n")
        kotlinCodeBuilder.append("        println(\"Hello, World!\")\n\n")
        kotlinCodeBuilder.append("        // Define a class\n")
        kotlinCodeBuilder.append("        class MyClass {\n")
        kotlinCodeBuilder.append("            // Define a method\n")
        kotlinCodeBuilder.append("            fun myMethod() {\n")
        kotlinCodeBuilder.append("                // If statement\n")
        kotlinCodeBuilder.append("                if (true) {\n")
        kotlinCodeBuilder.append("                    // Print statement\n")
        kotlinCodeBuilder.append("                    println(\"Inside if block\")\n")
        kotlinCodeBuilder.append("                } else {\n")
        kotlinCodeBuilder.append("                    // Print statement\n")
        kotlinCodeBuilder.append("                    println(\"Inside else block\")\n")
        kotlinCodeBuilder.append("                }\n")
        kotlinCodeBuilder.append("            }\n")
        kotlinCodeBuilder.append("        }\n\n")
        kotlinCodeBuilder.append("        // Create an instance of MyClass\n")
        kotlinCodeBuilder.append("        val myClass = MyClass()\n")
        kotlinCodeBuilder.append("        // Call the method\n")
        kotlinCodeBuilder.append("        myClass.myMethod()\n")
        kotlinCodeBuilder.append("    }\n")
        kotlinCodeBuilder.append("}\n")

        val kotlinCodeString = kotlinCodeBuilder.toString()

        // Example usage: Set the programText to a TextView with syntax highlighting and increased line height
        val highlightedText = highlightSyntax(kotlinCodeString ?: "")
        binding.program.apply {
            text = highlightedText
            setLineSpacing(10f, 1f) // Adjust the first parameter to increase/decrease line spacing
        }
    }


    private fun highlightSyntax(programText: String): SpannableString {
        val spannableString = SpannableString(programText)

        // Define colors for syntax highlighting
        val commentColor = Color.parseColor("#6A9955")
        val stringColor = Color.parseColor("#CE9178")
        val keywordColor = Color.parseColor("#569CD6")
        val functionColor = Color.parseColor("#DCDCAA")
        val userDefinedStringColor = Color.parseColor("#4EC9B0")

        // Define lists of keywords, variables, and string literals
        val keywords = listOf("public", "class", "void", "static", "if", "else", "for", "while", "new", "return", "break", "continue", "switch", "case")
        val variables = listOf("args", "HelloWorld")
        val functions = listOf("main", "print", "println", "printf")
        val strings = mutableListOf<String>()

        // Find positions of keywords, variables, functions, and strings in the program text
        keywords.forEach { keyword ->
            var startIndex = programText.indexOf(keyword)
            while (startIndex != -1) {
                spannableString.setSpan(
                    ForegroundColorSpan(keywordColor),
                    startIndex,
                    startIndex + keyword.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                startIndex = programText.indexOf(keyword, startIndex + keyword.length)
            }
        }

        variables.forEach { variable ->
            var startIndex = programText.indexOf(variable)
            while (startIndex != -1) {
                spannableString.setSpan(
                    ForegroundColorSpan(functionColor),
                    startIndex,
                    startIndex + variable.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                startIndex = programText.indexOf(variable, startIndex + variable.length)
            }
        }

        functions.forEach { function ->
            var startIndex = programText.indexOf(function)
            while (startIndex != -1) {
                spannableString.setSpan(
                    ForegroundColorSpan(functionColor),
                    startIndex,
                    startIndex + function.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                startIndex = programText.indexOf(function, startIndex + function.length)
            }
        }

        // Add more syntax highlighting for comments, strings, and user-defined strings here...

        return spannableString
    }
}

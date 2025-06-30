package com.example.javaLearningApp.QuizFragment

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.javaLearningApp.R
import com.example.javaLearningApp.databinding.ActivityQuizStartBinding

class QuizStartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQuizStartBinding
    private lateinit var questionList: List<QuizQuestion>
    private var currentQuestionIndex: Int = 0
    private val quizDBHelper = QuizDBHelper(this)
    private var userQueNeed: Int = 39  //not initialize with 39
    private var userDiffNeed: String = "easy"
    private var correctAnswers: Int = 0

    // Retrieve extra data from the Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizStartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        userQueNeed = intent.getIntExtra("quiz_number", 3)
        userDiffNeed = intent.getStringExtra("difficulty_level").toString()

        // Retrieve extra data from the Intent

        // Add questions to the database if not already added
        addQuestionsToDatabaseIfNotAdded()

        // Call displayQuestion function with the desired parameters
        if (userDiffNeed != null) {
            displayQuestion(userQueNeed, userDiffNeed)
        } // Change the number of questions and difficulty level here

        // Handle option clicks
        binding.option1.setOnClickListener { handleOptionClick(1) }
        binding.option2.setOnClickListener { handleOptionClick(2) }
        binding.option3.setOnClickListener { handleOptionClick(3) }
        binding.option4.setOnClickListener { handleOptionClick(4) }
        binding.queLevel.text = userDiffNeed

        // Display the first question
        //displayQuestion(currentQuestionIndex)
    }

    private fun addQuestionsToDatabaseIfNotAdded() {
        if (!areQuestionsAdded()) {
            val questionList = mutableListOf<QuizQuestion>()


            // Add 30 easy questions

            questionList.add(
                QuizQuestion(
                    1,
                    "Which of the following option leads to the portability and security of Java?",
                    "Bytecode is executed by JVM",
                    "The applet makes the Java code secure and portable",
                    "Use of exception handling",
                    "Dynamic binding between objects",
                    1,
                    "easy"
                )
            )
            questionList.add(
                QuizQuestion(
                    1,
                    "Which of the following is not a Java features?",
                    " Dynamic",
                    "Architecture Neutral",
                    "Use of pointers",
                    "Object-oriented",
                    3,
                    "easy"
                )
            )
            questionList.add(
                QuizQuestion(
                    1,
                    "Who invented Java Programming?",
                    "Guido van Rossum",
                    "James Gosling",
                    "Dennis Ritchie",
                    "Bjarne Stroustrup",
                    2,
                    "easy"
                )
            )
            questionList.add(
                QuizQuestion(
                    1,
                    "Which statement is true about Java?",
                    "Java is a sequence-dependent programming language",
                    "Java is a code dependent programming language",
                    "Java is a platform-dependent programming language",
                    "Java is a platform-independent programming language",
                    4,
                    "easy"
                )
            )
            questionList.add(
                QuizQuestion(
                    1,
                    "Which component is used to compile, debug and execute the java programs?",
                    "JRE",
                    "JIT",
                    "JDK",
                    "JVM",
                    3,
                    "easy"
                )
            )
            questionList.add(
                QuizQuestion(
                    1, "Which one of the following is not Java feature?",
                    "Object-oriented",
                    "Use of pointers",
                    "Portable",
                    "Dynamic and Extensible", 2, "easy"
                )
            )

            questionList.add(
                QuizQuestion(
                    1,
                    "Which of these cannot be used for a variable name in Java?",
                    "identifier & keyword",
                    "identifier",
                    "keyword",
                    "none of the mentio",
                    3,
                    "easy"
                )
            )

            questionList.add(
                QuizQuestion(
                    1, "What is the extension of java code files?",
                    ".js",
                    ".txt",
                    ".class",
                    ".java", 4, "easy"
                )
            )

            questionList.add(
                QuizQuestion(
                    1, "Which environment variable is used to set the java path?",
                    "MAVEN_Path",
                    "JavaPATH",
                    "JAVA",
                    "JAVA_HOME", 4, "easy"
                )
            )
            questionList.add(
                QuizQuestion(
                    1, "Which of the following is not an OOPS concept in Java?",
                    "Polymorphism",
                    "Inheritance",
                    "Compilation",
                    "Encapsulation", 3, "easy"
                )
            )

            questionList.add(
                QuizQuestion(
                    1, "What is not the use of “this” keyword in Java?",
                    "Referring to the instance variable when a local variable has the same name",
                    "Passing itself to the method of the same class",
                    "Passing itself to another method",
                    "Calling another constructor in constructor chaining", 2, "easy"
                )
            )

            questionList.add(
                QuizQuestion(
                    1,
                    "Which of the following is a type of polymorphism in Java Programming?",
                    "Multiple polymorphism",
                    "Compile time polymorphism",
                    "Multilevel polymorphism",
                    "Execution time polymorphism",
                    2,
                    "easy"
                )
            )
            questionList.add(
                QuizQuestion(
                    1,
                    "What is Truncation in Java?",
                    "Floating-point value assigned to a Floating type",
                    "Floating-point value assigned to an integer type",
                    "Integer value assigned to floating type",
                    "Integer value assigned to floating type",
                    2,
                    "easy"
                )
            )
            questionList.add(
                QuizQuestion(
                    1,
                    "What is the extension of compiled java classes?",
                    ".txt",
                    ".js",
                    ".class",
                    ".java",
                    3,
                    "easy"
                )
            )
            questionList.add(
                QuizQuestion(
                    1,
                    "Which exception is thrown when java is out of memory?",
                    "MemoryError",
                    "OutOfMemoryError",
                    "MemoryOutOfBoundsException", "MemoryFullException",
                    2,
                    "easy"
                )
            )
            questionList.add(
                QuizQuestion(
                    1,
                    "Which of these are selection statements in Java?",
                    "break",
                    "continue",
                    "for()",
                    "if()",
                    4,
                    "easy"
                )
            )
            questionList.add(
                QuizQuestion(
                    1,
                    "Which of these keywords is used to define interfaces in Java?",
                    "intf",
                    "Intf",
                    "interface",
                    "Interface",
                    3,
                    "easy"
                )
            )
            questionList.add(
                QuizQuestion(
                    1,
                    "Which of the following is a superclass of every class in Java?",
                    "ArrayList",
                    "Abstract class",
                    "Object class",
                    "String",
                    3,
                    "easy"
                )
            )
            questionList.add(
                QuizQuestion(
                    1,
                    "Which of the below is not a Java Profiler?",
                    "JProfiler", "Eclipse Profiler", "JVM", "JConsole",
                    3,
                    "easy"
                )
            )
            questionList.add(
                QuizQuestion(
                    1,
                    "Which of these packages contains the exception Stack Overflow in Java?",
                    "java.io",
                    "java.system",
                    "java.lang",
                    "java.util",
                    3,
                    "easy"
                )
            )
            questionList.add(
                QuizQuestion(
                    1,
                    "Which of these statements is incorrect about Thread?",
                    "start() method is used to begin execution of the thread",
                    "run() method is used to begin execution of a thread before start() method in special cases",
                    "A thread can be formed by implementing Runnable interface only",
                    "A thread can be formed by a class that extends Thread class",
                    2,
                    "easy"
                )
            )
            questionList.add(
                QuizQuestion(
                    1,
                    "Which of these statements is incorrect about Thread?",
                    "start() method is used to begin execution of the thread",
                    "run() method is used to begin execution of a thread before start() method in special cases",
                    "A thread can be formed by implementing Runnable interface only",
                    "A thread can be formed by a class that extends Thread class",
                    2,
                    "easy"
                )
            )
            questionList.add(
                QuizQuestion(
                    1,
                    "What is the numerical range of a char data type in Java?",
                    "0 to 256",
                    "-128 to 127",
                    "0 to 65535",
                    "0 to 32767",
                    3,
                    "easy"
                )
            )
            questionList.add(
                QuizQuestion(
                    1,
                    " Which class provides system independent server side implementation?",
                    "Server",
                    "ServerReader",
                    "Socket",
                    "ServerSocket",
                    4,
                    "easy"
                )
            )
            questionList.add(
                QuizQuestion(
                    1,
                    "Which of the following is true about servlets?",
                    "Servlets can use the full functionality of the Java class libraries",
                    "Servlets execute within the address space of web server, platform independent and uses the functionality of java class librarie",
                    "Servlets execute within the address space of web server",
                    "Servlets are platform-independent because they are written in java",
                    2,
                    "easy"
                )
            )
            questionList.add(
                QuizQuestion(
                    1,
                    "What is the output of the following Java code?\n\npublic class Test {\n    public static void main(String[] args) {\n        int x = 5;\n        System.out.println(x++);\n    }\n}",
                    "5",
                    "6",
                    "4",
                    "Compiler error",
                    1,
                    "medium"
                )
            )

            questionList.add(
                QuizQuestion(
                    1,
                    "Which of the following is not a valid keyword in Java?",
                    "static",
                    "void",
                    "unsigned",
                    "volatile",
                    3,
                    "medium"
                )
            )

            questionList.add(
                QuizQuestion(
                    1,
                    "What will be the output of the following Java code?\n\npublic class Test {\n    public static void main(String[] args) {\n        int a = 5;\n        int b = 2;\n        System.out.println(a / b);\n    }\n}",
                    "2.5",
                    "2",
                    "2.0",
                    "Compilation error",
                    2,
                    "medium"
                )
            )

            questionList.add(
                QuizQuestion(
                    1,
                    "Which of the following is a marker interface in Java?",
                    "Runnable",
                    "Serializable",
                    "Comparable",
                    "Iterable",
                    2,
                    "medium"
                )
            )

            questionList.add(
                QuizQuestion(
                    1,
                    "What is the correct way to declare a constant in Java?",
                    "final int VALUE = 10;",
                    "constant int VALUE = 10;",
                    "int VALUE = 10; final;",
                    "const int VALUE = 10;",
                    1,
                    "medium"
                )
            )

            questionList.add(
                QuizQuestion(
                    1,
                    "What is the output of the following Java code?\n\npublic class Test {\n    public static void main(String[] args) {\n        String str = null;\n        System.out.println(str.length());\n    }\n}",
                    "null",
                    "0",
                    "Compilation error",
                    "NullPointerException",
                    4,
                    "medium"
                )
            )

            questionList.add(
                QuizQuestion(
                    1,
                    "What is the superclass of all exception types in Java?",
                    "Throwable",
                    "RuntimeException",
                    "Exception",
                    "Error",
                    1,
                    "medium"
                )
            )

            questionList.add(
                QuizQuestion(
                    1,
                    "What will be the output of the following Java code?\n\npublic class Test {\n    public static void main(String[] args) {\n        String str1 = \"Hello\";\n        String str2 = new String(\"Hello\");\n        System.out.println(str1 == str2);\n    }\n}",
                    "true",
                    "false",
                    "Compilation error",
                    "NullPointerException",
                    2,
                    "medium"
                )
            )

            questionList.add(
                QuizQuestion(
                    1,
                    "Which of the following statements is true regarding constructors in Java?",
                    "Constructors can be final.",
                    "Constructors can be abstract.",
                    "Constructors can be synchronized.",
                    "Constructors can be static.",
                    4,
                    "medium"
                )
            )

            questionList.add(
                QuizQuestion(
                    1,
                    "What is the purpose of the 'finally' block in Java?",
                    "To handle exceptions.",
                    "To execute a block of code regardless of whether an exception is thrown or not.",
                    "To execute a block of code only if an exception is thrown.",
                    "To execute a block of code only if no exception is thrown.",
                    2,
                    "medium"
                )
            )
// Question 11
            questionList.add(
                QuizQuestion(
                    1,
                    "What is the output of the following Java code?\n\npublic class Test {\n    public static void main(String[] args) {\n        System.out.println(Math.round(2.5));\n    }\n}",
                    "2",
                    "3",
                    "2.5",
                    "3.0",
                    2,
                    "medium"
                )
            )

// Question 12
            questionList.add(
                QuizQuestion(
                    1,
                    "Which of the following access modifiers restricts the accessibility of a method to only within its own class?",
                    "public",
                    "protected",
                    "private",
                    "default",
                    3,
                    "medium"
                )
            )

// Question 13
            questionList.add(
                QuizQuestion(
                    1,
                    "What is the correct syntax for declaring a multi-dimensional array in Java?",
                    "int[][] arr;",
                    "int arr[];",
                    "int arr[][];",
                    "int[] arr[];",
                    1,
                    "medium"
                )
            )

// Question 14
            questionList.add(
                QuizQuestion(
                    1,
                    "Which of the following is true about the 'this' keyword in Java?",
                    "It refers to the current class object.",
                    "It refers to the current instance variable.",
                    "It refers to the current method.",
                    "It refers to the parent class object.",
                    1,
                    "medium"
                )
            )

// Question 15
            questionList.add(
                QuizQuestion(
                    1,
                    "What is the output of the following Java code?\n\npublic class Test {\n    public static void main(String[] args) {\n        System.out.println(\"Hello\".substring(0, 2));\n    }\n}",
                    "Hel",
                    "Hell",
                    "He",
                    "Hello",
                    3,
                    "medium"
                )
            )

// Question 16
            questionList.add(
                QuizQuestion(
                    1,
                    "Which of the following is not a valid identifier in Java?",
                    "myVar123",
                    "var_1",
                    "123var",
                    "_myVar",
                    3,
                    "medium"
                )
            )

// Question 17
            questionList.add(
                QuizQuestion(
                    1,
                    "What is the output of the following Java code?\n\npublic class Test {\n    public static void main(String[] args) {\n        int num = 10;\n        System.out.println(num > 20 ? \"Yes\" : \"No\");\n    }\n}",
                    "Yes",
                    "No",
                    "Compilation error",
                    "Runtime error",
                    2,
                    "medium"
                )
            )

// Question 18
            questionList.add(
                QuizQuestion(
                    1,
                    "Which of the following is a wrapper class for primitive type 'int' in Java?",
                    "Integer",
                    "Float",
                    "Double",
                    "Char",
                    1,
                    "medium"
                )
            )

// Question 19
            questionList.add(
                QuizQuestion(
                    1,
                    "What is the output of the following Java code?\n\npublic class Test {\n    public static void main(String[] args) {\n        System.out.println(\"Java\".equals(\"java\"));\n    }\n}",
                    "true",
                    "false",
                    "Compilation error",
                    "NullPointerException",
                    2,
                    "medium"
                )
            )

// Question 20
            questionList.add(
                QuizQuestion(
                    1,
                    "Which of the following statements is true about method overloading in Java?",
                    "Method overloading allows different methods to have the same name.",
                    "Method overloading allows methods to have different return types.",
                    "Method overloading is achieved by changing the method name only.",
                    "Method overloading is not allowed in Java.",
                    1,
                    "medium"
                )
            )
// Question 21
            questionList.add(
                QuizQuestion(
                    1,
                    "What is the result of the following expression in Java?\n\n5 + 3 * 2",
                    "16",
                    "11",
                    "10",
                    "13",
                    11,
                    "medium"
                )
            )

// Question 22
            questionList.add(
                QuizQuestion(
                    1,
                    "Which of the following is the correct way to declare a constant in Java?",
                    "const int MAX_VALUE = 100;",
                    "final int MAX_VALUE = 100;",
                    "int final MAX_VALUE = 100;",
                    "final MAX_VALUE = 100;",
                    2,
                    "medium"
                )
            )

// Question 23
            questionList.add(
                QuizQuestion(
                    1,
                    "What is the result of the following expression in Java?\n\n(5 > 3) && (8 < 5)",
                    "true",
                    "false",
                    "true false",
                    "Compilation error",
                    2,
                    "medium"
                )
            )

// Question 24
            questionList.add(
                QuizQuestion(
                    1,
                    "Which of the following data types in Java is used to store a single 16-bit Unicode character?",
                    "char",
                    "int",
                    "byte",
                    "short",
                    1,
                    "medium"
                )
            )

// Question 25
            questionList.add(
                QuizQuestion(
                    1,
                    "What will be the output of the following Java code?\n\npublic class Test {\n    public static void main(String[] args) {\n        System.out.println(\"10\" + 20 + 30);\n    }\n}",
                    "60",
                    "102030",
                    "10",
                    "Compilation error",
                    3,
                    "medium"
                )
            )

// Question 26
            questionList.add(
                QuizQuestion(
                    1,
                    "Which keyword is used to define a method that does not return any value in Java?",
                    "void",
                    "null",
                    "none",
                    "return",
                    1,
                    "medium"
                )
            )

// Question 27
            questionList.add(
                QuizQuestion(
                    1,
                    "What is the result of the following expression in Java?\n\n10 % 3",
                    "3",
                    "1",
                    "0",
                    "10",
                    2,
                    "medium"
                )
            )

// Question 28
            questionList.add(
                QuizQuestion(
                    1,
                    "Which of the following is the correct syntax to create an object of a class in Java?",
                    "object MyClass = new MyClass();",
                    "MyClass obj = new MyClass;",
                    "new MyClass() = obj;",
                    "MyClass obj = MyClass();",
                    1,
                    "medium"
                )
            )

// Question 29
            questionList.add(
                QuizQuestion(
                    1,
                    "What is the output of the following Java code?\n\npublic class Test {\n    public static void main(String[] args) {\n        int i = 10;\n        System.out.println(i++ + ++i);\n    }\n}",
                    "21",
                    "20",
                    "22",
                    "Compilation error",
                    3,
                    "medium"
                )
            )

// Question 30
            questionList.add(
                QuizQuestion(
                    1,
                    "What is the correct syntax to declare an array of integers named 'arr' with 5 elements in Java?",
                    "int[] arr = new int[5];",
                    "int arr[5];",
                    "int arr[5] = new int[];",
                    "int arr[] = new int[5];",
                    1,
                    "medium"
                )
            )
            // Question 31
            questionList.add(
                QuizQuestion(
                    1,
                    "What is the output of the following Java code?\n\npublic class Test {\n    public static void main(String[] args) {\n        String str1 = \"hello\";\n        String str2 = \"hello\";\n        System.out.println(str1 == str2);\n    }\n}",
                    "true",
                    "false",
                    "Compilation error",
                    "It depends",
                    1,
                    "hard"
                )
            )

// Question 32
            questionList.add(
                QuizQuestion(
                    1,
                    "Which of the following modifiers cannot be used with a constructor in Java?",
                    "private",
                    "public",
                    "static",
                    "abstract",
                    4,
                    "hard"
                )
            )

// Question 33
            questionList.add(
                QuizQuestion(
                    1,
                    "What will be the output of the following Java code?\n\npublic class Test {\n    public static void main(String[] args) {\n        int x = 5;\n        System.out.println(x > 2 ? x < 4 ? 10 : 8 : 7);\n    }\n}",
                    "8",
                    "10",
                    "7",
                    "Compilation error",
                    3,
                    "hard"
                )
            )

// Question 34
            questionList.add(
                QuizQuestion(
                    1,
                    "What is the output of the following Java code?\n\npublic class Test {\n    public static void main(String[] args) {\n        String str = null;\n        System.out.println(str instanceof String);\n    }\n}",
                    "true",
                    "false",
                    "Compilation error",
                    "NullPointerException",
                    2,
                    "hard"
                )
            )

// Question 35
            questionList.add(
                QuizQuestion(
                    1,
                    "What is the output of the following Java code?\n\npublic class Test {\n    public static void main(String[] args) {\n        System.out.println(2 + \"2\" + 2);\n    }\n}",
                    "222",
                    "6",
                    "22",
                    "Compilation error",
                    1,
                    "hard"
                )
            )

// Question 36
            questionList.add(
                QuizQuestion(
                    1,
                    "What is the output of the following Java code?\n\npublic class Test {\n    public static void main(String[] args) {\n        int x = 10;\n        int y = x++;\n        System.out.println(x + \" \" + y);\n    }\n}",
                    "10 10",
                    "11 10",
                    "10 11",
                    "11 11",
                    2,
                    "hard"
                )
            )

// Question 37
            questionList.add(
                QuizQuestion(
                    1,
                    "What is the output of the following Java code?\n\npublic class Test {\n    public static void main(String[] args) {\n        System.out.println(Math.round(2.5));\n    }\n}",
                    "2",
                    "3",
                    "2.5",
                    "Compilation error",
                    2,
                    "hard"
                )
            )

// Question 38
            questionList.add(
                QuizQuestion(
                    1,
                    "Which of the following statements is true about Java interface?",
                    "A class can implement multiple interfaces",
                    "An interface can extend multiple interfaces",
                    "An interface can contain constructors",
                    "An interface can contain instance variables",
                    1,
                    "hard"
                )
            )

// Question 39
            questionList.add(
                QuizQuestion(
                    1,
                    "What is the output of the following Java code?\n\npublic class Test {\n    public static void main(String[] args) {\n        String str = \"Hello\";\n        str.concat(\" World\");\n        System.out.println(str);\n    }\n}",
                    "Hello",
                    "Hello World",
                    "World",
                    "Compilation error",
                    1,
                    "hard"
                )
            )

// Question 40
            questionList.add(
                QuizQuestion(
                    1,
                    "What is the output of the following Java code?\n\npublic class Test {\n    public static void main(String[] args) {\n        String str = \"Hello\";\n        str += \" World\";\n        System.out.println(str);\n    }\n}",
                    "Hello",
                    "Hello World",
                    "World",
                    "Compilation error",
                    2,
                    "hard"
                )
            )
// Question 41
            questionList.add(
                QuizQuestion(
                    1,
                    "What is the output of the following Java code?\n\npublic class Test {\n    public static void main(String[] args) {\n        int x = 10;\n        if(x > 5) {\n            int y = 5;\n        }\n        System.out.println(y);\n    }\n}",
                    "5",
                    "10",
                    "Compilation error",
                    "0",
                    3,
                    "hard"
                )
            )
// Question 1
            questionList.add(
                QuizQuestion(
                    1,
                    "What is the output of the following Java code?\n\npublic class Test {\n    public static void main(String[] args) {\n        int x = 5;\n        System.out.println(++x);\n    }\n}",
                    "5",
                    "6",
                    "Compilation error",
                    "Runtime error",
                    2,
                    "hard"
                )
            )

// Question 2
            questionList.add(
                QuizQuestion(
                    1,
                    "What is the output of the following Java code?\n\npublic class Test {\n    public static void main(String[] args) {\n        int x = 10;\n        int y = x++;\n        System.out.println(x + \" \" + y);\n    }\n}",
                    "10 10",
                    "11 10",
                    "10 11",
                    "11 11",
                    2,
                    "hard"
                )
            )

// Question 3
            questionList.add(
                QuizQuestion(
                    1,
                    "What is the output of the following Java code?\n\npublic class Test {\n    public static void main(String[] args) {\n        int x = 5;\n        int y = x++ + ++x;\n        System.out.println(x + \" \" + y);\n    }\n}",
                    "6 11",
                    "11 6",
                    "12 12",
                    "Compilation error",
                    2,
                    "hard"
                )
            )

// Question 4
            questionList.add(
                QuizQuestion(
                    1,
                    "What is the output of the following Java code?\n\npublic class Test {\n    public static void main(String[] args) {\n        int x = 5;\n        int y = ++x + x++;\n        System.out.println(x + \" \" + y);\n    }\n}",
                    "6 10",
                    "10 6",
                    "11 11",
                    "Compilation error",
                    1,
                    "hard"
                )
            )

// Question 5
            questionList.add(
                QuizQuestion(
                    1,
                    "What is the output of the following Java code?\n\npublic class Test {\n    public static void main(String[] args) {\n        int x = 10;\n        System.out.println(x > 10 ? x < 20 ? 10 : 20 : 30);\n    }\n}",
                    "10",
                    "20",
                    "30",
                    "Compilation error",
                    3,
                    "hard"
                )
            )

// Question 6
            questionList.add(
                QuizQuestion(
                    1,
                    "What is the output of the following Java code?\n\npublic class Test {\n    public static void main(String[] args) {\n        System.out.println(10 / 0);\n    }\n}",
                    "0",
                    "10",
                    "Infinity",
                    "Compilation error",
                    4,
                    "hard"
                )
            )

// Question 7
            questionList.add(
                QuizQuestion(
                    1,
                    "What is the output of the following Java code?\n\npublic class Test {\n    public static void main(String[] args) {\n        System.out.println(0.0 / 0.0);\n    }\n}",
                    "0.0",
                    "NaN",
                    "Infinity",
                    "Compilation error",
                    2,
                    "hard"
                )
            )

// Question 8
            questionList.add(
                QuizQuestion(
                    1,
                    "What is the output of the following Java code?\n\npublic class Test {\n    public static void main(String[] args) {\n        System.out.println(Math.sqrt(-1));\n    }\n}",
                    "0.0",
                    "NaN",
                    "Infinity",
                    "Compilation error",
                    2,
                    "hard"
                )
            )

// Question 9
            questionList.add(
                QuizQuestion(
                    1,
                    "What is the output of the following Java code?\n\npublic class Test {\n    public static void main(String[] args) {\n        System.out.println(Math.log(-1));\n    }\n}",
                    "0.0",
                    "NaN",
                    "Infinity",
                    "Compilation error",
                    2,
                    "hard"
                )
            )

// Question 10
            questionList.add(
                QuizQuestion(
                    1,
                    "What is the output of the following Java code?\n\npublic class Test {\n    public static void main(String[] args) {\n        System.out.println(10.0 / 0);\n    }\n}",
                    "0.0",
                    "Infinity",
                    "NaN",
                    "Compilation error",
                    2,
                    "hard"
                )
            )

// Add more hard questions here...

            questionList.add(
                QuizQuestion(
                    1,
                    "What is the output of the following Java code?\n\npublic class Test {\n    public static void main(String[] args) {\n        int x = 5;\n        System.out.println(++x);\n    }\n}",
                    "5",
                    "6",
                    "Compilation error",
                    "Runtime error",
                    2,
                    "hard"
                )
            )

            questionList.add(
                QuizQuestion(
                    1,
                    "What is the output of the following Java code?\n\npublic class Test {\n    public static void main(String[] args) {\n        int x = 5;\n        System.out.println(--x);\n    }\n}",
                    "5",
                    "4",
                    "Compilation error",
                    "Runtime error",
                    4,
                    "hard"
                )
            )

            questionList.add(
                QuizQuestion(
                    1,
                    "What is the output of the following Java code?\n\npublic class Test {\n    public static void main(String[] args) {\n        int x = 5;\n        System.out.println(x--);\n    }\n}",
                    "5",
                    "4",
                    "Compilation error",
                    "Runtime error",
                    1,
                    "hard"
                )
            )

            questionList.add(
                QuizQuestion(
                    1,
                    "What is the output of the following Java code?\n\npublic class Test {\n    public static void main(String[] args) {\n        int x = 5;\n        System.out.println(x++);\n    }\n}",
                    "5",
                    "6",
                    "Compilation error",
                    "Runtime error",
                    1,
                    "hard"
                )
            )

            questionList.add(
                QuizQuestion(
                    1,
                    "What is the output of the following Java code?\n\npublic class Test {\n    public static void main(String[] args) {\n        int x = 5;\n        System.out.println(x + ++x);\n    }\n}",
                    "11",
                    "10",
                    "Compilation error",
                    "Runtime error",
                    1,
                    "hard"
                )
            )

            questionList.add(
                QuizQuestion(
                    1,
                    "What is the output of the following Java code?\n\npublic class Test {\n    public static void main(String[] args) {\n        int x = 5;\n        System.out.println(x + x++);\n    }\n}",
                    "10",
                    "11",
                    "Compilation error",
                    "Runtime error",
                    2,
                    "hard"
                )
            )

            questionList.add(
                QuizQuestion(
                    1,
                    "What is the output of the following Java code?\n\npublic class Test {\n    public static void main(String[] args) {\n        int x = 5;\n        x = x++ + ++x;\n        System.out.println(x);\n    }\n}",
                    "11",
                    "10",
                    "Compilation error",
                    "Runtime error",
                    1,
                    "hard"
                )
            )

            questionList.add(
                QuizQuestion(
                    1,
                    "What is the output of the following Java code?\n\npublic class Test {\n    public static void main(String[] args) {\n        int x = 5;\n        x = x++ - --x;\n        System.out.println(x);\n    }\n}",
                    "1",
                    "2",
                    "Compilation error",
                    "Runtime error",
                    1,
                    "hard"
                )
            )

            questionList.add(
                QuizQuestion(
                    1,
                    "What is the output of the following Java code?\n\npublic class Test {\n    public static void main(String[] args) {\n        int x = 5;\n        x = x-- - ++x;\n        System.out.println(x);\n    }\n}",
                    "1",
                    "2",
                    "Compilation error",
                    "Runtime error",
                    1,
                    "hard"
                )
            )

            questionList.add(
                QuizQuestion(
                    1,
                    "What is the output of the following Java code?\n\npublic class Test {\n    public static void main(String[] args) {\n        int x = 5;\n        x = x-- + --x;\n        System.out.println(x);\n    }\n}",
                    "7",
                    "6",
                    "Compilation error",
                    "Runtime error",
                    2,
                    "hard"
                )
            )

// Question 42
            questionList.add(
                QuizQuestion(
                    1,
                    "Which of the following is not a valid keyword in Java?",
                    "dynamic",
                    "static",
                    "volatile",
                    "transient",
                    1,
                    "hard"
                )
            )

// Question 43
            questionList.add(
                QuizQuestion(
                    1,
                    "What is the output of the following Java code?\n\npublic class Test {\n    public static void main(String[] args) {\n        int x = 5;\n        System.out.println(x > 2 ? x < 4 ? 10 : 8 : 7);\n    }\n}",
                    "8",
                    "10",
                    "7",
                    "Compilation error",
                    1,
                    "hard"
                )
            )

// Question 44
            questionList.add(
                QuizQuestion(
                    1,
                    "What is the output of the following Java code?\n\npublic class Test {\n    public static void main(String[] args) {\n        int x = 5;\n        int y = 2;\n        System.out.println(x / y);\n    }\n}",
                    "2",
                    "2.5",
                    "2.0",
                    "Compilation error",
                    1,
                    "hard"
                )
            )

// Question 45
            questionList.add(
                QuizQuestion(
                    1,
                    "What is the output of the following Java code?\n\npublic class Test {\n    public static void main(String[] args) {\n        int x = 5;\n        int y = 2;\n        System.out.println(x % y);\n    }\n}",
                    "1",
                    "2",
                    "2.5",
                    "0",
                    1,
                    "hard"
                )
            )

// Question 46
            questionList.add(
                QuizQuestion(
                    1,
                    "What is the output of the following Java code?\n\npublic class Test {\n    public static void main(String[] args) {\n        System.out.println(4 | 3);\n    }\n}",
                    "7",
                    "12",
                    "3",
                    "4",
                    1,
                    "hard"
                )
            )

// Question 47
            questionList.add(
                QuizQuestion(
                    1,
                    "What is the output of the following Java code?\n\npublic class Test {\n    public static void main(String[] args) {\n        System.out.println(10 >> 1);\n    }\n}",
                    "5",
                    "10",
                    "1",
                    "Compilation error",
                    1,
                    "hard"
                )
            )

// Question 48
            questionList.add(
                QuizQuestion(
                    1,
                    "What is the output of the following Java code?\n\npublic class Test {\n    public static void main(String[] args) {\n        System.out.println(Math.min(Double.MIN_VALUE, 0.0));\n    }\n}",
                    "0.0",
                    "Positive infinity",
                    "Negative infinity",
                    "Double.MIN_VALUE",
                    4,
                    "hard"
                )
            )

// Question 49
            questionList.add(
                QuizQuestion(
                    1,
                    "What is the output of the following Java code?\n\npublic class Test {\n    public static void main(String[] args) {\n        int x = 0;\n        System.out.println(x++);\n    }\n}",
                    "0",
                    "1",
                    "Compilation error",
                    "Runtime error",
                    1,
                    "hard"
                )
            )

// Question 50
            questionList.add(
                QuizQuestion(
                    1,
                    "What is the output of the following Java code?\n\npublic class Test {\n    public static void main(String[] args) {\n        int x = 10;\n        int y = ++x;\n        System.out.println(x + \" \" + y);\n    }\n}",
                    "11 10",
                    "10 11",
                    "11 11",
                    "10 10",
                    3,
                    "hard"
                )
            )
            // Easy Questions
            questionList.add(
                QuizQuestion(
                    1,
                    "Which of the following is a valid identifier in Java?",
                    "1variable",
                    "_variable",
                    "variable-1",
                    "variable#",
                    2,
                    "easy"
                )
            )

            questionList.add(
                QuizQuestion(
                    1,
                    "What is the correct way to declare a variable in Java?",
                    "variable int x;",
                    "int x;",
                    "x = int;",
                    "int = x;",
                    2,
                    "easy"
                )
            )

            questionList.add(
                QuizQuestion(
                    1,
                    "Which data type is used to store a single character in Java?",
                    "char",
                    "character",
                    "string",
                    "chr",
                    1,
                    "easy"
                )
            )

            questionList.add(
                QuizQuestion(
                    1,
                    "What is the output of the following code snippet?\n\nSystem.out.println(10 + 20 + \"Hello\");",
                    "10Hello",
                    "30Hello",
                    "1020Hello",
                    "Error",
                    3,
                    "easy"
                )
            )

            questionList.add(
                QuizQuestion(
                    1,
                    "What is the correct way to create an object of the Scanner class in Java to take input from the user?",
                    "Scanner scanner = new Scanner(System.in);",
                    "Scanner(System.in) scanner = new Scanner();",
                    "scanner(System.in) = new Scanner();",
                    "new Scanner(System.in) scanner;",
                    1,
                    "easy"
                )
            )

            questionList.add(
                QuizQuestion(
                    1,
                    "Which keyword is used to define a constant in Java?",
                    "static",
                    "final",
                    "constant",
                    "const",
                    2,
                    "easy"
                )
            )

            questionList.add(
                QuizQuestion(
                    1,
                    "What is the correct way to write a single-line comment in Java?",
                    "// This is a comment",
                    "/* This is a comment */",
                    "# This is a comment",
                    "' This is a comment",
                    1,
                    "easy"
                )
            )

            questionList.add(
                QuizQuestion(
                    1,
                    "What is the correct way to declare a method in Java that does not return any value?",
                    "void method() {}",
                    "int method() {}",
                    "method() {}",
                    "void method;",
                    1,
                    "easy"
                )
            )

            questionList.add(
                QuizQuestion(
                    1,
                    "Which operator is used to perform modulus division in Java?",
                    "%",
                    "/",
                    "*",
                    "**",
                    1,
                    "easy"
                )
            )

            questionList.add(
                QuizQuestion(
                    1,
                    "Which Java keyword is used to exit a loop?",
                    "break",
                    "exit",
                    "continue",
                    "return",
                    1,
                    "easy"
                )
            )

// Medium Questions
            questionList.add(
                QuizQuestion(
                    1,
                    "What is the output of the following code snippet?\n\nint x = 10;\nSystem.out.println(x > 5 ? (x < 15 ? 1 : 2) : 3);",
                    "1",
                    "2",
                    "3",
                    "Error",
                    1,
                    "medium"
                )
            )

            questionList.add(
                QuizQuestion(
                    1,
                    "What is the output of the following code snippet?\n\nint x = 5;\nint y = x++ + ++x;\nSystem.out.println(x + \" \" + y);",
                    "6 11",
                    "11 6",
                    "12 12",
                    "Compilation error",
                    2,
                    "medium"
                )
            )

            questionList.add(
                QuizQuestion(
                    1,
                    "What is the output of the following code snippet?\n\nint x = 10;\nint y = x-- - --x;\nSystem.out.println(x + \" \" + y);",
                    "8 21",
                    "21 8",
                    "9 19",
                    "Compilation error",
                    1,
                    "medium"
                )
            )

            questionList.add(
                QuizQuestion(
                    1,
                    "What is the output of the following code snippet?\n\nint x = 5;\nint y = x-- - --x + x--;\nSystem.out.println(x + \" \" + y);",
                    "2 15",
                    "3 14",
                    "2 14",
                    "Compilation error",
                    3,
                    "medium"
                )
            )

            questionList.add(
                QuizQuestion(
                    1,
                    "What is the output of the following code snippet?\n\nint x = 3;\nSystem.out.println(x >> 1);",
                    "1",
                    "2",
                    "3",
                    "Error",
                    1,
                    "medium"
                )
            )

            questionList.add(
                QuizQuestion(
                    1,
                    "What is the output of the following code snippet?\n\nint x = -10;\nSystem.out.println(x >>> 2);",
                    "-2",
                    "-3",
                    "1073741822",
                    "1073741823",
                    3,
                    "medium"
                )
            )

            questionList.add(
                QuizQuestion(
                    1,
                    "What is the output of the following code snippet?\n\nint x = 10;\nSystem.out.println(x & 6);",
                    "2",
                    "4",
                    "6",
                    "10",
                    2,
                    "medium"
                )
            )

            questionList.add(
                QuizQuestion(
                    1,
                    "What is the output of the following code snippet?\n\nint x = 10;\nSystem.out.println(x | 4);",
                    "6",
                    "4",
                    "14",
                    "10",
                    6,
                    "medium"
                )
            )

            questionList.add(
                QuizQuestion(
                    1,
                    "What is the output of the following code snippet?\n\nint x = 10;\nSystem.out.println(x ^ 6);",
                    "2",
                    "4",
                    "6",
                    "10",
                    12,
                    "medium"
                )
            )

            questionList.add(
                QuizQuestion(
                    1,
                    "What is the output of the following code snippet?\n\nint x = 5;\nSystem.out.println(~x);",
                    "-5",
                    "-6",
                    "5",
                    "6",
                    -6,
                    "medium"
                )
            )

// Hard Questions
            questionList.add(
                QuizQuestion(
                    1,
                    "What is the output of the following Java code?\n\npublic class Test {\n    public static void main(String[] args) {\n        int x = 5;\n        System.out.println(++x);\n    }\n}",
                    "5",
                    "6",
                    "Compilation error",
                    "Runtime error",
                    2,
                    "hard"
                )
            )

            for (question in questionList) {
                quizDBHelper.addQuestion(question)
            }
            markQuestionsAsAdded()
        }
    }

    private fun handleOptionClick(selectedOptionIndex: Int) {
        // Disable all option buttons to prevent multiple clicks
        disableOptionButtons()

        // Check if the selected option is correct
        val selectedOption = selectedOptionIndex
        val correctOption = questionList[currentQuestionIndex].correctOption

        if (selectedOption == correctOption) {
            correctAnswers++
        }
        setOptionBackgroundGradient(selectedOption, R.drawable.bgcard_red)
        setOptionBackgroundGradient(correctOption, R.drawable.bgcard_green)

        // Delay for 2 seconds using Handler
        Handler().postDelayed({
            // Increment the current question index
            currentQuestionIndex++
            if (currentQuestionIndex < userQueNeed) {
                // Display the next question with appropriate difficulty level and number of questions
                displayQuestion(
                    userQueNeed,
                    userDiffNeed.toString()
                ) // Change difficulty level and number of questions as needed
            } else {
                // No more questions, show end of quiz message
                Toast.makeText(this, "End of Quiz", Toast.LENGTH_SHORT).show()
                // Start QuizResultActivity with quiz result
                val intent = Intent(this, QuizResultActivity::class.java)
                intent.putExtra("totalQuestions", userQueNeed)
                intent.putExtra("correctAnswers", correctAnswers)
                intent.putExtra("que_level", userDiffNeed)

                startActivity(intent)

                //startActivity(Intent(this,QuizResultActivity::class.java))
                // Handle end of quiz (e.g., show quiz result or navigate to another activity)
            }


            // Reset the background color of all option buttons
            resetOptionButtonBackgrounds()

            // Enable option buttons again
            enableOptionButtons()
        }, 1000)
    }

    private fun setOptionBackgroundGradient(optionIndex: Int, gradientResId: Int) {
        val gradientDrawable = ContextCompat.getDrawable(this, gradientResId)

        when (optionIndex) {
            1 -> binding.option1.background = gradientDrawable
            2 -> binding.option2.background = gradientDrawable
            3 -> binding.option3.background = gradientDrawable
            4 -> binding.option4.background = gradientDrawable
        }
    }


    private fun disableOptionButtons() {
        binding.option1.isEnabled = false
        binding.option2.isEnabled = false
        binding.option3.isEnabled = false
        binding.option4.isEnabled = false
    }

    private fun enableOptionButtons() {
        binding.option1.isEnabled = true
        binding.option2.isEnabled = true
        binding.option3.isEnabled = true
        binding.option4.isEnabled = true
    }

    private fun resetOptionButtonBackgrounds() {
        binding.option1.background = ContextCompat.getDrawable(this, R.drawable.bgcard)
        binding.option2.background = ContextCompat.getDrawable(this, R.drawable.bgcard)
        binding.option3.background = ContextCompat.getDrawable(this, R.drawable.bgcard)
        binding.option4.background = ContextCompat.getDrawable(this, R.drawable.bgcard)
    }


    private fun areQuestionsAdded(): Boolean {
        val sharedPreferences = getSharedPreferences("APP_PREFS", Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean("QUESTIONS_ADDED", false)
    }

    private fun markQuestionsAsAdded() {
        val sharedPreferences = getSharedPreferences("APP_PREFS", Context.MODE_PRIVATE)
        sharedPreferences.edit().putBoolean("QUESTIONS_ADDED", true).apply()
    }

    private fun displayQuestion(numQuestions: Int, difficultyLevel: String) {
        questionList =
            quizDBHelper.getQuestionsByDifficulty(difficultyLevel, numQuestions).shuffled()
                .take(numQuestions)
        // Now you have questions based on the specified difficulty level and number
        // Proceed with displaying these questions as desired
        // Example:
        val question = questionList[currentQuestionIndex]
        binding.queNum.text = "${currentQuestionIndex + 1}/$userQueNeed"

        binding.que.text = question.questionText
        binding.op1.text = question.option1
        binding.op2.text = question.option2
        binding.op3.text = question.option3
        binding.op4.text = question.option4
    }

}

data class QuizQuestion(
    val id: Int,
    val questionText: String,
    val option1: String,
    val option2: String,
    val option3: String,
    val option4: String,
    val correctOption: Int,
    val difficultyLevel: String
)

class QuizDBHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "QuizDatabase"
        private const val TABLE_QUESTIONS = "questionsTable"
        private const val KEY_ID = "id"
        private const val KEY_QUESTION = "question_text"
        private const val KEY_OPTION1 = "option1"
        private const val KEY_OPTION2 = "option2"
        private const val KEY_OPTION3 = "option3"
        private const val KEY_OPTION4 = "option4"
        private const val KEY_CORRECT_OPTION = "correct_option"
        private const val KEY_DIFFICULTY_LEVEL = "difficulty_level"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTableQuery =
            "CREATE TABLE $TABLE_QUESTIONS ($KEY_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "$KEY_QUESTION TEXT, $KEY_OPTION1 TEXT, $KEY_OPTION2 TEXT, " +
                    "$KEY_OPTION3 TEXT, $KEY_OPTION4 TEXT, $KEY_CORRECT_OPTION INTEGER, $KEY_DIFFICULTY_LEVEL TEXT)"
        db.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_QUESTIONS")
        onCreate(db)
    }

    fun addQuestion(question: QuizQuestion) {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(KEY_QUESTION, question.questionText)
            put(KEY_OPTION1, question.option1)
            put(KEY_OPTION2, question.option2)
            put(KEY_OPTION3, question.option3)
            put(KEY_OPTION4, question.option4)
            put(KEY_CORRECT_OPTION, question.correctOption)
            put(KEY_DIFFICULTY_LEVEL, question.difficultyLevel)
        }
        db.insert(TABLE_QUESTIONS, null, values)
        db.close()
    }

    @SuppressLint("Range")
    fun getQuestionsByDifficulty(difficulty: String, numQuestions: Int): List<QuizQuestion> {
        val questionList = mutableListOf<QuizQuestion>()
        val selectQuery =
            "SELECT * FROM $TABLE_QUESTIONS WHERE $KEY_DIFFICULTY_LEVEL = ? ORDER BY RANDOM() LIMIT $numQuestions"
        val db = this.readableDatabase
        val cursor = db.rawQuery(selectQuery, arrayOf(difficulty))
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndex(KEY_ID))
                val questionText = cursor.getString(cursor.getColumnIndex(KEY_QUESTION))
                val option1 = cursor.getString(cursor.getColumnIndex(KEY_OPTION1))
                val option2 = cursor.getString(cursor.getColumnIndex(KEY_OPTION2))
                val option3 = cursor.getString(cursor.getColumnIndex(KEY_OPTION3))
                val option4 = cursor.getString(cursor.getColumnIndex(KEY_OPTION4))
                val correctOption = cursor.getInt(cursor.getColumnIndex(KEY_CORRECT_OPTION))
                val question = QuizQuestion(
                    id,
                    questionText,
                    option1,
                    option2,
                    option3,
                    option4,
                    correctOption,
                    difficulty
                )
                questionList.add(question)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return questionList
    }

}

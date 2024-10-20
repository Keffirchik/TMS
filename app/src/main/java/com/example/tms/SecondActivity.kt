package com.example.tms

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity


lateinit var login : String

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)


//        val textView = TextView(this)

//        textView.textSize = 26f
//        textView.setPadding(16, 16, 16, 16)

//        val intent = intent



//        val arguments = intent.extras



//        if (arguments != null) {
//            val loing = arguments["name"].toString()
//            val company = arguments.getString("company")
//            val age = arguments.getInt("age")
//            textView.text = """
//                Name: $name
//                Company: $company
//                Age: $age
//                """.trimIndent()
//        }



        val textView = findViewById<TextView>(R.id.second_text_view)
        val login = intent.getStringExtra("login")
        textView.text = "Hello $login we are happy to see you!"
//        setContentView(textView)
    }
}
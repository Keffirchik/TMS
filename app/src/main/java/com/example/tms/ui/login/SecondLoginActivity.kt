package com.example.tms.ui.login

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tms.R

class SecondLoginActivity : AppCompatActivity() {

    lateinit var login : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second_login)

        val textView = findViewById<TextView>(R.id.second_text_view)
        val login = intent.getStringExtra("login")
        textView.text = "Hello $login we are happy to see you!"

    }
}
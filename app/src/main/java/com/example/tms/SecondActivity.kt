package com.example.tms

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)

        val buttonGoMainctivity = findViewById<Button>(R.id.go_to_main_button)

        val intentGoToMainActivity = Intent(this.baseContext, MainActivity::class.java)

        buttonGoMainctivity.setOnClickListener {
            startActivity(intentGoToMainActivity)
        }
    }
}
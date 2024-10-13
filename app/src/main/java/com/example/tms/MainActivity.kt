package com.example.tms

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val mainButton = findViewById<Button>(R.id.main_button)

        val intentMainButton = Intent(this.baseContext, NotesActivity::class.java)

        mainButton.setOnClickListener {
            startActivity(intentMainButton)
        }

        val buttonGoToSecondActivity = findViewById<Button>(R.id.button_to_second_activity)

        val intentGoToSecondActivity = Intent(this.baseContext, SecondActivity::class.java)

        buttonGoToSecondActivity.setOnClickListener {
            startActivity(intentGoToSecondActivity)
        }

    }
}
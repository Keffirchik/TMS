package com.example.tms

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainLoginActivity : AppCompatActivity() {

    private lateinit var usernameInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_login)

        usernameInput = findViewById(R.id.username_input)
        passwordInput = findViewById(R.id.password_input)
        loginButton = findViewById(R.id.login_button)

        val intentGoToSecondActivity = Intent(this.baseContext, SecondActivity::class.java)

        loginButton.setOnClickListener {

            if (usernameInput.text.toString().length > 1 && passwordInput.text.toString().length > 1) {
                intentGoToSecondActivity.putExtra("login", usernameInput.text.toString());
                startActivity(intentGoToSecondActivity)
            }

        }
    }
}
package com.example.tms

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var usernameInput : EditText
    lateinit var passwordInput : EditText
    lateinit var loginButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        usernameInput = findViewById(R.id.username_input)
        passwordInput = findViewById(R.id.password_input)
        loginButton = findViewById(R.id.login_button)

        val intentGoToSecondActivity = Intent(this.baseContext, SecondActivity::class.java)

        loginButton.setOnClickListener {
//            val username = usernameInput.text.toString()
//            val password = passwordInput.text.toString()


            if(usernameInput.text.toString().length > 1 && passwordInput.text.toString().length > 1) {
                intentGoToSecondActivity.putExtra("login", usernameInput.text.toString());
                startActivity(intentGoToSecondActivity)
            }

//            Log.i("Test Credentials", "Username : $username and Password : $password")
        }

    }
}
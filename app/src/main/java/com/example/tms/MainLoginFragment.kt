package com.example.tms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment

class MainLoginFragment : Fragment() {

    private lateinit var usernameInput: EditText
    private lateinit var passwordInput: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val currentView = inflater.inflate(R.layout.fragment_main_login, container, false)
        usernameInput = currentView.findViewById(R.id.ll_username_input_fml)
        passwordInput = currentView.findViewById(R.id.ll_password_input_fml)

        val secondLoginFramgent = SecondLoginFragment()

        val loginButton = currentView.findViewById<AppCompatButton>(R.id.ll_login_button_fml)
        loginButton.setOnClickListener {
            if (usernameInput.text.toString().length > 1 && passwordInput.text.toString().length > 1) {
                secondLoginFramgent.arguments?.putString("loginInput", usernameInput.text.toString())
                parentFragmentManager.beginTransaction()
                    .replace(R.id.mainFragmentView, secondLoginFramgent)
                    .addToBackStack(null)
                    .commit()
            }
        }

        return currentView
    }

}
package com.example.tms.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import com.example.tms.R
import com.example.tms.presentation.view.activities.MainActivity

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


        val loginButton = currentView.findViewById<AppCompatButton>(R.id.ll_login_button_fml)
        loginButton.setOnClickListener {
            if (usernameInput.text.toString().length > 1 && passwordInput.text.toString().length > 1) {
                val bundle = Bundle()
                val fragment = SecondLoginFragment()
                bundle.putString("loginInput", usernameInput.text.toString())
                fragment.arguments = bundle
                (activity as MainActivity).openFragment(fragment)
            }
        }

        return currentView
    }

}
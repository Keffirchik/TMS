package com.example.tms.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.tms.data.storage.SPObject
import com.example.tms.databinding.FragmentMainLoginBinding
import com.example.tms.presentation.view.activities.MainActivity

class MainLoginFragment : Fragment() {

    private lateinit var usernameInput: EditText
    private lateinit var passwordInput: EditText

    private var _binding: FragmentMainLoginBinding? = null
    private val binding: FragmentMainLoginBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMainLoginBinding.inflate(inflater)

//        val currentView = inflater.inflate(R.layout.fragment_main_login, container, false)

//        usernameInput = currentView.findViewById(R.id.ll_username_input_fml)
        usernameInput = binding.llUsernameInputFml
//        passwordInput = currentView.findViewById(R.id.ll_password_input_fml)
        passwordInput = binding.llPasswordInputFml

//        val loginButton = currentView.findViewById<AppCompatButton>(R.id.ll_login_button_fml)
//        loginButton.setOnClickListener {
        binding.llLoginButtonFml.setOnClickListener {

            val listOfLoginPass: List<String?> = SPObject.getLoginPassFromPreferences()
            val loginBD: String? = listOfLoginPass[0]
            val passwordBD: String? = listOfLoginPass[1]

            val login: String = usernameInput.text.toString()
            val password: String = passwordInput.text.toString()

            if (loginBD?.isEmpty() == true && login.length > 1 && password.length > 1) {

                val fragment = SecondLoginFragment()

                SPObject.putLoginPassToPreferences(login, password)

                (activity as MainActivity).openFragment(fragment)


            } else if (login != loginBD || password != passwordBD) {

                val text = "Wrong login or password"
                val duration = Toast.LENGTH_SHORT

                val toast = Toast.makeText(context, text, duration)
                toast.show()

            } else {

                val fragment = SecondLoginFragment()
                (activity as MainActivity).openFragment(fragment)

            }
        }

//        return currentView
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
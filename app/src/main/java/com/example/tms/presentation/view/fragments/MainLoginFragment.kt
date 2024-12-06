package com.example.tms.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import com.example.tms.R
import com.example.tms.data.storage.MySharedPreferences
import com.example.tms.data.storage.SPObject
import com.example.tms.presentation.view.activities.MainActivity

class MainLoginFragment : Fragment() {

    private lateinit var usernameInput: EditText
    private lateinit var passwordInput: EditText
    private var sharedPreferences: MySharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initSharedPreferences()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val currentView = inflater.inflate(R.layout.fragment_main_login, container, false)

        usernameInput = currentView.findViewById(R.id.ll_username_input_fml)
        passwordInput = currentView.findViewById(R.id.ll_password_input_fml)


        val loginButton = currentView.findViewById<AppCompatButton>(R.id.ll_login_button_fml)
        loginButton.setOnClickListener {

//            val listOfLoginPass: List<String?>? = sharedPreferences?.getLoginPassFromPreferences()
            val listOfLoginPass: List<String?> = SPObject.getLoginPassFromPreferences()
            val loginBD: String? = listOfLoginPass[0]
            val passwordBD: String? = listOfLoginPass[1]

            val login: String = usernameInput.text.toString()
            val password: String = passwordInput.text.toString()

            if (loginBD?.isEmpty() == true && login.length > 1 && password.length > 1) {

                val fragment = SecondLoginFragment()
//                bundle.putString("loginInput", usernameInput.text.toString())
//                fragment.arguments = bundle
                SPObject.putLoginPassToPreferences(login, password)
//                sharedPreferences?.putLoginPassToPreferences(login, password)
                (activity as MainActivity).openFragment(fragment)


            } else if (login != loginBD || password != passwordBD) {

                val text = "Wrong login or password"
                val duration = Toast.LENGTH_SHORT

                val toast = Toast.makeText(context, text, duration)
                toast.show()

            }
        }

        return currentView
    }

    private fun initSharedPreferences() {
        sharedPreferences = MySharedPreferences(context)
    }

}
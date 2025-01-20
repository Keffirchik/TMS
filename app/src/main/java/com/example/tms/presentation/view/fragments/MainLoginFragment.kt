package com.example.tms.presentation.view.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.tms.App
import com.example.tms.R
import com.example.tms.data.storage.RoomDB
import com.example.tms.data.storage.SharedPreferencesDB
import com.example.tms.data.storage.SharedPreferencesObject
import com.example.tms.databinding.FragmentMainLoginBinding
import javax.inject.Inject

class MainLoginFragment : Fragment() {

    private lateinit var usernameInput: EditText
    private lateinit var passwordInput: EditText

    private var _binding: FragmentMainLoginBinding? = null
    private val binding: FragmentMainLoginBinding get() = _binding!!

    @Inject
    lateinit var sharedPreferencesDB: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as App).appComponent?.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMainLoginBinding.inflate(inflater)

        usernameInput = binding.llUsernameInputFml
        passwordInput = binding.llPasswordInputFml

        binding.llLoginButtonFml.setOnClickListener {

            val listOfLoginPass: List<String?> = (sharedPreferencesDB as SharedPreferencesDB).getLoginPassFromPreferences()
//            val listOfLoginPass: List<String?> = SharedPreferencesObject.getLoginPassFromPreferences()
            val loginBD: String? = listOfLoginPass[0]
            val passwordBD: String? = listOfLoginPass[1]

            val login: String = usernameInput.text.toString()
            val password: String = passwordInput.text.toString()

            if (loginBD?.isEmpty() == true && login.length > 1 && password.length > 1) {

                (sharedPreferencesDB as SharedPreferencesDB).putLoginPassToPreferences(login, password)
//                SharedPreferencesObject.putLoginPassToPreferences(login, password)

            } else if (login != loginBD || password != passwordBD) {

                val text = "Wrong login or password"
                val duration = Toast.LENGTH_SHORT

                val toast = Toast.makeText(context, text, duration)
                toast.show()
                binding.customView.showError("Wrong login or password")

            } else {
                findNavController().navigate(R.id.action_mainLoginFragment_to_secondLoginFragment)
            }
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
package com.example.tms.presentation.view.fragments

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tms.App
import com.example.tms.data.storage.SharedPreferencesDB
import com.example.tms.data.storage.SharedPreferencesObject
import com.example.tms.databinding.FragmentSecondLoginBinding
import javax.inject.Inject

class SecondLoginFragment : Fragment() {

    private var _binding: FragmentSecondLoginBinding? = null
    private val binding: FragmentSecondLoginBinding get() = _binding!!

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

        _binding = FragmentSecondLoginBinding.inflate(inflater)

//        val listOfLoginPass: List<String?> = sharedPreferencesDB.getLoginPassFromPreferences()
        val listOfLoginPass: List<String?> = SharedPreferencesObject.getLoginPassFromPreferences()
        val login: String? = listOfLoginPass[0]

        binding.clSecondTextViewSlf.text = "Hello $login we are happy to see you!"

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
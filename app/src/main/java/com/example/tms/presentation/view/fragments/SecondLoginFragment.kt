package com.example.tms.presentation.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tms.data.storage.SPObject
import com.example.tms.databinding.FragmentSecondLoginBinding

class SecondLoginFragment : Fragment() {

    private var _binding: FragmentSecondLoginBinding? = null
    private val binding: FragmentSecondLoginBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSecondLoginBinding.inflate(inflater)

//        val currentView = inflater.inflate(R.layout.fragment_second_login, container, false)

        val listOfLoginPass: List<String?> = SPObject.getLoginPassFromPreferences()
        val login: String? = listOfLoginPass[0]

//        val textView = currentView.findViewById<TextView>(R.id.cl_second_text_view_slf)

//        textView.text = "Hello $login we are happy to see you!"
        binding.clSecondTextViewSlf.text = "Hello $login we are happy to see you!"

//        return currentView
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
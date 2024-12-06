package com.example.tms.presentation.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.tms.R
import com.example.tms.data.storage.SPObject

class SecondLoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val currentView = inflater.inflate(R.layout.fragment_second_login, container, false)

        val listOfLoginPass: List<String?> = SPObject.getLoginPassFromPreferences()
        val login: String? = listOfLoginPass[0]

        val textView = currentView.findViewById<TextView>(R.id.cl_second_text_view_slf)

        textView.text = "Hello $login we are happy to see you!"

        return currentView
    }

}
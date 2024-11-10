package com.example.tms

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.widget.AppCompatButton

class SecondFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val currentView = inflater.inflate(R.layout.fragment_second, container, false)

        val buttonGoMainctivity =
            currentView.findViewById<AppCompatButton>(R.id.cl_go_to_main_button_fs)
        buttonGoMainctivity.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.mainFragmentView, MainFragment())
                .addToBackStack(null)
                .commit()

        }

        return currentView
    }

}
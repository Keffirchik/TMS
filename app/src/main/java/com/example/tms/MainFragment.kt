package com.example.tms

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val currentView = inflater.inflate(R.layout.fragment_main, container, false)

        //login button
        val buttonLogin = currentView.findViewById<AppCompatButton>(R.id.fragment_main_button)

        buttonLogin.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.mainFragmentView, MainLoginFragment())
                .addToBackStack(null)
                .commit()
        }

        //button to second screen
        val buttonGoToSecondActivity =
            currentView.findViewById<AppCompatButton>(R.id.button_to_second_activity)

        buttonGoToSecondActivity.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.mainFragmentView, SecondFragment())
                .addToBackStack(null)
                .commit()
        }

        //button to notes
        val noteButton = currentView.findViewById<AppCompatButton>(R.id.acb_go_to_notes_am)

        noteButton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.mainFragmentView, NotesFragment())
                .addToBackStack(null)
                .commit()
        }

        return currentView
    }

}
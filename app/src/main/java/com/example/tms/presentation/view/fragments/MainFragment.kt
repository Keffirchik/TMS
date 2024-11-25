package com.example.tms.presentation.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.lifecycleScope
import com.example.tms.R
import com.example.tms.presentation.view.activities.MainActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val currentView = inflater.inflate(R.layout.fragment_main, container, false)

        //login button
        val buttonLogin = currentView.findViewById<AppCompatButton>(R.id.fragment_main_button)

        buttonLogin.setOnClickListener {
            val fragment = MainLoginFragment()
            (activity as MainActivity).openFragment(fragment)
        }

        //button to second screen
        val buttonGoToSecondActivity =
            currentView.findViewById<AppCompatButton>(R.id.button_to_second_activity)

        buttonGoToSecondActivity.setOnClickListener {
            val fragment = SecondFragment()
            (activity as MainActivity).openFragment(fragment)
        }

        //button to notes
        val noteButton = currentView.findViewById<AppCompatButton>(R.id.acb_go_to_notes_am)

        val progressBar = currentView.findViewById<ProgressBar>(R.id.progressBar)

        noteButton.setOnClickListener {
            val fragment = NotesFragment()

            showWaitingIconLaunch(progressBar, fragment)

        }

        return currentView
    }

    private fun showWaitingIconLaunch(progressBar: ProgressBar, fragment: Fragment) {
        lifecycleScope.launch {
            showWaitingIcon(progressBar)
            (activity as MainActivity).openFragment(fragment)
        }

    }

    private suspend fun showWaitingIcon(progressBar: ProgressBar) {
        progressBar.visibility = View.VISIBLE
        delay(3_000)
        progressBar.visibility = View.GONE
    }


}
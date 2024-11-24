package com.example.tms.presentation.view.fragments

import android.icu.util.Calendar
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import androidx.lifecycle.lifecycleScope
import com.example.tms.R
import com.example.tms.data.storage.MySharedPreferences
import com.example.tms.presentation.view.activities.MainActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AddNoteFragment : Fragment() {

    private var sharedPreferences: MySharedPreferences? = null


    private lateinit var titleEditText: EditText
    private lateinit var contentEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initSharedPreferences()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val currentView = inflater.inflate(R.layout.fragment_add_note, container, false)

        // save button
        val saveButton = currentView.findViewById<Button>(R.id.ll_add_new_note_fan)
        val progressBar = currentView.findViewById<ProgressBar>(R.id.pb_progressBar_fan)
        saveButton.setOnClickListener {

            saveNoteToPreferences(currentView)

            val fragment = NotesFragment()

            showWaitingIconLaunch(progressBar, fragment)
//            (activity as MainActivity).openFragment(fragment)
        }

        // cancel button
        val cancelButton = currentView.findViewById<Button>(R.id.ll_cancel_buttonLfan)
        cancelButton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.mainFragmentView, NotesFragment())
                .addToBackStack(null)
                .commit()
        }

        return currentView
    }

    private fun showWaitingIconLaunch(progressBar: ProgressBar?, fragment: NotesFragment) {
        lifecycleScope.launch {
            showWaitingIcon(progressBar, fragment)

        }
    }

    private suspend fun showWaitingIcon(progressBar: ProgressBar?, fragment: NotesFragment) {
        progressBar?.visibility = View.VISIBLE
        delay(3_000)
        progressBar?.visibility = View.GONE
        (activity as MainActivity).openFragment(fragment)
    }

    private fun initSharedPreferences() {
        sharedPreferences = MySharedPreferences(context)
    }

    private fun saveNoteToPreferences(currentView: View?) {
        titleEditText = currentView?.findViewById(R.id.titleEditText)!!
        contentEditText = currentView.findViewById(R.id.contentEditText)!!

        val title = titleEditText.text.toString()
        val content = contentEditText.text.toString()
        val noteDate = Calendar.getInstance().time.toString()

        if (title.isNotEmpty() && content.isNotEmpty()) {
            sharedPreferences?.addElementsToPreferences(title, content, noteDate)

        }
    }


}
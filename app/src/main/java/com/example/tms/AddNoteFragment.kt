package com.example.tms

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.icu.util.Calendar
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import kotlin.properties.Delegates

class AddNoteFragment : Fragment() {

    private val prefsName: String = "NotePrefs"
    private val keyNoteCount: String = "NoteCount"

    private lateinit var titleEditText: EditText
    private lateinit var contentEditText: EditText

    private lateinit var saveButton: Button
    private lateinit var cancelButton: Button

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    private var noteCount by Delegates.notNull<Int>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val currentView = inflater.inflate(R.layout.fragment_add_note, container, false)

        // save button
        val saveButton = currentView.findViewById<Button>(R.id.ll_add_new_note_fan)
        saveButton.setOnClickListener {
            saveNote(currentView)
            parentFragmentManager.beginTransaction()
                .replace(R.id.mainFragmentView, NotesFragment())
                .addToBackStack(null)
                .commit()
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

    private fun saveNote(currentView: View?) {
        titleEditText = currentView?.findViewById(R.id.titleEditText)!!
        contentEditText = currentView.findViewById(R.id.contentEditText)!!

        val title = titleEditText.text.toString()
        val content = contentEditText.text.toString()
        val noteDate = Calendar.getInstance().time.toString()

        if (title.isNotEmpty() && content.isNotEmpty()) {
            saveNotesToPreferences(title, content, noteDate)
        }
    }

    private fun saveNotesToPreferences(title: String, content: String, noteDate: String) {
        sharedPreferences = context?.getSharedPreferences(prefsName, MODE_PRIVATE)!!
        editor = sharedPreferences.edit()
        noteCount = sharedPreferences.getInt(keyNoteCount, 0)

        editor.putString("note_title_$noteCount", title)
        editor.putString("note_content_$noteCount", content)
        editor.putString("note_date_$noteCount", noteDate)

        editor.putInt(keyNoteCount, noteCount + 1)

        editor.apply()
    }


}
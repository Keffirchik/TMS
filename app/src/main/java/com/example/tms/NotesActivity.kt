package com.example.tms

import Note
import android.app.AlertDialog
import android.content.Intent
import android.content.SharedPreferences
import android.icu.util.Calendar
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import kotlin.properties.Delegates

class NotesActivity : AppCompatActivity() {

    private val PREFS_NAME: String = "NotePrefs"
    private val KEY_NOTE_COUNT: String = "NoteCount"

    private lateinit var titleEditText: EditText
    private lateinit var contentEditText: EditText

    private lateinit var saveButton: Button
    private lateinit var cancelButton: Button

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    private var noteCount by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_notes)

        val intentGoToMainActivity = Intent(this.baseContext, MainActivity::class.java)

        saveButton = findViewById(R.id.addNewNote)
        saveButton.setOnClickListener {
            saveNote()
            startActivity(intentGoToMainActivity)
        }

        cancelButton = findViewById(R.id.cancelButton)
        cancelButton.setOnClickListener {
            startActivity(intentGoToMainActivity)
        }
    }

    private fun saveNote() {
        titleEditText = findViewById(R.id.titleEditText)
        contentEditText = findViewById(R.id.contentEditText)

        val title = titleEditText.text.toString()
        val content = contentEditText.text.toString()
        val noteDate = Calendar.getInstance().time.toString()

        if (title.isNotEmpty() && content.isNotEmpty()) {
            saveNotesToPreferences(title, content, noteDate)
        }
    }

    private fun saveNotesToPreferences(title: String, content: String, noteDate: String) {
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
        editor = sharedPreferences.edit()
        noteCount = sharedPreferences.getInt(KEY_NOTE_COUNT, 0) + 1

        editor.putString("note_title_$noteCount", title)
        editor.putString("note_content_$noteCount", content)
        editor.putString("note_date_$noteCount", noteDate)

        editor.putInt(KEY_NOTE_COUNT, noteCount)

        editor.apply()
    }
}
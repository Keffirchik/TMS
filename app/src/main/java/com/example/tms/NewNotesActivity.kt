package com.example.tms

import Note
import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class NewNotesActivity : AppCompatActivity() {

    private val PREFS_NAME: String = "NotePrefs"
    private val KEY_NOTE_COUNT: String = "NoteCount"

    private lateinit var noteContainer: LinearLayout
    private lateinit var noteList: MutableList<Note>

    private lateinit var titleEditText: EditText
    private lateinit var contentEditText: EditText

    private lateinit var saveButton: Button

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    private lateinit var titleTextView: TextView
    private lateinit var contentTextView: TextView

    private lateinit var noteView: View

    private lateinit var builder: AlertDialog.Builder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_new_notes)

        noteContainer = findViewById(R.id.notesContainer)
        saveButton = findViewById(R.id.saveButton)

        saveButton.setOnClickListener {
            saveNote()
        }

        displayNotes()
        loadNotesFromPreferences()
    }

    private fun displayNotes() {
        for (note in noteList) {
            createNoteView(note)
        }
    }

    private fun loadNotesFromPreferences() {
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
        val noteCount = sharedPreferences.getInt(KEY_NOTE_COUNT, 0)

        for (i in 0..noteCount) {
            val title = sharedPreferences.getString("note_title_" + i, "")
            val content = sharedPreferences.getString("note_content_" + i, "")

            val note = Note(title, content)

            noteList.add(note)

        }
    }

    private fun saveNote() {
        titleEditText = findViewById(R.id.titleEditText)
        contentEditText = findViewById(R.id.contentEditText)

        val title = titleEditText.text.toString()
        val content = contentEditText.text.toString()

        if (title.isNotEmpty() && content.isNotEmpty()) {
            val note = Note(title, content)

            noteList.add(note)
            saveNotesToPreferences()

            createNoteView(note)
            clearInputFields()
        }

    }

    private fun clearInputFields() {
        titleEditText = findViewById(R.id.titleEditText)
        contentEditText = findViewById(R.id.contentEditText)

        titleEditText.text.clear()
        contentEditText.text.clear()
    }

    @SuppressLint("InflateParams")
    private fun createNoteView(note: Note) {
        noteView = layoutInflater.inflate(R.layout.note_item, null)
        titleTextView = noteView.findViewById(R.id.titleTextView)
        contentTextView = noteView.findViewById(R.id.contentTextView)

        titleTextView.text = note.title
        contentTextView.text = note.content

        noteView.setOnClickListener {
            showDeleteDialog(note)
            return@setOnClickListener
        }

    }

    private fun showDeleteDialog(note: Note) {
        builder = AlertDialog.Builder(this)
        builder.setTitle("Delete this note.")
        builder.setMessage("Are you sure you want delete this note?")
        //val a : (DialogInterface, Int)? = null
        builder.setPositiveButton(
            "Delete"
        ) { _, _ -> deleteNoteAndRefresh(note) }
//        builder.setPositiveButton(

//            "Delete", DialogInterface.OnClickListener(
//                deleteNoteAndRefresh(note)
//            )
//        )
        builder.setNeutralButton("Cancel", null)
        builder.show()

    }

    private fun deleteNoteAndRefresh(note: Note) {
        noteList.remove(note)
        saveNotesToPreferences()
        refreshNoteViews()
        //val a : (DialogInterface, Int)? = null
        return
    }

    private fun refreshNoteViews() {
        noteContainer.removeAllViews()
        saveNotesToPreferences()
        refreshNoteViews()
    }


    private fun saveNotesToPreferences() {
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
        editor = sharedPreferences.edit()

        editor.putInt(KEY_NOTE_COUNT, noteList.size)
        for (i in 1..noteList.size) {
            val note = noteList[i]
            editor.putString("note_title_" + i, note.title)
            editor.putString("note_content_" + i, note.content)
        }
        editor.apply()
    }

}
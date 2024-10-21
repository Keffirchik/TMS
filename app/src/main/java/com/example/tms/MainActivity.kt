package com.example.tms

import Note
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {

    private val prefsName: String = "NotePrefs"
    private val keyNoteCount: String = "NoteCount"

    private lateinit var noteContainer: LinearLayout
    private lateinit var noteList: MutableList<Note>

    private lateinit var addNewNote: Button

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    private lateinit var titleTextView: TextView
    private lateinit var contentTextView: TextView
    private lateinit var dateTextView: TextView

    private lateinit var noteView: View

    private lateinit var builder: AlertDialog.Builder

    private var noteCount by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        noteList = ArrayList()

        noteContainer = findViewById(R.id.notesContainer)

        val intentGoToNotesActivity = Intent(this.baseContext, NotesActivity::class.java)

        loadNotesFromPreferences()
        displayNotes()

        addNewNote = findViewById(R.id.addNote)
        addNewNote.setOnClickListener {
            startActivity(intentGoToNotesActivity)
        }
    }

    private fun displayNotes() {
        for (note in noteList) {
            createNoteView(note)
        }
    }

    private fun loadNotesFromPreferences() {
        sharedPreferences = getSharedPreferences(prefsName, MODE_PRIVATE)
        noteCount = sharedPreferences.getInt(keyNoteCount, 0)

        for (i in 0..<noteCount) {
            val title = sharedPreferences.getString("note_title_$i", "")
            val content = sharedPreferences.getString("note_content_$i", "")
            val noteDate = sharedPreferences.getString("note_date_$i", "")

            val note = Note(title, content, noteDate)

            noteList.add(note)

        }
    }

    @SuppressLint("InflateParams")
    private fun createNoteView(note: Note) {
        noteView = layoutInflater.inflate(R.layout.note_item, null)
        titleTextView = noteView.findViewById(R.id.titleTextView)
        contentTextView = noteView.findViewById(R.id.contentTextView)
        dateTextView = noteView.findViewById(R.id.dateTextView)

        titleTextView.text = note.title
        contentTextView.text = note.content
        dateTextView.text = note.noteDate.toString()

        noteView.setOnClickListener {
            showDeleteDialog(note)
            return@setOnClickListener
        }
        noteContainer.addView(noteView)
    }

    private fun showDeleteDialog(note: Note) {
        builder = AlertDialog.Builder(this)
        builder.setTitle("Delete this note.")
        builder.setMessage("Are you sure you want delete this note?")
        val a = object : DialogInterface.OnClickListener {
            override fun onClick(p0: DialogInterface?, p1: Int) {
                deleteNoteAndRefresh(note)
            }

        }
        builder.setPositiveButton(
            "Delete", a
        )

        builder.setNeutralButton("Cancel", null)
        builder.show()
    }

    private fun deleteNoteAndRefresh(note: Note) {
        noteList.remove(note)
        saveNotesToPreferences()
        refreshNoteViews()
        return
    }

    private fun refreshNoteViews() {
        noteContainer.removeAllViews()
        displayNotes()
    }

    private fun saveNotesToPreferences() {
        sharedPreferences = getSharedPreferences(prefsName, MODE_PRIVATE)
        editor = sharedPreferences.edit()

        editor.putInt(keyNoteCount, noteList.size)
        for (i in 1..<noteList.size) {
            val note = noteList[i]
            editor.putString("note_title_$i", note.title)
            editor.putString("note_content_$i", note.content)
        }
        editor.apply()
    }

}
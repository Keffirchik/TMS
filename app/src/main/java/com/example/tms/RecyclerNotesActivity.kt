package com.example.tms

import AdapterClass
import Note
import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.properties.Delegates

class RecyclerNotesActivity : AppCompatActivity() {

    private val prefsName: String = "NotePrefs"
    private val keyNoteCount: String = "NoteCount"

    private lateinit var noteContainer: LinearLayout
    private lateinit var noteList: MutableList<Note>

    private lateinit var addNewNote: Button
    private lateinit var goToMain: Button

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    private var noteCount by Delegates.notNull<Int>()

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_recycler_notes)

        noteList = ArrayList()

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        loadNotesFromPreferences()
        displayNotes()

        val intentGoToNotesActivity = Intent(this.baseContext, AddNotesActivity::class.java)

        addNewNote = findViewById(R.id.addNote)
        addNewNote.setOnClickListener {
            startActivity(intentGoToNotesActivity)
        }

        val intentGoMainActivity = Intent(this.baseContext, MainActivity::class.java)

        goToMain = findViewById(R.id.goToMain)
        goToMain.setOnClickListener {
            startActivity(intentGoMainActivity)
        }
    }

    private fun displayNotes() {
        recyclerView.adapter = AdapterClass(noteList) { id -> deleteNoteAndRefresh(id) }
//        for (note in noteList) {
//            createNoteView(note)
//        }
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

    private fun deleteNoteAndRefresh(id: Int) {
        noteList.removeAt(id)
        saveNotesToPreferences()
        return
    }

    private fun saveNotesToPreferences() {
        sharedPreferences = getSharedPreferences(prefsName, MODE_PRIVATE)
        editor = sharedPreferences.edit()

        editor.putInt(keyNoteCount, noteList.size)
        for (i in 0..<noteList.size) {
            val note = noteList[i]
            editor.putString("note_title_$i", note.title)
            editor.putString("note_content_$i", note.content)
        }
        editor.apply()
    }
}
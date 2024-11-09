package com.example.tms

import AdapterClass
import Note
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.properties.Delegates

class RecyclerNotesActivity : AppCompatActivity() {

//    private val listOfItems = listOf(
//        Custom.InfoBlock("Infoblock")
//    )

    private val prefsName: String = "NotePrefs"
    private val keyNoteCount: String = "NoteCount"

//    private var listOfItems: MutableList<Custom> = mutableListOf(Custom.InfoBlock("Infoblock"))
    private lateinit var listOfItems: MutableList<Custom>
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
        listOfItems = ArrayList()

        if (listOfItems.isEmpty()) {
            listOfItems.add(Custom.InfoBlock("Infoblock"))
        }

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
        addNoteToListOfItems()
//        recyclerView.adapter = AdapterClass(noteList) { id,command ->
        recyclerView.adapter = AdapterClass(listOfItems) { id,command ->
            when(command){
                "delete" -> deleteNoteAndRefresh(id)
                "share" -> shareNote(id)
            }
        }
    }

    private fun addNoteToListOfItems() {
        for (i in 0..<noteList.size) {
            val note = noteList[i]
            listOfItems.add(Custom.Note(
                note.title,
                note.content,
                note.noteDate
            ))
        }
    }

    private fun shareNote(id: Int) {
        val note = noteList[id]
        val intentShareNote: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "Title: ${note.title}\nNote: ${note.content}\nDate: ${note.noteDate}")
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(intentShareNote, null)
        startActivity(shareIntent)

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

//sealed interface Custom {
//    data class InfoBlock(
//        val info: String
//    ) :Custom
//    data class Note(
//        val title: String?,
//        val content: String?,
//        val date: String?
//    ) : Custom
//}
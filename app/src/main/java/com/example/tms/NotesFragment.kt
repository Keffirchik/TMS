package com.example.tms

import AdapterClass
import Note
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.properties.Delegates

class NotesFragment : Fragment() {

    private val prefsName: String = "NotePrefs"
    private val keyNoteCount: String = "NoteCount"

    private lateinit var listOfItems: MutableList<Custom>
    private lateinit var noteList: MutableList<Note>

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    private var noteCount by Delegates.notNull<Int>()

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val currentView = inflater.inflate(R.layout.fragment_notes, container, false)

        noteList = ArrayList()
        listOfItems = ArrayList()

        listOfItems.clear()
        listOfItems.add(Custom.InfoBlock("Infoblock"))

        recyclerView = currentView.findViewById(R.id.fragmentRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(currentView.context)

        displayNotes()

        //add note button
        val addNote = currentView.findViewById<Button>(R.id.ll_addNote_nt)
        addNote.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.mainFragmentView, AddNoteFragment())
                .addToBackStack(null)
                .commit()
        }

        //go to main button
        val goToMain = currentView.findViewById<Button>(R.id.ll_goToMain_nf)
        goToMain.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.mainFragmentView, MainFragment())
                .addToBackStack(null)
                .commit()
        }

        return currentView
    }

    private fun displayNotes() {
        loadNotesFromPreferences()
        addNoteToListOfItems()
        recyclerView.adapter = AdapterClass(listOfItems) { id, command ->
            when (command) {
                "delete" -> deleteNoteAndRefresh(id)
                "share" -> shareNote(id)
            }
        }
    }

    private fun deleteNoteAndRefresh(id: Int) {
        noteList.removeAt(id - 1)
        saveNotesToPreferences()
        addNoteToListOfItems()
        return
    }

    private fun saveNotesToPreferences() {
        sharedPreferences = context?.getSharedPreferences(prefsName, MODE_PRIVATE)!!
        editor = sharedPreferences.edit()

        editor.putInt(
            keyNoteCount,
            noteList.size
        )
        for (i in 0..<noteList.size) {
            val note = noteList[i]
            editor.putString("note_title_$i", note.title)
            editor.putString("note_content_$i", note.content)
            editor.putString("note_date_$i", note.noteDate)
        }
        editor.apply()
    }

    private fun shareNote(id: Int) {
        val note = noteList[id]
        val intentShareNote: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(
                Intent.EXTRA_TEXT,
                "Title: ${note.title}\nNote: ${note.content}\nDate: ${note.noteDate}"
            )
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(intentShareNote, null)
        startActivity(shareIntent)

    }

    private fun addNoteToListOfItems() {
        listOfItems.clear()
        listOfItems.add(Custom.InfoBlock("Infoblock"))
        for (i in 0..<noteList.size) {
            val note = noteList[i]
            listOfItems.add(
                Custom.Note(
                    note.title,
                    note.content,
                    note.noteDate
                )
            )
        }
    }

    private fun loadNotesFromPreferences() {
        sharedPreferences = context?.getSharedPreferences(prefsName, MODE_PRIVATE)!!
        noteCount = sharedPreferences.getInt(keyNoteCount, 0)

        for (i in 0..<noteCount) {
            val title = sharedPreferences.getString("note_title_$i", "")
            val content = sharedPreferences.getString("note_content_$i", "")
            val noteDate = sharedPreferences.getString("note_date_$i", "")

            val note = Note(title, content, noteDate)

            noteList.add(note)

        }
    }

}

sealed interface Custom {
    data class InfoBlock(
        val info: String
    ) : Custom

    data class Note(
        val title: String?,
        val content: String?,
        val date: String?
    ) : Custom
}
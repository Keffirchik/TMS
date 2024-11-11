package com.example.tms.presentation.view.fragments

import AdapterClass
import Note
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tms.R
import com.example.tms.data.storage.MySharedPreferences
import com.example.tms.presentation.model.NotesType
import com.example.tms.presentation.view.activities.MainActivity

class NotesFragment : Fragment() {

    private var listOfItems: MutableList<NotesType>? = null
    private var noteList: MutableList<Note>? = null

    private lateinit var recyclerView: RecyclerView //fixme

    private var sharedPreferences: MySharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initSharedPreferences()
    }

    private fun initSharedPreferences() {
        sharedPreferences = MySharedPreferences(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val currentView = inflater.inflate(R.layout.fragment_notes, container, false)

        noteList = ArrayList()
        listOfItems = ArrayList()

        listOfItems.clear()  //fixme
        listOfItems.add(NotesType.InfoBlock("Infoblock")) //fixme

        recyclerView = currentView.findViewById(R.id.fragmentRecyclerView)  //fixme
        recyclerView.layoutManager = LinearLayoutManager(currentView.context)

        displayNotes()

        //add note button
        val addNote = currentView.findViewById<Button>(R.id.ll_addNote_nt)
        addNote.setOnClickListener {
            val fragment = AddNoteFragment()
            (activity as MainActivity).openFragment(fragment)
        }

        //go to main button
        val goToMain = currentView.findViewById<Button>(R.id.ll_goToMain_nf)
        goToMain.setOnClickListener {
            val fragment = MainFragment()
            (activity as MainActivity).openFragment(fragment)
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

    private fun deleteNoteAndRefresh(id: Int) {   //fixme
        noteList.removeAt(id - 1)
        saveNotesToPreferences()
        addNoteToListOfItems()
        return
    }

    private fun saveNotesToPreferences() {

        sharedPreferences?.saveToPreferences(noteList)

//        val mySharedPreferences = context?.getSharedPreferences(prefsName, MODE_PRIVATE)!!
//        editor = mySharedPreferences.edit()
//
//        editor.putInt(
//            keyNoteCount,
//            noteList.size
//        )
//        for (i in 0..<noteList.size) {
//            val note = noteList[i]
//            editor.putString("note_title_$i", note.title)
//            editor.putString("note_content_$i", note.content)
//            editor.putString("note_date_$i", note.noteDate)
//        }
//        editor.apply()
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
        noteList = MySharedPreferences(context).loadFromPreferences()
    }

}

sealed interface Custom { //fixme
    data class InfoBlock(
        val info: String
    ) : Custom

    data class Note(
        val title: String?,
        val content: String?,
        val date: String?
    ) : Custom
}
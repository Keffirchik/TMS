package com.example.tms.presentation.view.fragments

import AdapterClass
import com.example.tms.domain.models.Note
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tms.R
import com.example.tms.data.storage.MySharedPreferences
import com.example.tms.data.storage.RoomObject
import com.example.tms.databinding.FragmentNotesBinding
import com.example.tms.presentation.model.NotesType
import com.example.tms.presentation.view.activities.MainActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesFragment : Fragment() {

    private var listOfItems: MutableList<NotesType>? = null
    private var noteList: MutableList<Note>? = null

    private var recyclerView: RecyclerView? = null

    private var sharedPreferences: MySharedPreferences? = null

    private var _binding: FragmentNotesBinding? = null
    private val binding: FragmentNotesBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initSharedPreferences()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentNotesBinding.inflate(inflater)

//        val currentView = inflater.inflate(R.layout.fragment_notes, container, false)
        initRecyclerView(binding.root)
//        initRecyclerView(currentView)

        noteList = ArrayList()
        listOfItems = ArrayList()

        (listOfItems as ArrayList<NotesType>).clear()  //fixme
        (listOfItems as ArrayList<NotesType>).add(NotesType.InfoBlock("Infoblock")) //fixme


        //add note button
//        val addNote = currentView.findViewById<Button>(R.id.ll_addNote_nt)
//        addNote.setOnClickListener {
        binding.llAddNoteNt.setOnClickListener {
            val fragment = AddNoteFragment()
            (activity as MainActivity).openFragment(fragment)
        }

        //go to main button
//        val goToMain = currentView.findViewById<Button>(R.id.ll_goToMain_nf)
//        goToMain.setOnClickListener {
        binding.llGoToMainNf.setOnClickListener {
            val fragment = MainFragment()
            (activity as MainActivity).openFragment(fragment)
        }

//        return currentView
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        displayNotes()
    }

    private fun initSharedPreferences() {
        sharedPreferences = MySharedPreferences(context)
    }

    private fun initRecyclerView(currentView: View?) {
        recyclerView = currentView?.findViewById(R.id.fragmentRecyclerView)
        recyclerView?.layoutManager = LinearLayoutManager(currentView?.context)
    }

    private fun displayNotes() {
        lifecycleScope.launch(Dispatchers.IO) {
            loadNotesFromPreferences()
            addNoteToListOfItems()
            launch(Dispatchers.Main) {
                recyclerView?.adapter = AdapterClass(listOfItems) { id, command ->
                when (command) {
                    "delete" -> deleteNoteAndRefresh(id)
                    "share" -> shareNote(id)
                }
            } }

        }

    }

    private fun deleteNoteAndRefresh(id: Int) {   //fixme
        deleteFromDB(noteList?.get(id-1))
        noteList?.removeAt(id - 1)
        addNoteToListOfItems()
        return
    }

    private fun deleteFromDB(note: Note?) {

        lifecycleScope.launch(Dispatchers.IO) {

            if (note != null) {
                RoomObject.deleteFromDB(note)
            }

        }

    }

    private fun shareNote(id: Int) {
        val note = noteList?.get(id)
        val intentShareNote: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(
                Intent.EXTRA_TEXT,
                "Title: ${note?.title}\ncom.example.tms.domain.models.Note: ${note?.content}\nDate: ${note?.noteDate}"
            )
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(intentShareNote, null)
        startActivity(shareIntent)

    }

    private fun addNoteToListOfItems() {

        listOfItems?.clear()
        listOfItems?.add(NotesType.InfoBlock("Infoblock"))
        for (i in 0..<noteList?.size!!) {
            val note = noteList!![i]
            listOfItems?.add(
                NotesType.Note(
                    note.title,
                    note.content,
                    note.noteDate
                )
            )
        }
    }

    private suspend fun loadNotesFromPreferences() {

        noteList = RoomObject.getFromDB()

    }

}
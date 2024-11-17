package com.example.tms.data.storage

import com.example.tms.domain.models.Note
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

class MySharedPreferences(context: Context?) {

    private val prefsName: String = "NotePrefs"
    private val keyNoteCount: String = "NoteCount"
    private var noteList: MutableList<Note> = ArrayList()

    private val sharedPreferences = context?.getSharedPreferences(
        prefsName,
        MODE_PRIVATE
    )

    private val editor: SharedPreferences.Editor = sharedPreferences?.edit()!!
    private val noteCount = sharedPreferences?.getInt(keyNoteCount, 0)

    fun saveToPreferences(noteList: MutableList<Note>) {

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

//        editor.putString("note_title_$noteCount", title)
//        editor.putString("note_content_$noteCount", content)
//        editor.putString("note_date_$noteCount", Calendar.getInstance().time.toString())
//
//        editor.putInt(keyNoteCount, noteCount?.plus(1)!! )
//
//        editor.apply()
    }

    fun loadFromPreferences(): MutableList<Note> {

        noteList = ArrayList()

        for (i in 0..<noteCount!!) {
            val title = sharedPreferences?.getString("note_title_$i", "")
            val content = sharedPreferences?.getString("note_content_$i", "")
            val noteDate = sharedPreferences?.getString("note_date_$i", "")

            val note = Note(title, content, noteDate)

            noteList.add(note)

        }

        return noteList
    }

}
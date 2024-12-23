package com.example.tms.data.storage

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.example.tms.domain.models.Note

object SharedPreferencesObject {

    private var sharedPreferences: SharedPreferences? = null
    private const val prefsName: String = "NotePrefs"
    private const val keyNoteCount: String = "NoteCount"
    private var noteList: MutableList<Note> = ArrayList()

    private val editor: SharedPreferences.Editor? = sharedPreferences?.edit()
    private val noteCount = sharedPreferences?.getInt(keyNoteCount, 0)

    fun initDB(context: Application){
        sharedPreferences = context.getSharedPreferences(
            prefsName,
            MODE_PRIVATE
        )
    }

    fun saveToPreferences(noteList: MutableList<Note>) {

        editor?.putInt(
            keyNoteCount,
            noteList.size
        )
        for (i in 0..<noteList.size) {
            val note = noteList[i]
            editor?.putString("note_title_$i", note.title)
            editor?.putString("note_content_$i", note.content)
            editor?.putString("note_date_$i", note.noteDate)
        }
        editor?.apply()

    }

    fun loadFromPreferences(): MutableList<Note> {

        noteList = ArrayList()

        for (i in 0..<noteCount!!) {
            val title = sharedPreferences?.getString("note_title_$i", "")
            val content = sharedPreferences?.getString("note_content_$i", "")
            val noteDate = sharedPreferences?.getString("note_date_$i", "")

            val note = Note(1, title, content, noteDate)

            noteList.add(note)

        }

        return noteList
    }

    fun addElementsToPreferences(title: String, content: String, noteDate: String) {

        editor?.putString("note_title_$noteCount", title)
        editor?.putString("note_content_$noteCount", content)
        editor?.putString("note_date_$noteCount", noteDate)

        val newNoteCount = noteCount?.plus(1)

        editor?.putInt(
            keyNoteCount,
            newNoteCount!!
        )

        editor?.apply()
    }

    fun putLoginPassToPreferences(login: String, password: String) {

        editor?.putInt(
            keyNoteCount,
            1
        )

        editor?.putString("login_$keyNoteCount", login)
        editor?.putString("password_$keyNoteCount", password)

        editor?.apply()
    }

    fun getLoginPassFromPreferences(): List<String?> {

        val login = sharedPreferences?.getString("login_$keyNoteCount", "")
        val password = sharedPreferences?.getString("password_$keyNoteCount", "")

        return listOf(login, password)
    }

}
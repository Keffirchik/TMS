package com.example.tms.data.storage

import com.example.tms.domain.models.Note

interface SharedPreferencesInterface {

    fun saveToPreferences(noteList: MutableList<Note>)

    fun loadFromPreferences(): MutableList<Note>

    fun addElementsToPreferences(title: String, content: String, noteDate: String)

    fun putLoginPassToPreferences(login: String, password: String)

    fun getLoginPassFromPreferences(): List<String?>
}
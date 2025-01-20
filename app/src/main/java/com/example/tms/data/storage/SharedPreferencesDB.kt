package com.example.tms.data.storage

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

class SharedPreferencesDB(val context: Context) {

    private var sharedPreferences: SharedPreferences? = null
    private val prefsName: String = "NotePrefs"
    private val keyNoteCount: String = "NoteCount"
    private val editor: SharedPreferences.Editor? = sharedPreferences?.edit()

    fun getInstance(): SharedPreferences {
        sharedPreferences = context.getSharedPreferences(
            prefsName,
            MODE_PRIVATE
        )
        return sharedPreferences!!
    }

    fun putLoginPassToPreferences(login: String, password: String) {

        editor?.putInt(
            keyNoteCount,
            1
        )

        editor?.putString("login_${keyNoteCount}", login)
        editor?.putString("password_${keyNoteCount}", password)

        editor?.apply()
    }

    fun getLoginPassFromPreferences(): List<String?> {

        val login = sharedPreferences?.getString("login_${keyNoteCount}", "")
        val password = sharedPreferences?.getString("password_${keyNoteCount}", "")

        return listOf(login, password)
    }

}
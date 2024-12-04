package com.example.tms.data.storage

import android.app.Application
import androidx.room.Room
import com.example.tms.domain.models.Note

object RoomObject {

    private var bd: RoomDB? = null

    fun initDB(context: Application){
        bd = Room.databaseBuilder(context, RoomDB::class.java, "MyDataBase").build()
    }

    fun putInDB(note: Note) {
        bd?.noteDao()?.putNote(note)
    }

    fun getFromDB(): MutableList<Note>? {
        return bd?.noteDao()?.getNotes()
    }

    fun deleteFromDB(note: Note) {
        bd?.noteDao()?.deleteNote(note)
    }
}
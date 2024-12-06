package com.example.tms.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tms.domain.models.Note
import com.example.tms.domain.models.NoteDao

@Database(entities = [Note::class], version = 1)
abstract class RoomDB: RoomDatabase() {
    abstract fun noteDao(): NoteDao
}
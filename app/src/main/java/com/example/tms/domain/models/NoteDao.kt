package com.example.tms.domain.models

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun putNote(note:Note)

    @Query("SELECT * FROM note")
    fun getNote(): MutableList<Note>
}
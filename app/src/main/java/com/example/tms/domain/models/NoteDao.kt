package com.example.tms.domain.models

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tms.presentation.model.NotesType

@Dao
interface NoteDao {

    @Insert(entity = Note::class, onConflict = OnConflictStrategy.REPLACE)
    fun putNote(note:Note)

    @Query("SELECT * FROM note")
    fun getNote(): MutableList<Note>
}
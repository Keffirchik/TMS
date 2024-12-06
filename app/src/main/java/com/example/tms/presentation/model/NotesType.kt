package com.example.tms.presentation.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

sealed interface NotesType {

    @Entity
    data class InfoBlock(
        val info: String
    ) : NotesType

    @Entity
    data class Note(
//        @PrimaryKey val id: Int,
        @ColumnInfo("title") val title: String?,
        @ColumnInfo("content") val content: String?,
        @PrimaryKey val date: String?
//        @ColumnInfo("date") val date: String?
    ) : NotesType
}
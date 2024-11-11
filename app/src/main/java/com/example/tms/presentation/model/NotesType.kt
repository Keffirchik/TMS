package com.example.tms.presentation.model

sealed interface NotesType {
    data class InfoBlock(
        val info: String
    ) : NotesType

    data class Note(
        val title: String?,
        val content: String?,
        val date: String?
    ) : NotesType
}
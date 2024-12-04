package com.example.tms.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Note(@PrimaryKey(true) val id: Int = 0, var title: String?, var content: String?, var noteDate: String?) {

}
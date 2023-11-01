package com.example.notesapp.database.model


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_notes")
data class Note(
    var title: String,
    var content: String,
) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
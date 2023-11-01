package com.example.notesapp.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.notesapp.database.model.Note


@Dao
interface NoteDao {
    @Insert
    suspend fun insert(note: Note)

    @Query("SELECT * FROM tb_notes")
    fun getAllNotes(): kotlinx.coroutines.flow.Flow<List<Note>>

    @Query("DELETE FROM tb_notes WHERE id =:id")
    suspend fun deleteById(id: Long): Unit
}
package com.example.notesapp.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.notesapp.database.model.Note


@Dao
interface NoteDao {
    @Insert
    suspend fun insert(note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("SELECT * FROM tb_notes")
    fun getAllNotes(): kotlinx.coroutines.flow.Flow<List<Note>>

    @Query("SELECT * FROM tb_notes WHERE title LIKE :query OR content LIKE :query")
    fun searchNote(query: String?): LiveData<List<Note>>
}
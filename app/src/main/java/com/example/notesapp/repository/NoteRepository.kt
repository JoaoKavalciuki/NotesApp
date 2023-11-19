package com.example.notesapp.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.notesapp.database.daos.NoteDao
import com.example.notesapp.database.model.Note

class NoteRepository(private val notesDao: NoteDao) {
    val allNotes: kotlinx.coroutines.flow.Flow<List<Note>> = notesDao.getAllNotes()

     fun searchedNote(query: String?): LiveData<List<Note>> = notesDao.searchNote(query)

    @Suppress("RedundantSuspndModifier")
    @WorkerThread
    suspend fun insertNote(note: Note) = notesDao.insert(note)

    @Suppress("RedundantSuspndModifier")
    @WorkerThread
    suspend fun updateNote(note: Note) = notesDao.updateNote(note)

    @Suppress("RedundantSuspndModifier")
    @WorkerThread
    suspend fun deleteNote(note: Note) = notesDao.deleteNote(note)
}
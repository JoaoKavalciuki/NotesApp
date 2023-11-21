package com.example.notesapp.repository

import androidx.annotation.WorkerThread
import com.example.notesapp.database.daos.NoteDao
import com.example.notesapp.database.model.Note

class NoteRepository(private val notesDao: NoteDao) {
    val allNotes: kotlinx.coroutines.flow.Flow<List<Note>> = notesDao.getAllNotes()

    @Suppress("RedundantSuspndModifier")
    @WorkerThread
    suspend fun insert(note: Note) = notesDao.insert(note)
}
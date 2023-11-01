package com.example.notesapp.repository

import com.example.notesapp.database.daos.NoteDao
import com.example.notesapp.database.model.Note

class NoteRepository(private val notesDao: NoteDao) {
    val allNotes: kotlinx.coroutines.flow.Flow<List<Note>> = notesDao.getAllNotes()

    suspend fun insert(note: Note) = notesDao.insert(note)
}
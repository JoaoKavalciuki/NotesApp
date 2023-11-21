package com.example.notesapp.application

import android.app.Application
import com.example.notesapp.database.NotesDatabase
import com.example.notesapp.repository.NoteRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class NotesApplication: Application() {
    val applicationCoroutine = CoroutineScope(SupervisorJob())

    val database by lazy {NotesDatabase.getInstance( applicationCoroutine,this)}
    val repository by lazy { NoteRepository(database.noteDao()) }
}
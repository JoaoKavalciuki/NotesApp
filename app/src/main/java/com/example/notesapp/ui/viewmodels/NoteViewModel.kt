package com.example.notesapp.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.notesapp.database.model.Note
import com.example.notesapp.repository.NoteRepository
import kotlinx.coroutines.launch

class NoteViewModel(private val repository: NoteRepository): ViewModel() {

    val allNotes : LiveData<List<Note>> = repository.allNotes.asLiveData()
    fun  searchedNote(query: String?): LiveData<List<Note>> = repository.searchedNote(query)
    fun insert(note: Note) = viewModelScope.launch{
        repository.insertNote(note)

    }
    fun updateNote(note: Note) = viewModelScope.launch {
        repository.updateNote(note)
    }

    fun deleteNote(note: Note) = viewModelScope.launch {
        repository.deleteNote(note)
    }
}

class NoteViewModelFactory(private val repository: NoteRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(NoteViewModel::class.java)){
            return NoteViewModel(repository) as T
        }

        throw IllegalArgumentException("View Model solicitada não existe")
    }
}
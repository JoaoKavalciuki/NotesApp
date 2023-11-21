package com.example.notesapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.application.NotesApplication
import com.example.notesapp.databinding.ActivityMainBinding
import com.example.notesapp.ui.adapters.NoteListAdapter
import com.example.notesapp.ui.viewmodels.NoteViewModel
import com.example.notesapp.ui.viewmodels.NoteViewModelFactory
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notesapp.contracts.PassNoteDataContract
import com.example.notesapp.database.model.Note
import com.example.notesapp.ui.view.NewNoteActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    private val noteViewModel: NoteViewModel by viewModels{
        NoteViewModelFactory((application as NotesApplication).repository)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val resultLauncher = registerForActivityResult(PassNoteDataContract()) {
            result ->
            if(result != null){
                val (noteTitle, noteContent) = result
                noteViewModel.insert(Note(noteTitle, noteContent))
            }
        }

        this.binding.btnAddNota.setOnClickListener {
            Log.d("Contract", "BTN clicado")
            resultLauncher.launch(Unit)
        }

    }


    override fun onResume() {
        super.onResume()
        noteViewModel.allNotes.observe(this,
            {notes ->
                this.binding.rvNotes.setHasFixedSize(true)
                this.binding.rvNotes.apply {

                    layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                    adapter =
                        NoteListAdapter().apply {
                            setData(notes)
                        }
            }
        })
    }
}
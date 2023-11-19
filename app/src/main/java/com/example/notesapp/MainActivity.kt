package com.example.notesapp


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.notesapp.application.NotesApplication
import com.example.notesapp.databinding.ActivityMainBinding
import com.example.notesapp.ui.viewmodels.NoteViewModel
import com.example.notesapp.ui.viewmodels.NoteViewModelFactory
import androidx.activity.viewModels
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    val noteViewModel: NoteViewModel by viewModels{
        NoteViewModelFactory((application as NotesApplication).repository)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

    }

}
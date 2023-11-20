package com.example.notesapp.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.notesapp.MainActivity
import com.example.notesapp.R
import com.example.notesapp.database.model.Note
import com.example.notesapp.databinding.FragmentNewNoteBinding
import com.example.notesapp.ui.viewmodels.NoteViewModel
import com.google.android.material.snackbar.Snackbar

class NewNoteFragment : Fragment(R.layout.fragment_new_note) {

    private lateinit var binding: FragmentNewNoteBinding
    private lateinit var noteViewModel: NoteViewModel
    private lateinit var mView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        noteViewModel = (activity as MainActivity).noteViewModel
        mView = view

        binding.fabSaveNote.setOnClickListener {
            saveNote(mView)
        }
    }

    private fun saveNote(view: View){
        val errorMessage = "Este campo precisa estar preenchido"
        if (binding.etTitle.text.isBlank()) {
            binding.etTitle.error = errorMessage
        }else {
            val title = binding.etTitle.text.toString().trim()
            val content = binding.etContent.text.toString()

            noteViewModel.insert(Note(0, title, content))

            Snackbar.make(view, "Nota salva com sucesso", Snackbar.LENGTH_SHORT).show()
            view.findNavController().navigate(R.id.action_newNoteFragment_to_homeFragment)
        }

    }
}
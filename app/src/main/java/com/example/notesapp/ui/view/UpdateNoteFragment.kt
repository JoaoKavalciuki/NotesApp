package com.example.notesapp.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.notesapp.MainActivity
import com.example.notesapp.R
import com.example.notesapp.database.model.Note
import com.example.notesapp.databinding.FragmentUpdateNoteBinding
import com.example.notesapp.ui.viewmodels.NoteViewModel

class UpdateNoteFragment : Fragment(R.layout.fragment_update_note) {

    private lateinit var binding: FragmentUpdateNoteBinding
    private lateinit var viewModel: NoteViewModel

    private val args : UpdateNoteFragmentArgs by navArgs()
    private lateinit var currentNote: Note

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
        binding = FragmentUpdateNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).noteViewModel

        currentNote = args.note!!
        binding.etTitleUpdate.setText(currentNote.title)
        binding.etContentUpdate.setText(currentNote.content)

        binding.fabUpdateNote.setOnClickListener {
            val errorMessage = "Este campo precisa estar preenchido"
            if(binding.etTitleUpdate.text.isBlank()) binding.etTitleUpdate.error = errorMessage
            val title = binding.etTitleUpdate.text.toString().trim()
            val content = binding.etContentUpdate.text.toString().trim()
            viewModel.updateNote(Note(currentNote.id, title, content))

            view.findNavController().navigate(R.id.action_updateNoteFragment_to_homeFragment)
        }
    }

}
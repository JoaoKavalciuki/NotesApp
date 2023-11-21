package com.example.notesapp.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.notesapp.R
import com.example.notesapp.contracts.PassNoteDataContract
import com.example.notesapp.databinding.ActivityNewNoteBinding

class NewNoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityNewNoteBinding.inflate(layoutInflater)
        setContentView(this.binding.root)
        Log.d("NewNote", "On create chamado")
    }

    override fun onStart() {
        super.onStart()

        binding.btnSalvarNota.setOnClickListener {
            Log.d("NewNote", "btnSalvarNota chamado")
            val errorMessage = "Este campo precisa estar preenchido"
            if (binding.etTitle.text.isBlank()) {
                binding.etTitle.error = errorMessage
                return@setOnClickListener
            }
            if (binding.etContent.text.isBlank()) {
                binding.etContent.error = errorMessage
                return@setOnClickListener
            }

            val resultIntent = Intent().apply {
                val title = binding.etTitle.text.toString()
                val content = binding.etContent.text.toString()
                putExtra(PassNoteDataContract.TITLE_EXTRA, title)
                putExtra(PassNoteDataContract.CONTENT_EXTRA, content)
            }

            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }
}
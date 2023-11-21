package com.example.notesapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.R
import com.example.notesapp.database.model.Note

class NoteListAdapter(): RecyclerView.Adapter<NoteListAdapter.NoteViewHolder>() {

    var notesList = mutableListOf<Note>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_rv_notes, parent, false)
        return NoteViewHolder(view)
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
       val note = notesList[position]
        holder.bind(note.title, note.content)
    }

    fun setData(newNote:  List<Note>){
        notesList = newNote.toMutableList()
        notifyDataSetChanged()
    }

    class NoteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val noteTitle: TextView = itemView.findViewById(R.id.tvTitle)
        private val noteContent: TextView = itemView.findViewById(R.id.tvContent)

        fun bind(title: String?, content: String?){
            noteTitle.text = title
            noteContent.text = content
        }

        companion object{
            fun create(parent: ViewGroup): NoteViewHolder{
                val view : View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_rv_notes, parent, false)
                return NoteViewHolder(view)
            }
        }
    }
}
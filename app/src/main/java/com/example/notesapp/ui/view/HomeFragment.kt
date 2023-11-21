package com.example.notesapp.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.room.Query
import com.example.notesapp.MainActivity
import com.example.notesapp.R
import com.example.notesapp.database.model.Note
import com.example.notesapp.databinding.FragmentHomeBinding
import com.example.notesapp.ui.adapters.NoteListAdapter
import com.example.notesapp.ui.viewmodels.NoteViewModel

class HomeFragment : Fragment(R.layout.fragment_home), SearchView.OnQueryTextListener {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var noteViewModel: NoteViewModel
    private lateinit var noteAdapter: NoteListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        noteViewModel = (activity as MainActivity).noteViewModel
        setUpRecyclerView()

        val menuHost: MenuHost = requireActivity()

        menuHost.addMenuProvider(object: MenuProvider{
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menu.clear()
                menuInflater.inflate(R.menu.home_menu, menu)

                val mMenuSearch = menu.findItem(R.id.btnSearch).actionView as SearchView
                mMenuSearch.isSubmitButtonEnabled = true
                mMenuSearch.setOnQueryTextListener(this@HomeFragment)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return true
            }

        })

        binding.btnAddNote.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_newNoteFragment)
        }
    }

    override fun onPause() {
        super.onPause()

    }

    override fun onDestroy() {
        super.onDestroy()
    }

    fun setUpRecyclerView(){
        noteAdapter = NoteListAdapter()
        binding.rvNotes.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)


            adapter = noteAdapter
        }

        activity?.let {
            noteViewModel.allNotes.observe(viewLifecycleOwner, {
                notes ->
                noteAdapter.setData(notes)
                updateUI(notes)
            })
        }
    }

    fun updateUI(notes: List<Note>) {
        if(notes.isEmpty()){
            binding.rvNotes.visibility = View.GONE
            binding.cvHomeFragment.visibility = View.VISIBLE
        } else {

            binding.rvNotes.visibility = View.VISIBLE
            binding.cvHomeFragment.visibility = View.GONE
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if(query != null){
            searchNote(query)
        }


        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if(newText != null){
            searchNote(newText)
        }
        return true
    }

    private fun searchNote(query: String?){
        val searchQuery = "%$query%"

        noteViewModel.searchedNote(searchQuery).observe(this, {
            noteList ->
            noteAdapter.setData(noteList)
        })
    }
}
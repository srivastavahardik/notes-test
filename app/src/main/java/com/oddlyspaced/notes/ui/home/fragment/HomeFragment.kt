package com.oddlyspaced.notes.ui.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.oddlyspaced.notes.R
import com.oddlyspaced.notes.databinding.FragmentHomeBinding
import com.oddlyspaced.notes.ui.home.adapter.NotesAdapter
import com.oddlyspaced.notes.ui.home.viewmodel.HomeViewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewmodel: HomeViewModel

    private lateinit var adapter: NotesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewmodel = ViewModelProvider(this).get(HomeViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        setupClickListeners()
        setupRecyclerView()
        fetchNoteList()

        return binding.root
    }

    private fun setupClickListeners() {
        binding.fabHomeAdd.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(HomeFragmentDirections.homeToNote().apply {
                notejson = ""
            })
        }
    }

    private fun setupRecyclerView() {
        binding.rvHomeHeader.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        adapter = NotesAdapter(this) { position ->
            viewmodel.delete(adapter.currentList[position].id)
            val list = ArrayList(adapter.currentList)
            list.removeAt(position)
            adapter.submitList(list)
//            fetchNoteList()
        }
        binding.rvHomeHeader.adapter = adapter
    }

    private fun fetchNoteList() {
        viewmodel.notes.observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })
    }

    // TODO : Replace this with a better implementation of checking updates
    override fun onResume() {
        super.onResume()
        fetchNoteList()
    }
}
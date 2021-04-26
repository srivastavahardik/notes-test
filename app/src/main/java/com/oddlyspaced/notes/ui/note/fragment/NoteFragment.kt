package com.oddlyspaced.notes.ui.note.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.oddlyspaced.notes.R
import com.oddlyspaced.notes.databinding.FragmentNoteBinding
import com.oddlyspaced.notes.modal.Item
import com.oddlyspaced.notes.ui.home.adapter.ItemsAdapter
import com.oddlyspaced.notes.ui.home.adapter.NotesAdapter
import com.oddlyspaced.notes.ui.note.viewmodel.NoteViewModel
import com.oddlyspaced.notes.ui.note.viewmodel.NoteViewModelFactory

class NoteFragment: Fragment() {

    private lateinit var binding: FragmentNoteBinding
    private lateinit var viewmodel: NoteViewModel

    private lateinit var adapter: ItemsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewmodel = NoteViewModelFactory(NoteFragmentArgs.fromBundle(requireArguments()).notejson).create(NoteViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_note, container, false)

        setupRecyclerView()
        setupObservers()
        setupClickListeners()

        return binding.root
    }

    private fun setupClickListeners() {
        binding.cvEditSave.setOnClickListener {
            if (binding.etTitle.text.isEmpty() && viewmodel.isEditing.value == true) {
                Toast.makeText(context, "Title cannot be empty!", Toast.LENGTH_SHORT).show()
            }
            else {
                viewmodel.toggleEditing()
            }
        }

        binding.fabAddItem.setOnClickListener {
            viewmodel.addItem("Sample Item")
        }
    }

    private fun setupRecyclerView() {
        binding.rvItems.layoutManager = LinearLayoutManager(context)
        adapter = ItemsAdapter()
        binding.rvItems.adapter = adapter
    }

    private fun setupObservers() {
        binding.etTitle.addTextChangedListener {
            viewmodel.updateTitle(it.toString())
        }

        viewmodel.title.observe(viewLifecycleOwner, { title ->
            binding.apply {
                txTitle.text = title
            }
        })

        setupContentObserver()

        // TODO : Use data binding
        viewmodel.isEditing.observe(viewLifecycleOwner, { editing ->
            binding.apply {
                etTitle.setText(viewmodel.title.value)
                etTitle.isVisible = editing
                txTitle.isVisible = !editing

                etContent.isVisible = editing
                txContent.isVisible = !editing

                imgIcon.setImageResource(if (editing) R.drawable.ic_save else R.drawable.ic_edit)
            }

            if (!editing) {
                if ((viewmodel.title.value ?: "").isNotEmpty()) {
                    viewmodel.updateNote()
                }
            }
        })
    }

    private fun setupContentObserver() {
        viewmodel.content.observe(viewLifecycleOwner, { items ->
            when (items.size) {
                0 -> {
                    binding.consEmptyAdd.setOnClickListener {
                        viewmodel.toggleEditing()
                        binding.consEmptyAdd.isVisible = false
                        binding.consSingleItem.isVisible = true
                    }
                }
                1 -> {
                    binding.consEmptyAdd.isVisible = false
                    binding.consSingleItem.isVisible = true
                    binding.rvItems.isVisible = false
                }
                else -> {
                    binding.consEmptyAdd.isVisible = false
                    binding.consSingleItem.isVisible = false
                    binding.rvItems.isVisible = true
                    adapter.submitList(ArrayList(items))
                }
            }
        })
    }

}
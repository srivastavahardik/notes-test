package com.oddlyspaced.notes.ui.note.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.oddlyspaced.notes.R
import com.oddlyspaced.notes.databinding.FragmentNoteBinding
import com.oddlyspaced.notes.ui.note.viewmodel.NoteViewModel
import com.oddlyspaced.notes.ui.note.viewmodel.NoteViewModelFactory

class NoteFragment: Fragment() {

    private lateinit var binding: FragmentNoteBinding
    private lateinit var viewmodel: NoteViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewmodel = NoteViewModelFactory(1).create(NoteViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_note, container, false)

        setupObservers()
        setupClickListeners()

        return binding.root
    }

    private fun setupClickListeners() {
        binding.cvEditSave.setOnClickListener {
            viewmodel.toggleEditing()
        }
    }

    private fun setupObservers() {
        viewmodel.note.observe(viewLifecycleOwner, { completeNote ->
            completeNote.note.let { note ->
                binding.txTitle.text = note.title
                binding.etTitle.setText(note.title)
                binding.txDate.text = note.date
            }

            binding.txContent.text = ""
            binding.etContent.setText("")
            completeNote.content.let { contentList ->
                if (contentList.isNotEmpty()) {
                    binding.txContent.text = contentList.first().content
                    binding.etContent.setText(contentList.first().content)
                }
            }
        })

        // TODO : Use data binding
        viewmodel.isEditing.observe(viewLifecycleOwner, { editing ->
            binding.apply {
                etTitle.isVisible = editing
                txTitle.isVisible = !editing

                etContent.isVisible = editing
                txContent.isVisible = !editing
            }
        })
    }


}
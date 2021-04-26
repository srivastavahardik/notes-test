package com.oddlyspaced.notes.ui.note.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        return binding.root
    }

    private fun setupObservers() {
        viewmodel.note.observe(viewLifecycleOwner, { completeNote ->
            completeNote.note.let { note ->
                binding.txTitle.setText(note.title)
                binding.txDate.text = note.date
            }

            binding.txContent.setText("")
            completeNote.content.let { contentList ->
                if (contentList.isNotEmpty()) {
                    binding.txContent.setText(contentList.first().content)
                }
            }
        })
    }


}
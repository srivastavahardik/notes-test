package com.oddlyspaced.notes.ui.note.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
        viewmodel = NoteViewModelFactory(NoteFragmentArgs.fromBundle(requireArguments()).notejson).create(NoteViewModel::class.java)
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
        viewmodel.title.observe(viewLifecycleOwner, { title ->
            binding.apply {
                txTitle.text = title
                etTitle.setText(title)
            }
        })

        // TODO : Use data binding
        viewmodel.isEditing.observe(viewLifecycleOwner, { editing ->
            binding.apply {
                etTitle.isVisible = editing
                txTitle.isVisible = !editing

                etContent.isVisible = editing
                txContent.isVisible = !editing

                imgIcon.setImageResource(if (editing) R.drawable.ic_save else R.drawable.ic_edit)
            }

            if (!editing) {
                // Save
            }
        })
    }


}
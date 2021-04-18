package com.oddlyspaced.notes.ui.note.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.oddlyspaced.notes.R
import com.oddlyspaced.notes.databinding.FragmentNoteBinding

class NoteFragment: Fragment() {

    private lateinit var binding: FragmentNoteBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_note, container, false)
        return binding.root
    }


}
package com.oddlyspaced.notes.ui.note.viewmodel

import androidx.lifecycle.ViewModel
import com.oddlyspaced.notes.repository.NotesRepository

class NoteViewModel(noteID: Int): ViewModel() {

    private val repository = NotesRepository
    val note = repository.fetchNoteInfo(noteID)

}
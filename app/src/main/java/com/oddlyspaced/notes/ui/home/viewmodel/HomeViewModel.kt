package com.oddlyspaced.notes.ui.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.oddlyspaced.notes.modal.Note
import com.oddlyspaced.notes.repository.NotesRepository

class HomeViewModel : ViewModel() {

    private val notesRepository = NotesRepository
    val notes: LiveData<List<Note>> = notesRepository.fetchNotes()

}
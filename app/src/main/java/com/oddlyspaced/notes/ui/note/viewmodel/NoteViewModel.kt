package com.oddlyspaced.notes.ui.note.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oddlyspaced.notes.repository.NotesRepository

class NoteViewModel(noteID: Int): ViewModel() {

    private val repository = NotesRepository
    val note = repository.fetchNoteInfo(noteID)

    private val _isEditing = MutableLiveData<Boolean>()
    val isEditing: LiveData<Boolean>
        get() = _isEditing

    init {
        _isEditing.postValue(false)
    }

    fun toggleEditing() {
        val toggleValue = _isEditing.value?: true
        Log.e("togg", toggleValue.toString())
        _isEditing.postValue(!toggleValue)
    }

}
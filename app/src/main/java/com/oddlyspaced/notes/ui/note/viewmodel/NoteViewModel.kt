package com.oddlyspaced.notes.ui.note.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.oddlyspaced.notes.modal.Item
import com.oddlyspaced.notes.modal.Note

class NoteViewModel(noteJson: String): ViewModel() {

    private val gson = Gson()

    private val _title = MutableLiveData<String>()
    val title: LiveData<String>
        get() = _title

    private val _content = MutableLiveData<List<Item>>()
    val content: LiveData<List<Item>>
        get() = _content

//    val note = MutableLiveData<Note>()

    private val _isEditing = MutableLiveData<Boolean>()
    val isEditing: LiveData<Boolean>
        get() = _isEditing

    init {
        _isEditing.postValue(false)

        val type = object: TypeToken<Note>(){}.type
        val note: Note = gson.fromJson(noteJson, type)

        _title.postValue(note.title)
        _content.postValue(note.content)
    }

    fun toggleEditing() {
        val toggleValue = _isEditing.value?: true
        _isEditing.postValue(!toggleValue)
    }

}
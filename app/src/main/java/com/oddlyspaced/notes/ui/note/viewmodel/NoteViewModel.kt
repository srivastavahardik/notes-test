package com.oddlyspaced.notes.ui.note.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.oddlyspaced.notes.modal.Item
import com.oddlyspaced.notes.modal.Note
import com.oddlyspaced.notes.repository.NotesRepository

class NoteViewModel(noteJson: String): ViewModel() {

    private val gson = Gson()

    private val _title = MutableLiveData<String>()
    val title: LiveData<String>
        get() = _title

    private val _content = MutableLiveData<ArrayList<Item>>()
    val content: LiveData<ArrayList<Item>>
        get() = _content

    private val note: Note = if (noteJson.isEmpty()) {
        Note(
            -1,
            "",
            "2020",
            listOf()
        )
    } else {
        val type = object : TypeToken<Note>() {}.type
        gson.fromJson(noteJson, type)
    }

    private val _isEditing = MutableLiveData<Boolean>()
    val isEditing: LiveData<Boolean>
        get() = _isEditing

    init {
        _title.postValue(note.title)
        _content.postValue(ArrayList(note.content))

        _isEditing.postValue(false)
    }

    fun toggleEditing() {
        val toggleValue = _isEditing.value?: true
        _isEditing.postValue(!toggleValue)
    }

    fun updateTitle(newTitle: String) = _title.postValue(newTitle)

    fun updateNote() {
        val noteCopy = Note(
            note.id,
            title.value ?: "why",
            note.date,
            note.content
        )
        NotesRepository.updateNote(noteCopy)
    }

    fun addItem(content: String) {
        val copy = (_content.value) ?: ArrayList()
        val lastIndex = if (copy.isEmpty()) 0 else copy.last().id
        _content.value?.add(Item(lastIndex + 1, content, false))
        _content.postValue(_content.value)
    }

}
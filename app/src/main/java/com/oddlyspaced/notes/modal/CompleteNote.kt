package com.oddlyspaced.notes.modal

data class CompleteNote(
    val note: Note,
    val content: List<Item>,
)
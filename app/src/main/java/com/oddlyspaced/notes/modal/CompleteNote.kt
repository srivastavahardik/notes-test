package com.oddlyspaced.rdbms.notes.entity

import com.oddlyspaced.notes.modal.Note

data class CompleteNote(
    val note: Note,
    val content: List<Item>,
)
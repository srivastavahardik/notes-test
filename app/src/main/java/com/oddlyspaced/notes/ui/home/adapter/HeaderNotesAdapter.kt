package com.oddlyspaced.notes.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oddlyspaced.notes.databinding.ItemNoteBinding

class HeaderNotesAdapter : RecyclerView.Adapter<HeaderNotesAdapter.NoteViewHolder>() {

    inner class NoteViewHolder(binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(ItemNoteBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 10
    }

}
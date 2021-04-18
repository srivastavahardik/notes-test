package com.oddlyspaced.notes.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oddlyspaced.notes.databinding.ItemNoteBinding

class HeaderNotesAdapter : RecyclerView.Adapter<HeaderNotesAdapter.NoteViewHolder>() {

    inner class NoteViewHolder(binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root) {
        val text = binding.txItemTitle
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
//        return NoteViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false))
        return NoteViewHolder(ItemNoteBinding.inflate(LayoutInflater.from(parent.context)))
    }

    private val tt = listOf(
        "1111111111111111111111",
        "1111111111",
        "1111111111111111111111111111",
        "11111111111",
        "1111111111111111111111111111111111111",
        "1111",
        "12312311"
    )

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.text.text = tt.random()
    }

    override fun getItemCount(): Int {
        return 10
    }

}
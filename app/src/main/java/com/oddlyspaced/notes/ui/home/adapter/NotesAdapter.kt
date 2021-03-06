package com.oddlyspaced.notes.ui.home.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.oddlyspaced.notes.databinding.ItemNoteBinding
import com.oddlyspaced.notes.modal.Note
import com.oddlyspaced.notes.ui.home.fragment.HomeFragmentDirections

class NotesAdapter(private val fragment: Fragment, private val onLongPress: (Int) -> Unit) : ListAdapter<Note, NoteViewHolder>(NotesDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder.from(parent, fragment)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(getItem(position), onLongPress)
    }
}

class NoteViewHolder(private val binding: ItemNoteBinding, private val fragment: Fragment): RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Note, onLongPress: (Int) -> Unit) {
        binding.root.setOnClickListener {
            NavHostFragment.findNavController(fragment).navigate(HomeFragmentDirections.homeToNote().apply {
                notejson = Gson().toJson(item)
            })
        }
        binding.root.setOnLongClickListener {
            Log.e("POSITION", adapterPosition.toString())
            onLongPress(adapterPosition)
            true
        }
        binding.txNoteTitle.text = item.title
        binding.txNoteDate.text = item.date
    }

    companion object {
        fun from(parent: ViewGroup, fragment: Fragment): NoteViewHolder {
            return NoteViewHolder(ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false), fragment)
        }
    }
}

class NotesDiffCallback: DiffUtil.ItemCallback<Note>() {
    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean = (oldItem.id == newItem.id)
    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean = (oldItem == newItem)
}
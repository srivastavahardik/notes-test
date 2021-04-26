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
import com.oddlyspaced.notes.ui.note.fragment.NoteFragment

class NotesAdapter(private val fragment: Fragment) : ListAdapter<Note, NoteViewHolder>(NotesDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder.from(parent, fragment)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        Log.e("BIND","BIND")
        holder.bind(getItem(position))
    }
}

class NoteViewHolder(private val binding: ItemNoteBinding, private val fragment: Fragment): RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Note) {
        binding.root.setOnClickListener {
            NavHostFragment.findNavController(fragment).navigate(HomeFragmentDirections.homeToNote().apply {
                notejson = Gson().toJson(item)
            })
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
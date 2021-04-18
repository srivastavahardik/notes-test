package com.oddlyspaced.notes.ui.home.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.oddlyspaced.notes.databinding.ItemNoteBinding
import com.oddlyspaced.notes.modal.Note

class NotesAdapter : ListAdapter<Note, NotesAdapter.NoteViewHolder>(NotesDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        Log.e("BIND","BIND")
        holder.bind(getItem(position))
    }

    class NoteViewHolder(private val binding: ItemNoteBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Note) {
            binding.txNoteTitle.text = item.title
            binding.txNoteDate.text = item.date
        }

        companion object {
            fun from(parent: ViewGroup): NoteViewHolder {
                return NoteViewHolder(ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            }
        }
    }

//    class ViewHolder(private val binding: ListItemSleepNightBinding) : RecyclerView.ViewHolder(binding.root){
//
//        fun bind(item: SleepNight) {
//            binding.sleep = item
//            binding.executePendingBindings()
//        }
//
//        companion object {
//            fun from(parent: ViewGroup): ViewHolder {
//                val layoutInflater = LayoutInflater.from(parent.context)
//                val binding = ListItemSleepNightBinding.inflate(layoutInflater, parent, false)
//                return ViewHolder(binding)
//            }
//        }
//    }

//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
////        return NoteViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false))
//        return NoteViewHolder(ItemNoteBinding.inflate(LayoutInflater.from(parent.context)))
//    }
//
//    private val tt = listOf(
//        "1111111111111111111111",
//        "1111111111",
//        "1111111111111111111111111111",
//        "11111111111",
//        "1111111111111111111111111111111111111",
//        "1111",
//        "12312311"
//    )
//
//    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
//        holder.text.text = tt.random()
//    }
//
//    override fun getItemCount(): Int {
//        return 10
//    }

}

class NotesDiffCallback: DiffUtil.ItemCallback<Note>() {
    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean = (oldItem.id == newItem.id)
    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean = (oldItem == newItem)
}
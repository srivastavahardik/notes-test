package com.oddlyspaced.notes.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.oddlyspaced.notes.databinding.ItemListCheckBinding
import com.oddlyspaced.notes.modal.Item

class ItemsAdapter: ListAdapter<Item, ItemViewHolder>(ItemDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class ItemViewHolder(private val binding: ItemListCheckBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Item) {
        binding.cbItem.isChecked = item.isDone
        binding.txItem.text = item.content
    }

    companion object {
        fun from(parent: ViewGroup): ItemViewHolder {
            return ItemViewHolder(ItemListCheckBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }
    }
}

class ItemDiffCallback: DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean = (oldItem.id == newItem.id)
    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean = (oldItem == newItem)
}
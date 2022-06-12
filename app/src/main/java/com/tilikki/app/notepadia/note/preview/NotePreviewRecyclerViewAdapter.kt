package com.tilikki.app.notepadia.note.preview

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.tilikki.app.notepadia.databinding.NotePreviewRecyclerViewItemBinding
import com.tilikki.app.notepadia.models.Note

class NotePreviewRecyclerViewAdapter :
  ListAdapter<Note, NotePreviewRecyclerViewHolder>(DiffCallback) {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotePreviewRecyclerViewHolder {
    return NotePreviewRecyclerViewHolder(getRecyclerViewItemBinding(parent))
  }

  private fun getRecyclerViewItemBinding(parent: ViewGroup): NotePreviewRecyclerViewItemBinding {
    val inflater = LayoutInflater.from(parent.context)
    return NotePreviewRecyclerViewItemBinding.inflate(inflater, parent, false)
  }

  override fun onBindViewHolder(holder: NotePreviewRecyclerViewHolder, position: Int) {
    val item = getItem(position)
    holder.bind(item)
  }

  object DiffCallback : DiffUtil.ItemCallback<Note>() {
    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
      return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
      return oldItem.uuid == newItem.uuid
    }
  }

  companion object {
    fun getGridLayoutManager(): RecyclerView.LayoutManager {
      val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
      layoutManager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
      return layoutManager
    }

    fun getListLayoutManager(context: Context): RecyclerView.LayoutManager {
      return LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }
  }

}
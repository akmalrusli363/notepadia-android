package com.tilikki.app.notepadia.note.preview

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import com.tilikki.app.notepadia.databinding.NotePreviewRecyclerViewItemBinding
import com.tilikki.app.notepadia.models.Note
import com.tilikki.app.notepadia.models.NoteColor
import com.tilikki.app.notepadia.note.NoteEditorActivity

class NotePreviewRecyclerViewHolder(private val itemBinding: NotePreviewRecyclerViewItemBinding) :
  RecyclerView.ViewHolder(itemBinding.root) {

  fun bind(note: Note) {
    note.color?.let { bindNoteColor(it) }
    itemBinding.tvTitle.text = note.title
    itemBinding.tvContents.text = note.content
    itemBinding.root.setOnClickListener { view ->
      openNoteEditor(view.context, note)
    }
  }

  private fun openNoteEditor(context: Context, note: Note) {
    val intent = Intent(context, NoteEditorActivity::class.java)
    intent.putExtra(NoteEditorActivity.INTENT_URL, note)
    context.startActivity(intent)
  }

  private fun bindNoteColor(noteColor: NoteColor) {
    val textColor = noteColor.textColor
    itemBinding.tvContents.setTextColor(textColor.rgbHex)
    itemBinding.tvTitle.setTextColor(textColor.rgbHex)
    itemBinding.clNoteView.setBackgroundColor(noteColor.background.rgbHex)
  }

}
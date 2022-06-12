package com.tilikki.app.notepadia.note

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.tilikki.app.notepadia.databinding.ActivityNoteEditorBinding
import com.tilikki.app.notepadia.models.Note

class NoteEditorActivity : AppCompatActivity() {

  private lateinit var binding: ActivityNoteEditorBinding

  private lateinit var viewModel: NoteEditorViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    viewModel = ViewModelProvider(this).get(NoteEditorViewModel::class.java)
    binding = ActivityNoteEditorBinding.inflate(layoutInflater)

    setContentView(binding.root)
    setSupportActionBar(binding.toolbar)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)

    val note = intent.getParcelableExtra<Note>(INTENT_URL)
    viewModel.initializeNoteEditor(note)

    viewModel.note.observe(this) { vmNote ->
      supportFragmentManager.beginTransaction()
        .replace(binding.fragmentContainer.id, NoteEditorFragment.newInstance(vmNote)).commit()
    }
  }

  override fun onBackPressed() {
    super.onBackPressed()
    viewModel.updateNote()
    Toast.makeText(
      applicationContext,
      "edited -> ${viewModel.note.value?.title} $ ${viewModel.note.value?.content}",
      Toast.LENGTH_SHORT
    ).show() //    viewModel.saveToDb()
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      android.R.id.home -> onBackPressed()
      else -> super.onOptionsItemSelected(item)
    }
    return true
  }

  companion object {
    const val INTENT_URL: String = "com.tilikki.app.notepadia.NoteProperties"
  }
}
package com.tilikki.app.notepadia.main

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.ui.AppBarConfiguration
import com.tilikki.app.notepadia.R
import com.tilikki.app.notepadia.databinding.ActivityMainBinding
import com.tilikki.app.notepadia.models.BaseColor
import com.tilikki.app.notepadia.models.Note
import com.tilikki.app.notepadia.models.NoteColor
import com.tilikki.app.notepadia.note.NoteEditorActivity
import com.tilikki.app.notepadia.note.preview.NotePreviewRecyclerViewAdapter

class MainActivity : AppCompatActivity() {

  private lateinit var appBarConfiguration: AppBarConfiguration
  private lateinit var binding: ActivityMainBinding
  private lateinit var viewModel: MainActivityViewModel

  private enum class NoteLayout {
    LIST_VIEW, GRID_VIEW;
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)
    setSupportActionBar(binding.toolbar)

    initializeActivity()
    binding.fab.setOnClickListener { view ->
      val intent = Intent(this, NoteEditorActivity::class.java)
      startActivity(intent)
    }
  }

  private fun initializeActivity() {
    binding.content.rvNoteList.apply {
      adapter = NotePreviewRecyclerViewAdapter()
      layoutManager = NotePreviewRecyclerViewAdapter.getGridLayoutManager()
      setHasFixedSize(true)
    }
    binding.content.ibGridView.setOnClickListener {
      setPreviewerLayout(NoteLayout.GRID_VIEW)
    }
    binding.content.ibListView.setOnClickListener {
      setPreviewerLayout(NoteLayout.LIST_VIEW)
    }
    viewModel.notes.observe(this) { noteList ->
      (binding.content.rvNoteList.adapter as NotePreviewRecyclerViewAdapter).submitList(noteList)
    }
    initializeList()
    viewModel.getNoteList()
  }

  private fun setPreviewerLayout(noteLayout: NoteLayout) {
    binding.content.rvNoteList.layoutManager = when (noteLayout) {
      NoteLayout.LIST_VIEW -> NotePreviewRecyclerViewAdapter.getListLayoutManager(baseContext)
      NoteLayout.GRID_VIEW -> NotePreviewRecyclerViewAdapter.getGridLayoutManager()
    }
  }

  private fun initializeList() {
    viewModel.getNoteList()
  }

  private fun initializeMockList(): List<Note> {
    return listOf(
      Note(
        "Hello world", "Lorem ipsum dolor sit amet", NoteColor(Color.YELLOW, Color.BLACK)
      ),
      Note(
        "Lorem Ipsum", "This is another note :)", NoteColor(Color.MAGENTA, Color.WHITE)
      ),
      Note(
        "Longgger text",
        "This is a note who written very very very very very long text :)",
        NoteColor(Color.GRAY, Color.WHITE)
      ),
      Note("Ipsum", "Lorem ipsum dolor", NoteColor(Color.RED, Color.BLACK)),
      Note(
        "Lorem\nIpsum", "This\nis\nanother\nnewline\nnote :)", NoteColor(Color.WHITE, Color.RED)
      ),
      Note("Lorem Ipsum", "Torem :)", NoteColor(Color.DKGRAY, Color.WHITE)),
      Note("", "MAMAMIA LEZATOS", NoteColor(BaseColor("#C0DEBE"), Color.BLACK)),
      Note("untitled", "", NoteColor(Color.BLUE, BaseColor("#acebee"))),
    )
  }

  override fun onCreateOptionsMenu(menu: Menu): Boolean { // Inflate the menu; this adds items to the action bar if it is present.
    menuInflater.inflate(R.menu.menu_main, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean { // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    return when (item.itemId) {
      R.id.action_settings -> true
      else -> super.onOptionsItemSelected(item)
    }
  }
}
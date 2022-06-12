package com.tilikki.app.notepadia.note

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tilikki.app.notepadia.models.Note
import com.tilikki.app.notepadia.repository.NoteRepository
import java.util.Date

class NoteEditorViewModel : ViewModel() {
  private var _note: MutableLiveData<Note> = MutableLiveData()
  val note: LiveData<Note>
    get() = _note

  var title: MutableLiveData<String> = MutableLiveData()

  var content: MutableLiveData<String> = MutableLiveData()

  fun initializeNoteEditor(note: Note?) {
    _note.value = note ?: Note()
    title.value = note?.title
    content.value = note?.content
  }

  fun updateNote() {
    updateNote(title.value.orEmpty(), content.value.orEmpty())
  }

  private fun updateNote(title: String, content: String) {
    val currentNote = _note.value ?: return
    val newTitle = if (currentNote.title == title) currentNote.title else title
    val newContents = if (currentNote.content == content) currentNote.content else content
    val newNote = Note(currentNote.uuid, newTitle, newContents, currentNote.color, Date())
    _note.value = newNote
    saveToDb()
  }

  fun saveToDb() {
    _note.value?.let { note ->
      NoteRepository().addOrUpdateNote(note)
    }
  }
}
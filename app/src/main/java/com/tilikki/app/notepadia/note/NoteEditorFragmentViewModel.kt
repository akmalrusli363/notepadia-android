package com.tilikki.app.notepadia.note

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tilikki.app.notepadia.models.Note

class NoteEditorFragmentViewModel : ViewModel() {
  private var _note: MutableLiveData<Note> = MutableLiveData()
  val note: LiveData<Note>
    get() = _note

  fun initializeNoteEditor(note: Note?) {
    _note.value = note ?: Note()
  }
}
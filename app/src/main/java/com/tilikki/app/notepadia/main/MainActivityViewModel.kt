package com.tilikki.app.notepadia.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tilikki.app.notepadia.models.Note
import com.tilikki.app.notepadia.repository.NoteRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivityViewModel : ViewModel() {
  private var _notes: MutableLiveData<List<Note>> = MutableLiveData()
  val notes: LiveData<List<Note>>
    get() = _notes

  fun submitList(list: List<Note>) {
    val sortedList = list.sortedByDescending { element -> element.lastUpdated }
    _notes.postValue(sortedList)
  }

  fun getNoteList() {
    val observableNoteList = NoteRepository().getNotes()
    observableNoteList.run {
      this.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        .subscribe({ noteList ->
          submitList(noteList)
          Log.d("RoomDB", "List fetching success! List: $noteList")
        }, { throwable ->
          Log.e("RoomDB", "List fetching failed!", throwable)
        })
    }
  }
}
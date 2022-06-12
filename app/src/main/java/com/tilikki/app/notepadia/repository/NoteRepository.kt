package com.tilikki.app.notepadia.repository

import android.content.Context
import com.tilikki.app.notepadia.NotePadiaApp
import com.tilikki.app.notepadia.database.AppDatabase
import com.tilikki.app.notepadia.database.entity.EntityNote
import com.tilikki.app.notepadia.models.Note
import io.reactivex.Observable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class NoteRepository(context: Context) {
  constructor() : this(NotePadiaApp.appContext)

  private val database: AppDatabase = AppDatabase.getDatabase(context)
  private val scope = CoroutineScope(Job() + Dispatchers.IO)

  fun addOrUpdateNote(note: Note) {
    scope.launch {
      val eNote = EntityNote.fromDomainEntityNote(note)
      database.noteDao.insertNewNote(eNote)
    }
  }

  fun getNotes(): Observable<List<Note>> {
    return database.noteDao.getAllNotes().map { list ->
      return@map list.map { eNote ->
        eNote.toDomainEntityNote()
      }
    }.onErrorReturn { listOf() }
  }
}
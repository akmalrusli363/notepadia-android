package com.tilikki.app.notepadia.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.tilikki.app.notepadia.database.entity.EntityNote
import io.reactivex.Observable

@Dao interface NoteDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertImportedNote(notes: List<EntityNote>)

  @Insert(onConflict = OnConflictStrategy.REPLACE) suspend fun insertNewNote(note: EntityNote)

  @Update(onConflict = OnConflictStrategy.REPLACE) suspend fun updateNote(note: EntityNote)

  @Transaction @Query("SELECT * FROM notes WHERE uuid LIKE :uuidToFind") suspend fun getNoteById(
    uuidToFind: String
  ): EntityNote

  @Query("SELECT * FROM notes") fun getAllNotes(): Observable<List<EntityNote>>
}
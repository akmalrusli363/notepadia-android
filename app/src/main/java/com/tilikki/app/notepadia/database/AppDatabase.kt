package com.tilikki.app.notepadia.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tilikki.app.notepadia.database.dao.NoteDao
import com.tilikki.app.notepadia.database.entity.EntityNote

@Database(
  entities = [EntityNote::class], version = 1, exportSchema = false
) abstract class AppDatabase : RoomDatabase() {
  abstract val noteDao: NoteDao

  companion object {
    private lateinit var INSTANCE: AppDatabase

    fun getDatabase(context: Context): AppDatabase {
      synchronized(AppDatabase::class.java) {
        if (!this::INSTANCE.isInitialized) {
          INSTANCE = Room.databaseBuilder(
            context.applicationContext, AppDatabase::class.java, "notepadia-db"
          ).build()
        }
      }
      return INSTANCE
    }
  }
}
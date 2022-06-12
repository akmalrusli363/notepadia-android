package com.tilikki.app.notepadia.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tilikki.app.notepadia.models.Note
import com.tilikki.app.notepadia.models.NoteColor
import java.util.Date
import java.util.UUID

@Entity(tableName = "notes") data class EntityNote(
  @PrimaryKey @ColumnInfo(name = "uuid") val uuid: String,
  @ColumnInfo(name = "title") val title: String,
  @ColumnInfo(name = "contents") val contents: String,
  @ColumnInfo(name = "last_updated") val lastUpdated: Long,
  @ColumnInfo(name = "background_color") val backgroundColor: Int?,
  @ColumnInfo(name = "text_color") val textColor: Int?
) {
  companion object {
    @JvmStatic fun fromDomainEntityNote(note: Note): EntityNote {
      return EntityNote(
        uuid = note.uuid.toString(),
        title = note.title,
        contents = note.content,
        lastUpdated = note.lastUpdated.time,
        backgroundColor = note.color?.background?.rgbHex,
        textColor = note.color?.textColor?.rgbHex
      )
    }
  }

  fun toDomainEntityNote(): Note {
    val noteColor = if (backgroundColor == null || textColor == null) {
      null
    } else {
      NoteColor(backgroundColor, textColor)
    }
    return Note(
      uuid = UUID.fromString(uuid),
      title = title,
      content = contents,
      color = noteColor,
      lastUpdated = Date(lastUpdated)
    )
  }
}
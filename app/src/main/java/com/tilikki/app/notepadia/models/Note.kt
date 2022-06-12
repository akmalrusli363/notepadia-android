package com.tilikki.app.notepadia.models

import android.os.Parcel
import android.os.Parcelable
import com.tilikki.app.notepadia.utils.ParcelableHelper
import java.util.Date
import java.util.UUID

data class Note(
  val uuid: UUID,
  val title: String,
  val content: String,
  val color: NoteColor?,
  val lastUpdated: Date
) : Parcelable {
  constructor(parcel: Parcel) : this(
    UUID.fromString(parcel.readString()),
    parcel.readString().orEmpty(),
    parcel.readString().orEmpty(),
    NoteColor.readFromParcelable(parcel.readInt(), parcel.readInt()),
    Date(parcel.readLong())
  )

  constructor(title: String, contents: String, color: NoteColor) : this(
    UUID.randomUUID(), title, contents, color, Date()
  )

  constructor() : this(
    UUID.randomUUID(), "", "", NoteColor.DEFAULT_COLOR, Date()
  )

  override fun writeToParcel(parcel: Parcel, flags: Int) {
    parcel.writeString(uuid.toString())
    parcel.writeString(title)
    parcel.writeString(content)
    parcel.writeInt(ParcelableHelper.encodeIntOrNull(color?.background?.rgbHex))
    parcel.writeInt(ParcelableHelper.encodeIntOrNull(color?.textColor?.rgbHex))
    parcel.writeLong(lastUpdated.time)
  }

  override fun describeContents(): Int {
    return 0
  }

  companion object CREATOR : Parcelable.Creator<Note> {
    override fun createFromParcel(parcel: Parcel): Note {
      return Note(parcel)
    }

    override fun newArray(size: Int): Array<Note?> {
      return arrayOfNulls(size)
    }
  }
}
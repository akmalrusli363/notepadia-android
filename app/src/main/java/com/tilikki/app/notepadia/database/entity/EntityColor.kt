package com.tilikki.app.notepadia.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "colors") data class EntityColor(
  @PrimaryKey @ColumnInfo(name = "id") val id: Int,
  @ColumnInfo(name = "background_color") val backgroundColor: Int,
  @ColumnInfo(name = "text_color") val textColor: Int
)
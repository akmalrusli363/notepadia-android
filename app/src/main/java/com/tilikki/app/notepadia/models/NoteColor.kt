package com.tilikki.app.notepadia.models

import android.content.Context
import android.graphics.Color
import androidx.annotation.ColorInt
import com.tilikki.app.notepadia.utils.ParcelableHelper

data class NoteColor(
  val background: BaseColor,
  val textColor: BaseColor,
) {
  companion object {
    val DEFAULT_COLOR = NoteColor(BaseColor(Color.WHITE), BaseColor(Color.BLACK))

    fun getDefaultNoteColor(context: Context): NoteColor {
      return NoteColor(
        BaseColor.getDefaultBackgroundColor(context), BaseColor.getDefaultTextColor(context)
      )
    }

    fun readFromParcelable(@ColorInt background: Int, @ColorInt textColor: Int): NoteColor? {
      val backgroundColorInt: Int? = ParcelableHelper.decodeIntAsNullableInt(background)
      val textColorInt: Int? = ParcelableHelper.decodeIntAsNullableInt(textColor)
      return if (backgroundColorInt == null || textColorInt == null) null
      else NoteColor(backgroundColorInt, textColorInt)
    }
  }

  constructor(background: BaseColor, @ColorInt textColor: Int) : this(
    background, BaseColor(textColor)
  )

  constructor(@ColorInt background: Int, textColor: BaseColor) : this(
    BaseColor(background), textColor
  )

  constructor(@ColorInt background: Int, @ColorInt textColor: Int) : this(
    BaseColor(background), BaseColor(textColor)
  )
}

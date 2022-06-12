package com.tilikki.app.notepadia.models

import android.content.Context
import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.graphics.ColorUtils
import com.tilikki.app.notepadia.R

data class BaseColor(
  @ColorInt val rgbHex: Int
) {

  constructor(red: Int, green: Int, blue: Int) : this(rgbToIntHex(red, green, blue))

  constructor(rgbHex: String) : this(decodeRgbHexString(rgbHex))

  constructor(context: Context, @ColorRes colorRes: Int) : this(getFromResources(context, colorRes))

  companion object {
    private fun rgbToIntHex(red: Int, green: Int, blue: Int): Int {
      return (256 * 256 * red) + (256 * green) + blue
    }

    private fun decodeRgbHexString(rgbHex: String): Int {
      return Color.parseColor(rgbHex)
    }

    fun getFromResources(context: Context, @ColorRes colorRes: Int): Int {
      val argbColor = ContextCompat.getColor(context, colorRes)
      return ColorUtils.compositeColors(argbColor, Color.WHITE)
    }

    fun getDefaultBackgroundColor(context: Context): BaseColor {
      return BaseColor(context, R.color.noteBackgroundColor)
    }

    fun getDefaultTextColor(context: Context): BaseColor {
      return BaseColor(context, R.color.titleTextColor)
    }
  }
}

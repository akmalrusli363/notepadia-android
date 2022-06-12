package com.tilikki.app.notepadia.utils

object ParcelableHelper {
  fun encodeIntOrNull(int: Int?): Int {
    return int ?: Int.MIN_VALUE
  }

  fun decodeIntAsNullableInt(int: Int): Int? {
    return if (int == Int.MIN_VALUE) null else int
  }
}
package com.tilikki.app.notepadia

import android.app.Application
import android.content.Context

class NotePadiaApp : Application() {
  companion object {
    lateinit var appContext: Context
  }

  override fun onCreate() {
    super.onCreate()
    appContext = applicationContext
  }
}
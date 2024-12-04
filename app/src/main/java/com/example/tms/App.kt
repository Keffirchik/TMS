package com.example.tms

import android.app.Application
import com.example.tms.data.storage.RoomObject

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        RoomObject.initDB(this)

    }
}
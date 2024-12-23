package com.example.tms

import android.app.Application
import com.example.tms.data.storage.RoomObject
import com.example.tms.data.storage.SharedPreferencesObject

class App: Application() {

    var appComponent: AppComponent = DaggerAppComponent.create()

    override fun onCreate() {
        super.onCreate()
        RoomObject.initDB(this)
        SharedPreferencesObject.initDB(this)
    }
}
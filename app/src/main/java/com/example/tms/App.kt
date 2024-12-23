package com.example.tms

import android.app.Application
import com.example.tms.data.storage.RoomObject
import com.example.tms.data.storage.SharedPreferencesObject

class App: Application() {

    var appComponent: AppComponent? = null

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().context(this).build()
//        RoomObject.initDB(this)
        SharedPreferencesObject.initDB(this)
    }
}
package com.example.tms.di

import androidx.room.Room
import com.example.tms.data.storage.RoomDB
import com.example.tms.data.storage.RoomObject
import com.example.tms.di.scope.CustomScope
import dagger.Module
import dagger.Provides

@Module
class RoomModule {

    @Provides
//    @CustomScope
    fun provideRoom(): RoomDB =
        Room.databaseBuilder(context, RoomDB::class.java, "MyDataBase").build()
}
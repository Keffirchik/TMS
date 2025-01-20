package com.example.tms.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tms.data.storage.RoomDB
import dagger.Module
import dagger.Provides

@Module
class RoomModule {

    @Provides
    fun provideRoom(context: Context): RoomDB =
        Room.databaseBuilder(context, RoomDB::class.java, "MyDataBase").build()
}
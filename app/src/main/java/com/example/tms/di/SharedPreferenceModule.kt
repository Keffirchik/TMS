package com.example.tms.di

import android.content.Context
import android.content.SharedPreferences
import com.example.tms.data.storage.SharedPreferencesDB
import com.example.tms.di.scope.CustomScope
import dagger.Module
import dagger.Provides

@Module
class SharedPreferenceModule {

    @Provides
    @CustomScope
    fun provideSharedPreference(context: Context): SharedPreferences = SharedPreferencesDB(context).getInstance()



}
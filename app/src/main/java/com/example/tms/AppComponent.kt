package com.example.tms

import android.content.Context
import com.example.tms.di.RoomModule
import com.example.tms.di.SharedPreferenceModule
import com.example.tms.di.scope.CustomScope
import com.example.tms.presentation.view.activities.MainActivity
import dagger.BindsInstance
import dagger.Component

@Component(modules = [RoomModule::class, SharedPreferenceModule::class])
@CustomScope
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Component.Builder
        fun build(): AppComponent
    }

    fun inject(mainActivity: MainActivity)
}
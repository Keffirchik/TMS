package com.example.tms

import android.content.Context
import com.example.tms.di.RoomModule
import com.example.tms.di.SharedPreferenceModule
import com.example.tms.di.scope.CustomScope
import com.example.tms.presentation.view.activities.MainActivity
import com.example.tms.presentation.view.fragments.AddNoteFragment
import com.example.tms.presentation.view.fragments.NotesFragment
import dagger.BindsInstance
import dagger.Component

@Component(modules = [RoomModule::class, SharedPreferenceModule::class])
@CustomScope
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder
        fun build(): AppComponent
    }

    fun inject(mainActivity: MainActivity)

    fun inject(notesFragment: NotesFragment)

    fun inject(addNoteFragment: AddNoteFragment)
}
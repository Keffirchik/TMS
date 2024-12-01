package com.example.tms.presentation.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tms.presentation.view.AddNoteFragmentAction

class AddFragmentModel : ViewModel() {

    private val _liveData: MutableLiveData<AddNoteFragmentAction> = MutableLiveData(null)

    val publicLiveData: LiveData<AddNoteFragmentAction> = _liveData

    fun toNextScreen(action: AddNoteFragmentAction) {
        _liveData.value = action
    }
}
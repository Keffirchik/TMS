package com.example.tms.presentation.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tms.presentation.view.MainFragmentAction

class MainFragmentModel : ViewModel(){

    private val _liveData: MutableLiveData<MainFragmentAction> = MutableLiveData(null)

    val publicLiveData: LiveData<MainFragmentAction> = _liveData

    fun toNextScreen(action: MainFragmentAction) {
        _liveData.value = action
    }

}


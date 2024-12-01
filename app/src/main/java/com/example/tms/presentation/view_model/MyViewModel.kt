package com.example.tms.presentation.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel() : ViewModel() {

    private val _liveData = MutableLiveData<String>("First string")

    fun updateTextField() {
        _liveData.value = "second string"
    }

}
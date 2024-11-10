package com.example.tms.presentation.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tms.domain.use_case.SaveUseCase

class ViewModel() : ViewModel() {

    val liveData = MutableLiveData<String>("First string")

    fun updateTextField() {
        liveData.value = "second string"
    }

}
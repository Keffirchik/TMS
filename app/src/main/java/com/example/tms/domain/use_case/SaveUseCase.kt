package com.example.tms.domain.use_case

import com.example.tms.domain.repositories.Repository

class SaveUseCase(
    private val repository: Repository
) {

    fun invokeSave() {
        repository.save()
    }
}
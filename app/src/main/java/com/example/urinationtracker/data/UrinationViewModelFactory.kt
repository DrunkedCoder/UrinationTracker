package com.example.urinationtracker.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class UrinationViewModelFactory(
    private val repository: UrinationRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UrinationViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UrinationViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}





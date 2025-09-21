package com.example.urinationtracker

import android.app.Application
import com.example.urinationtracker.data.UrinationDatabase
import com.example.urinationtracker.data.UrinationRepository
import com.example.urinationtracker.data.UrinationViewModelFactory

class UrinationApp : Application() {

    // Singletons for the whole app process
    val database: UrinationDatabase by lazy {
        UrinationDatabase.getDatabase(this)
    }

    val repository: UrinationRepository by lazy {
        UrinationRepository(database.urinationDao())
    }

    // Convenience factory so Activities can do: by viewModels { (application as UrinationApp).viewModelFactory }
    val viewModelFactory: UrinationViewModelFactory by lazy {
        UrinationViewModelFactory(repository)
    }

    override fun onCreate() {
        super.onCreate()
        // Place for future init: logging, crash reporting, StrictMode, etc.
    }
}


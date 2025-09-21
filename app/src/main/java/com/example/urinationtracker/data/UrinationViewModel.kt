package com.example.urinationtracker.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class UrinationViewModel(private val repository: UrinationRepository) : ViewModel() {

    // ✅ Daily totals for PastRecordsActivity
    val dailyTotals: LiveData<List<DailyTotal>> = repository.getDailyTotals().asLiveData()

    // ✅ Today's entries for MainActivity
    val todayEntries: LiveData<List<UrinationEntry>> =
        repository.getEntriesForDate(UrinationUtils.getCurrentDate()).asLiveData()

    fun addEntry(amountMl: Int) {
        val date = UrinationUtils.getCurrentDate()
        val time = UrinationUtils.getCurrentTime()
        val entry = UrinationEntry(0, date, time, amountMl)

        viewModelScope.launch {
            repository.insert(entry)
        }
    }

    fun getRecordsForDate(date: String): LiveData<List<UrinationEntry>> {
        return repository.getEntriesForDate(date).asLiveData()
    }
}











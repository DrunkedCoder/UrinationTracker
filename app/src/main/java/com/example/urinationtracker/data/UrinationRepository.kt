package com.example.urinationtracker.data

class UrinationRepository(private val dao: UrinationDao) {

    // Get all records for a specific date
    fun getEntriesForDate(date: String) = dao.getEntriesForDate(date)

    // Get daily totals (date + totalAmount)
    fun getDailyTotals() = dao.getDailyTotals()

    // Insert a new urination entry
    suspend fun insert(entry: UrinationEntry) {
        dao.insert(entry)
    }
}












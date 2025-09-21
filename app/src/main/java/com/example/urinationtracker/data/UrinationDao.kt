package com.example.urinationtracker.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UrinationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entry: UrinationEntry)

    // For chart view
    @Query("SELECT * FROM urination_entries WHERE date = :date ORDER BY time ASC")
    fun getEntriesForDate(date: String): Flow<List<UrinationEntry>>

    // For daily totals view
    @Query("""
        SELECT date, SUM(amountMl) AS totalAmount
        FROM urination_entries
        GROUP BY date
        ORDER BY date DESC
    """)
    fun getDailyTotals(): Flow<List<DailyTotal>>

    // For spreadsheet/day record view
    @Query("SELECT * FROM urination_entries WHERE date = :date ORDER BY time ASC")
    fun getRecordsForDate(date: String): Flow<List<UrinationEntry>>
}







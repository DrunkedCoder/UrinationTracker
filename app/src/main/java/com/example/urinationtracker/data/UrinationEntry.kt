package com.example.urinationtracker.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "urination_entries") // ✅ matches DAO queries
data class UrinationEntry(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val date: String,
    val time: String,
    val amountMl: Int
)


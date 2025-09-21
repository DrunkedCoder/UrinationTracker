package com.example.urinationtracker.data

import java.text.SimpleDateFormat
import java.util.*

object UrinationUtils {

    fun getCurrentDate(): String {  // ✅ renamed
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return formatter.format(Date())
    }

    fun getCurrentTime(): String {
        val formatter = SimpleDateFormat("HH:mm", Locale.getDefault())
        return formatter.format(Date())
    }
}


package com.example.urinationtracker.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [UrinationEntry::class], version = 2, exportSchema = false)
abstract class UrinationDatabase : RoomDatabase() {
    abstract fun urinationDao(): UrinationDao

    companion object {
        @Volatile
        private var INSTANCE: UrinationDatabase? = null

        fun getDatabase(context: Context): UrinationDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UrinationDatabase::class.java,
                    "urination_db"
                )
                    .addMigrations(MIGRATION_1_2) // ✅ migration support
                    .build()
                INSTANCE = instance
                instance
            }
        }

        // ✅ Migration: rename old table to new name
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    "ALTER TABLE urination_records RENAME TO urination_entries"
                )
            }
        }
    }
}




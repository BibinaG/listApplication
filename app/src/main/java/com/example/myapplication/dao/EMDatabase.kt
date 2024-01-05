package com.example.myapplication.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.myapplication.lists.EmployeData

@Database(entities = [EmployeData::class], version = 1, exportSchema = false)
abstract class EMDatabase : RoomDatabase() {
    abstract fun employeeDAO(): EmployeeDAO

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: EMDatabase? = null

        fun getDatabase(context: Context): EMDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EMDatabase::class.java,
                    "em_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}
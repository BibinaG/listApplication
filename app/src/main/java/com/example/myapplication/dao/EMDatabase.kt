package com.example.myapplication.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.myapplication.lists.EmployeData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [EmployeData::class], version = 1, exportSchema = false)
abstract class EMDatabase : RoomDatabase() {
    abstract fun employeeDAO(): EmployeeDAO
    private class EmployeeDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    val emDAO = database.employeeDAO()
                    emDAO.deleteAll()
                }
            }
        }
    }

    companion object {
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
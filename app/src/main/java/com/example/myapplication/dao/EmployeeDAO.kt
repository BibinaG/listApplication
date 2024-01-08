package com.example.myapplication.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.myapplication.lists.EmployeData
import kotlinx.coroutines.flow.Flow

@Dao
interface EmployeeDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIntoEMData(emData: EmployeData)

    @Query("SELECT * FROM Employee")
    fun getAllEmployeeDetails(): Flow<List<EmployeData>>

    @Query("DELETE FROM Employee")
    suspend fun deleteAll()


}
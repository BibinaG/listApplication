package com.example.myapplication.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.myapplication.lists.EmployeData

@Dao
interface EmployeeDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIntoEMData(emData: EmployeData)

    @Query("SELECT * FROM employee")
    fun getAllEmployeeDetails(): LiveData<List<EmployeData>>


    @Delete
    fun delete(emData: EmployeData)

}
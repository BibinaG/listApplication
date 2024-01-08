package com.example.myapplication.lists.repo

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.myapplication.UiState
import com.example.myapplication.dao.EmployeeDAO
import com.example.myapplication.handler.doTryCatch
import com.example.myapplication.handler.handleResponse
import com.example.myapplication.lists.DummyResponse
import com.example.myapplication.lists.EmployeData
import com.example.myapplication.pagination.network.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import java.nio.channels.FileLock


class EmployeeRepo(private val employeeDAO: EmployeeDAO) {

    suspend fun getEmployeeDetails(): UiState<DummyResponse> {
        return withContext(Dispatchers.IO) {
            doTryCatch {
                ApiClient.instance.getEmployeeDetails().handleResponse()
            }
        }
    }

    val shipments: Flow<List<EmployeData>> = employeeDAO.getAllEmployeeDetails()

    suspend fun insert(emData: EmployeData) {
        employeeDAO.insertIntoEMData(emData)
    }
}


// Room executes all queries on a separate thread.
// Observed LiveData will notify the observer when the data has changed.




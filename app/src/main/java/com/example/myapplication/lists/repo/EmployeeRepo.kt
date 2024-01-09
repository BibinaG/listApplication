package com.example.myapplication.lists.repo

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


class EmployeeRepo(private val employeeDAO: EmployeeDAO) {

    suspend fun getEmployeeDetails(): UiState<DummyResponse> {
        return withContext(Dispatchers.IO) {
            doTryCatch {
                ApiClient.instance.getEmployeeDetails().handleResponse()
            }
        }
    }

    val employeeData: Flow<List<EmployeData>> = employeeDAO.getAllEmployeeDetails()

    suspend fun insert(emData: EmployeData) {
        employeeDAO.insertIntoEMData(emData)
    }
}






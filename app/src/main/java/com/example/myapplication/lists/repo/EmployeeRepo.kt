package com.example.myapplication.lists.repo

import com.example.myapplication.UiState
import com.example.myapplication.handler.doTryCatch
import com.example.myapplication.lists.DummyResponse
import com.example.myapplication.pagination.network.ApiClient
import com.example.myapplication.handler.handleResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class EmployeeRepo {
    suspend fun getEmployeeDetails(): UiState<DummyResponse> {
        return withContext(Dispatchers.IO) {
            doTryCatch {
                ApiClient.instance.getEmployeeDetails().handleResponse()
            }
        }
    }
}

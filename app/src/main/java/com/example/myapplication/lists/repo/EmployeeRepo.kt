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
import kotlinx.coroutines.withContext





class EmployeeRepo() {

    suspend fun getEmployeeDetails(): UiState<DummyResponse> {
        return withContext(Dispatchers.IO) {
            doTryCatch {
                ApiClient.instance.getEmployeeDetails().handleResponse()
            }
        }
    }



    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.




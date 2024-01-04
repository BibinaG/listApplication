package com.example.myapplication.lists.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.UiState
import com.example.myapplication.dao.EMDatabase
import com.example.myapplication.lists.DummyResponse
import com.example.myapplication.lists.EmployeData
import com.example.myapplication.lists.repo.EmployeeRepo
import kotlinx.coroutines.launch

class EmployeeVM() : ViewModel() {
    private val repo by lazy {
        EmployeeRepo()
    }
    private val _employeeData = MutableLiveData<UiState<DummyResponse>>()
    val employeeDetails: LiveData<UiState<DummyResponse>> = _employeeData


    fun fetData() {
        viewModelScope.launch {
            _employeeData.value = UiState.Loading()
            _employeeData.value = repo.getEmployeeDetails()
        }
    }

    fun insert(context: Context, employeData: EmployeData) =
        viewModelScope.launch {
            EMDatabase.getDatabase(context = context).employeeDAO().insertIntoEMData(employeData)
        }


}
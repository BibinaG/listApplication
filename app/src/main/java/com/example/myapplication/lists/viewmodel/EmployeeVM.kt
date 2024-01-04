package com.example.myapplication.lists.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.UiState
import com.example.myapplication.dao.EMDatabase
import com.example.myapplication.lists.DummyResponse
import com.example.myapplication.lists.EmployeData
import com.example.myapplication.lists.repo.EmployeeRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EmployeeVM(application: Application) : AndroidViewModel(application) {
    private val repo: EmployeeRepo
    private val getAllEmployeeDetails: LiveData<List<EmployeData>>

    init {
        val dao = EMDatabase.getDatabase(application).employeeDAO()
        repo = EmployeeRepo(dao)
        getAllEmployeeDetails = repo.employData

    }
    fun addEmployee(employee:EmployeData){
        viewModelScope.launch (Dispatchers.IO){
            repo.addEmployeeData(employee)
        }
    }

    private val _employeeData = MutableLiveData<UiState<DummyResponse>>()
    val employeeDetails: LiveData<UiState<DummyResponse>> = _employeeData


    fun fetData() {
        viewModelScope.launch {
            _employeeData.value = UiState.Loading()
            _employeeData.value = repo.getEmployeeDetails()
        }
    }



}
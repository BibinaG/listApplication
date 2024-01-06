package com.example.myapplication.lists.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.UiState
import com.example.myapplication.lists.DummyResponse
import com.example.myapplication.lists.EmployeData
import com.example.myapplication.lists.repo.EmployeeRepo
import kotlinx.coroutines.launch

class EmployeeVM(application: Application) : AndroidViewModel(application) {
    private val repo by lazy {
        EmployeeRepo()
    }

    private val _employeeData = MutableLiveData<UiState<DummyResponse>>()
    val employeeDetails: LiveData<UiState<DummyResponse>> = _employeeData


    private val _emData = MutableLiveData<List<EmployeData>>()
    val getEmployeeLikedData: LiveData<List<EmployeData>> = _emData


    fun fetData() {
        viewModelScope.launch {
            _employeeData.value = UiState.Loading()
            _employeeData.value = repo.getEmployeeDetails()
        }
    }

    fun addData(employeData: EmployeData) {
        viewModelScope.launch {
            _emData.value = listOf(employeData)

        }

    }

    fun setValue(employeData: EmployeData) {

    }


}
package com.example.myapplication.lists.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.UiState
import com.example.myapplication.lists.DummyResponse
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

}
package com.example.myapplication.lists.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.UiState
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

    private val employeLikeData = MutableLiveData<List<EmployeData>>()
    val likedData: LiveData<List<EmployeData>> = employeLikeData


    fun fetData() {
        viewModelScope.launch {
            _employeeData.value = UiState.Loading()
            _employeeData.value = repo.getEmployeeDetails()
        }
    }


    //    val likedEmployeeData: LiveData<List<EmployeData>> = repo.employeeData.asLiveData()
    fun insertValueInDatabase(employeData: EmployeData) = viewModelScope.launch {
        employeLikeData.postValue(listOf(employeData))
    }


}
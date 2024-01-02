package com.example.myapplication.lists.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.UiState
import com.example.myapplication.lists.DummyResponse
import com.example.myapplication.lists.EmployeData
import com.example.myapplication.lists.repo.EmployeeRepo
import com.google.gson.Gson
import kotlinx.coroutines.launch

class EmployeeVM() : ViewModel() {
    private val repo by lazy {
        EmployeeRepo()
    }
    private val _employeeData = MutableLiveData<UiState<DummyResponse>>()
    val employeeDetails: LiveData<UiState<DummyResponse>> = _employeeData

    private val _likedData = MutableLiveData<UiState<DummyResponse>>()
    val likedData: LiveData<UiState<DummyResponse>> = _likedData

    fun fetData() {
        viewModelScope.launch {
            _employeeData.value = UiState.Loading()
            _employeeData.value = repo.getEmployeeDetails()
        }
    }

    fun addEmployeData(data: List<EmployeData>) {
        Log.e("Check", " addEmployeData: " + Gson().toJson(data))
    }


}
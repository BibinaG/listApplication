package com.example.myapplication.lists.viewmodel

import androidx.lifecycle.*
import com.example.myapplication.UiState
import com.example.myapplication.lists.DummyResponse
import com.example.myapplication.lists.EmployeData
import com.example.myapplication.lists.repo.EmployeeRepo
import kotlinx.coroutines.launch

class EmployeeVM(private val repo: EmployeeRepo) : ViewModel() {
    private val _employeeData = MutableLiveData<UiState<DummyResponse>>()
    val employeeDetails: LiveData<UiState<DummyResponse>> = _employeeData


    fun fetData() {
        viewModelScope.launch {
            _employeeData.value = UiState.Loading()
            _employeeData.value = repo.getEmployeeDetails()
        }
    }


    val emData: LiveData<List<EmployeData>> = repo.shipments.asLiveData()

    fun insert(word: EmployeData) = viewModelScope.launch {
        repo.insert(word)
    }


    class WordViewModelFactory(private val repository: EmployeeRepo) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(EmployeeVM::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return EmployeeVM(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }





}
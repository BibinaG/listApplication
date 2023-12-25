package com.example.myapplication.lists.viewmodel

import androidx.lifecycle.*
import com.example.myapplication.lists.DummyResponse
import com.example.myapplication.lists.EmployeData
import com.example.myapplication.lists.repo.EmployeeRepo

class EmployeeVM(private val repo: EmployeeRepo) : ViewModel() {
    private val _employeeData = MutableLiveData<EmployeData>()
    val employeeDetails: LiveData<EmployeData> get() = _employeeData


    fun fetData() {
        repo.fetchData()
    }


//    class EmployeeVM(
//        private val repo: EmployeeRepo
//    ): ViewModel() {
//
//        fun login(username: String, token: String) {
//            // Create a new coroutine to move the execution off the UI thread
//            viewModelScope.launch(Dispatchers.IO) {
//                val jsonBody = "{ username: \"$username\", token: \"$token\"}"
//                loginRepository.makeLoginRequest(jsonBody)
//            }
//        }
//    }

}
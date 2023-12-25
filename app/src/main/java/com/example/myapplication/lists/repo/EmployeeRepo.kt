package com.example.myapplication.lists.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.lists.DummyResponse
import com.example.myapplication.lists.EmployeData
import com.example.myapplication.pagination.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EmployeeRepo {
    private val _employeeDetails = MutableLiveData<EmployeData>()
    val employeeDetails: LiveData<EmployeData> get() = _employeeDetails

    fun fetchData() {
        val call: Call<EmployeData> = ApiClient.getInstance().employeDetails
        call.enqueue(object : Callback<EmployeData> {
            override fun onResponse(call: Call<EmployeData>, response: Response<EmployeData>) {
                if (response.isSuccessful) {
                    _employeeDetails.postValue(response.body())
                }
            }


            override fun onFailure(call: Call<EmployeData>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}

package com.example.myapplication.pagination.network

import com.example.myapplication.lists.DummyResponse
import com.example.myapplication.lists.EmployeData
import com.example.myapplication.pagination.response.UserDataResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiGateway {
    @GET("users")
    fun getUserDetails(@Query("page") Pages: Int): Call<UserDataResponse?>?

    @GET("employees")
    suspend fun getEmployeeData(): Response<DummyResponse>
}
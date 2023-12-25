package com.example.myapplication.pagination.network;

import com.example.myapplication.lists.DummyResponse;
import com.example.myapplication.lists.EmployeData;
import com.example.myapplication.pagination.response.UserDataResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiGateway {
    @GET("users")
    Call<UserDataResponse> getUserDetails(@Query("page") int Pages);

    @GET("employees")
    Call<EmployeData> getDetails();

}

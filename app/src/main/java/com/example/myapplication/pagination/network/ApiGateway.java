package com.example.myapplication.pagination.network;

import com.example.myapplication.pagination.model.UserModel;
import com.example.myapplication.pagination.response.UserDataResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface ApiGateway {
    @GET("users")
//    Call<UserDataResponse> getUserDetails(@Query("page") int Pages);
    Call<UserModel> getUserDetails(@Query("page") int Pages);
}

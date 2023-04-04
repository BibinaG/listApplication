package com.example.myapplication.pagination.network;

import com.example.myapplication.BuildConfig;
import com.example.myapplication.pagination.model.UserModel;
import com.example.myapplication.pagination.response.UserDataResponse;
import com.google.gson.JsonObject;

import java.util.concurrent.TimeUnit;

import okhttp3.CertificatePinner;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Url;

public class ApiClient {
    private static ApiClient apiClient;
    private static ApiGateway apiGateway;

    public ApiClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addNetworkInterceptor((new HttpLoggingInterceptor().setLevel(BuildConfig.DEBUG ?
                        HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE)));
        builder.connectTimeout(120, TimeUnit.SECONDS);
        builder.readTimeout(60, TimeUnit.SECONDS);
        builder.writeTimeout(60, TimeUnit.SECONDS);
        builder.followRedirects(false);
        builder.retryOnConnectionFailure(true);


        OkHttpClient client = builder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://reqres.in/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        apiGateway = retrofit.create(ApiGateway.class);

    }

    public static ApiClient getInstance() {

        if (apiClient == null) {
            apiClient = new ApiClient();
        }

        return new ApiClient();
    }

    public Call<UserModel> getUsers(int pages) {
        return apiGateway.getUserDetails( pages);
    }

}

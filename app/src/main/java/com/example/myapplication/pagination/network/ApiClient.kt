package com.example.myapplication.pagination.network

import com.example.myapplication.BuildConfig
import com.example.myapplication.lists.DummyResponse
import com.example.myapplication.lists.EmployeData
import com.example.myapplication.pagination.response.UserDataResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient {
    init {
        val builder = OkHttpClient.Builder()
            .addNetworkInterceptor(
                HttpLoggingInterceptor()
                    .setLevel(
                        if (BuildConfig.DEBUG)
                            HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
                    )
            )
        builder.connectTimeout(120, TimeUnit.SECONDS)
        builder.readTimeout(60, TimeUnit.SECONDS)
        builder.writeTimeout(60, TimeUnit.SECONDS)
        builder.followRedirects(false)
        builder.retryOnConnectionFailure(true)
        val client: OkHttpClient = builder.build()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://dummy.restapiexample.com/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        apiGateway = retrofit.create(ApiGateway::class.java)
    }

    fun getUsers(pages: Int): Call<UserDataResponse?>? {
        return apiGateway.getUserDetails(pages)
    }


    suspend fun getEmployeeDetails(): Response<DummyResponse> {
        return apiGateway.getEmployeeData()

    }


    companion object {
        private var apiClient: ApiClient? = null
        private lateinit var apiGateway: ApiGateway

        @JvmStatic
        val instance: ApiClient
            get() {
                if (apiClient == null) {
                    apiClient = ApiClient()
                }
                return ApiClient()
            }
    }
}
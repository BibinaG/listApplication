package com.example.myapplication.lists


import com.google.gson.annotations.SerializedName

data class DummyResponse(
    @SerializedName("data")
    var `data`: List<EmployeData>,
    @SerializedName("message")
    var message: String,
    @SerializedName("status")
    var status: String
)
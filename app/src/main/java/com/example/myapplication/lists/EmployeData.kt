package com.example.myapplication.lists


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Employee")
data class EmployeData(
    @PrimaryKey
    @SerializedName("id")
    var id: Int,
    @SerializedName("employee_age")
    var employeeAge: Int,
    @SerializedName("employee_name")
    var employeeName: String,
    @SerializedName("employee_salary")
    var employeeSalary: Int,

    @SerializedName("profile_image")
    var profileImage: String
)
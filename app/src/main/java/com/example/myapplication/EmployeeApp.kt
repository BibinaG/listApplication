package com.example.myapplication
import android.app.Application
import com.example.myapplication.dao.EMDatabase
import com.example.myapplication.lists.repo.EmployeeRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class EmployeeApp : Application() {
    private val applicationScope = CoroutineScope(SupervisorJob())
    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    private val database by lazy { EMDatabase.getDatabase(this,applicationScope) }
    val repository by lazy { EmployeeRepo(database.employeeDAO()) }
}
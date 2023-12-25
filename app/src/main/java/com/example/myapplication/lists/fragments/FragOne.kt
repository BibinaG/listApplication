package com.example.myapplication.lists.fragments

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.FragmentFragOneBinding
import com.example.myapplication.lists.DummyResponse
import com.example.myapplication.lists.EmployeData
import com.example.myapplication.lists.adapters.DataAdapter
import com.example.myapplication.lists.viewmodel.EmployeeVM
import com.example.myapplication.pagination.network.ApiClient
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FragOne : Fragment() {
    private lateinit var progressDialog: ProgressDialog
    private lateinit var adapter: DataAdapter
    private val employeeViewModel: EmployeeVM by viewModels()

    private val binding by lazy {
        FragmentFragOneBinding.inflate(LayoutInflater.from(requireContext()))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView = binding.root
        netWorkCall()
        initObserver()
        return rootView
    }

    private fun initObserver() {
        employeeViewModel.employeeDetails.observe(viewLifecycleOwner) { employeeDetails ->
            employeeDetails ?: return@observe
            when (employeeDetails) {


            }
        }

    }

    private fun netWorkCall() {

        val call: Call<DummyResponse> = ApiClient.getInstance().employeDetails
        call.enqueue(object : Callback<DummyResponse> {
            override fun onResponse(call: Call<DummyResponse>, response: Response<DummyResponse>) {
                if (response.isSuccessful) {
                    if (response.body()?.data != null) {
                        adapter = DataAdapter(FragOne(), response.body()?.data!!)
                        binding.rvData.layoutManager = LinearLayoutManager(
                            requireContext(),
                            LinearLayoutManager.VERTICAL, false
                        )
                        binding.rvData.adapter = adapter
                    }

                } else {
                    Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<DummyResponse>, t: Throwable) {
            }
        })
    }

    fun retrieveLikedData(data: EmployeData) {
        Log.e("likedData", Gson().toJson(data))


    }

}
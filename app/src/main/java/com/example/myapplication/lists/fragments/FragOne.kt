package com.example.myapplication.lists.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.UiState
import com.example.myapplication.dao.EMDatabase
import com.example.myapplication.databinding.FragmentFragOneBinding
import com.example.myapplication.lists.EmployeData
import com.example.myapplication.lists.adapters.DataAdapter
import com.example.myapplication.lists.viewmodel.EmployeeVM
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class FragOne : Fragment() {
    private lateinit var adapter: DataAdapter
    private val employeeViewModel by viewModels<EmployeeVM>()


    private val binding by lazy {
        FragmentFragOneBinding.inflate(LayoutInflater.from(requireContext()))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView = binding.root
        employeeViewModel.fetData()
        initObserver()
        return rootView
    }

    private fun initObserver() {
        employeeViewModel.employeeDetails.observe(this) { employeeDetails ->
            employeeDetails ?: return@observe
            when (employeeDetails) {
                is UiState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                is UiState.Success -> {
                    binding.progressBar.visibility = View.GONE
                    employeeDetails.data?.data?.let { setupAdapter(it) }

                }

                is UiState.Error -> {
                    binding.progressBar.visibility = View.VISIBLE

                }

                else -> {}
            }
        }


    }

    private fun setupAdapter(dataList: List<EmployeData>) {
        binding.rvData.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = DataAdapter(
                mList = dataList
            ) { clickedData ->
                Toast.makeText(requireContext(), "Liked Employee", Toast.LENGTH_SHORT).show()
                employeeViewModel.addData(clickedData)
                GlobalScope.launch(Dispatchers.IO) {
                    EMDatabase.getDatabase(requireContext()).employeeDAO()
                        .insertIntoEMData(clickedData)


                }
            }

        }
    }


}
package com.example.myapplication.lists.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.EmployeeApp
import com.example.myapplication.UiState
import com.example.myapplication.databinding.FragmentFragOneBinding
import com.example.myapplication.lists.EmployeData
import com.example.myapplication.lists.adapters.DataAdapter
import com.example.myapplication.lists.viewmodel.EmployeeVM


class FragOne : Fragment() {
    private val binding by lazy {
        FragmentFragOneBinding.inflate(LayoutInflater.from(requireContext()))
    }

    private val employeeViewModel: EmployeeVM by viewModels {
        EmployeeVM.WordViewModelFactory((requireActivity().application as EmployeeApp).repository)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        employeeViewModel.fetData()
        initObserver()


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
                employeeViewModel.insert(clickedData)

//                employeeViewModel.addData(clickedData)
//                GlobalScope.launch(Dispatchers.IO) {
//                    EMDatabase.getDatabase(requireContext()).employeeDAO()
//                        .insertIntoEMData(clickedData)
//
//
//                }

            }

        }
    }
}



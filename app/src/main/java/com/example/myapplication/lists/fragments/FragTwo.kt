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
import com.example.myapplication.Utils.LiveDataUtil
import com.example.myapplication.dao.EMDatabase
import com.example.myapplication.databinding.FragmentFragTwoBinding
import com.example.myapplication.lists.adapters.LikedDataAdapter
import com.example.myapplication.lists.viewmodel.EmployeeVM
import com.google.gson.Gson


class FragTwo : Fragment() {
    private val binding by lazy {
        FragmentFragTwoBinding.inflate(layoutInflater);
    }

    private val employeeVM by viewModels<EmployeeVM>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = binding.root
        initObserver()
        return view;

    }

    private fun initObserver() {
        employeeVM.getEmployeeData.observe(this) { addedData ->
            run {
                Log.e("initObserver: ", Gson().toJson(addedData))
                binding.rvLikedData.apply {
                    layoutManager = LinearLayoutManager(requireContext())
                    adapter = LikedDataAdapter(addedData) { em ->
                    }
                }
            }
        }
        LiveDataUtil.observeOnce(
            EMDatabase.getDatabase(requireContext()).employeeDAO().getAllEmployeeDetails()
        ) {
            binding.rvLikedData.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = LikedDataAdapter(it) { em ->
                    Toast.makeText(requireContext(), "Item Removed!!", Toast.LENGTH_SHORT).show()
                }
            }

        }


    }
}
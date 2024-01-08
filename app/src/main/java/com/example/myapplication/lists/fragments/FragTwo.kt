package com.example.myapplication.lists.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.EmployeeApp
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

    private val employeeViewModel: EmployeeVM by viewModels {
        EmployeeVM.WordViewModelFactory((requireActivity() as EmployeeApp).repository)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root;

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserver()

    }

    private fun initObserver() {
        employeeViewModel.emData.observe(this) {
            Log.e("initObserver: ", Gson().toJson(it))
            binding.rvLikedData.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = LikedDataAdapter(it)
            }

        }
//        LiveDataUtil.observeOnce(
//            EMDatabase.getDatabase(requireContext()).employeeDAO().getAllEmployeeDetails()
//        ) {
//            binding.rvLikedData.apply {
//                layoutManager = LinearLayoutManager(requireContext())
//                adapter = LikedDataAdapter(it)
//            }
////
//        }


    }


}
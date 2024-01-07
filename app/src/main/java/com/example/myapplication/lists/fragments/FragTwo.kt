package com.example.myapplication.lists.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.Utils.LiveDataUtil
import com.example.myapplication.dao.EMDatabase
import com.example.myapplication.databinding.FragmentFragTwoBinding
import com.example.myapplication.lists.adapters.LikedDataAdapter
import com.example.myapplication.lists.viewmodel.EmployeeVM
import com.google.gson.Gson
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class FragTwo : Fragment() {
    private val binding by lazy {
        FragmentFragTwoBinding.inflate(layoutInflater);
    }

    private val employeeVM by viewModels<EmployeeVM>()


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
        employeeVM.likedData.observe(this){
            Log.e( "initObserver: ",Gson().toJson(it) )

        }
        LiveDataUtil.observeOnce(EMDatabase.getDatabase(requireContext()).employeeDAO().getAllEmployeeDetails()
        ) {
            binding.rvLikedData.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = LikedDataAdapter(it)
            }
//
        }


    }


}
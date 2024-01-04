package com.example.myapplication.lists.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.myapplication.dao.EMDatabase
import com.example.myapplication.databinding.FragmentFragTwoBinding
import com.example.myapplication.lists.viewmodel.EmployeeVM


class FragTwo : Fragment() {
    private val binding by lazy {
        FragmentFragTwoBinding.inflate(layoutInflater);
    }
    private val employeeViewModel by viewModels<EmployeeVM>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = binding.root
        initObserver()
        return view;

    }

    private fun initObserver() {


    }



}
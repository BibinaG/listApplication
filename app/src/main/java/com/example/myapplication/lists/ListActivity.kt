package com.example.myapplication.lists

import android.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityListBinding
import com.example.myapplication.lists.adapters.ViewPagersAdapter
import com.example.myapplication.lists.fragments.FragTwo
import com.google.android.material.tabs.TabLayoutMediator


class ListActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityListBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initView()
        TabLayoutMediator(binding.tab, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "First Tab"
                1 -> tab.text = "Second Tab"
                else -> tab.text = "Tab ${position + 1}"
            }
        }.attach()
    }

    private fun initView() {
        val adapter = ViewPagersAdapter(this)
        binding.viewPager.adapter = adapter
    }



}
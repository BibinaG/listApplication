package com.example.myapplication

import android.app.IntentService
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity

import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.pagination.PPActivity

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(LayoutInflater.from(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initViews()


    }

    private fun initViews() {
        binding.dialog.setOnClickListener {
            startActivity(Intent(this@MainActivity, PersistantActivity::class.java))

        }
        binding.tvChat.setOnClickListener {
            startActivity(Intent(this@MainActivity, ChatActivity::class.java))
        }
        binding.tvPagination.setOnClickListener {
            startActivity(Intent(this@MainActivity, PPActivity::class.java))
        }
    }
}
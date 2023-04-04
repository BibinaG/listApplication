package com.example.myapplication.pagination

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ActivityPaginationBinding
import com.example.myapplication.pagination.model.UserModel
import com.example.myapplication.pagination.network.ApiClient
import com.example.myapplication.pagination.response.UserDataResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException


class PaginationActivity() : AppCompatActivity() {
    var page = 0
    var limit: Int = 2
    private lateinit var binding: ActivityPaginationBinding;
    private var userModalArrayList: ArrayList<UserModel>? = null
    private var userRVAdapter: AdapterUser? = null
    private val userRV: RecyclerView? = null
    private val loadingPB: ProgressBar? = null
    private val nestedSV: NestedScrollView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaginationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        userModalArrayList = ArrayList()

        getDataFromAPI(page, limit);

        binding.idNestedSV.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {
                page++
                binding.idPBLoading.visibility = View.VISIBLE
                getDataFromAPI(page, limit)
            }
        })


    }

    private fun getDataFromAPI(page: Int, limit: Int) {
        if (page > limit) {
            Toast.makeText(this, "That's all Data", Toast.LENGTH_SHORT).show()
            binding.idPBLoading.setVisibility(View.GONE);
            return;
        }


    }




}
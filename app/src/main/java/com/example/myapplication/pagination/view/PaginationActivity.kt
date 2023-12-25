package com.example.myapplication.pagination.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import com.example.myapplication.databinding.ActivityPaginationBinding
import com.example.myapplication.pagination.AdapterUser
import com.example.myapplication.pagination.model.UserModel
import com.example.myapplication.pagination.network.ApiClient
import com.example.myapplication.pagination.response.Data
import com.example.myapplication.pagination.response.UserDataResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PaginationActivity() : AppCompatActivity() {
    var page = 0
    var limit: Int = 2
    private lateinit var binding: ActivityPaginationBinding;
    private var userModalArrayList: ArrayList<UserModel>? = null


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
        ApiClient.getInstance().getUsers(page).enqueue(object : Callback<UserDataResponse> {
            override fun onResponse(
                call: Call<UserDataResponse>,
                response: Response<UserDataResponse>
            ) {
                binding.idPBLoading.visibility = View.GONE
                if (response.isSuccessful) {
                    val user = AdapterUser(
                        response.body()!!.data as java.util.ArrayList<Data?>,
                        this@PaginationActivity
                    )
                    binding.idRVUsers.adapter = user
                }
            }

            override fun onFailure(call: Call<UserDataResponse>, t: Throwable) {}
        })


    }


}
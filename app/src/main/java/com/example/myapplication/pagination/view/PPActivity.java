package com.example.myapplication.pagination.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.databinding.ActivityPpactivityBinding;
import com.example.myapplication.pagination.AdapterUser;
import com.example.myapplication.pagination.network.ApiClient;
import com.example.myapplication.pagination.response.Data;
import com.example.myapplication.pagination.response.UserDataResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PPActivity extends AppCompatActivity {
    ActivityPpactivityBinding binding;
    int page = 0, limit = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPpactivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getDataFromAPI(page, limit);


        binding.idNestedSV.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                // on scroll change we are checking when users scroll as bottom.
                if (scrollY == v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()) {
                    // in this method we are incrementing page number,
                    // making progress bar visible and calling get data method.
                    page++;
                    binding.idPBLoading.setVisibility(View.VISIBLE);
                    getDataFromAPI(page, limit);
                }
            }
        });
    }


    private void getDataFromAPI(int page, int limit) {
        if (page > limit) {
            Toast.makeText(this, "That's all the data..", Toast.LENGTH_SHORT).show();
            binding.idPBLoading.setVisibility(View.GONE);
            return;
        }

        ApiClient.getInstance().getUsers(page).enqueue(new Callback<UserDataResponse>() {
            @Override
            public void onResponse(Call<UserDataResponse> call, Response<UserDataResponse> response) {
                binding.idPBLoading.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    AdapterUser user = new AdapterUser((ArrayList<Data>) response.body().getData(), PPActivity.this);
                    binding.idRVUsers.setAdapter(user);
                }
            }

            @Override
            public void onFailure(Call<UserDataResponse> call, Throwable t) {

            }
        });
    }


}



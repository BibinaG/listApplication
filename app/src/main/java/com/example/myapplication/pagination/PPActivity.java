package com.example.myapplication.pagination;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.example.myapplication.R;
import com.example.myapplication.pagination.model.UserModel;
import com.example.myapplication.pagination.network.ApiClient;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PPActivity extends AppCompatActivity {
    private ArrayList<UserModel> userModalArrayList;
    private AdapterUser userRVAdapter;
    private RecyclerView userRV;
    private ProgressBar loadingPB;
    private NestedScrollView nestedSV;
    int page = 0, limit = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ppactivity);
        userModalArrayList = new ArrayList();


        getDataFromAPI(page, limit);

        userRV = findViewById(R.id.idRVUsers);
        loadingPB = findViewById(R.id.idPBLoading);
        nestedSV = findViewById(R.id.idNestedSV);


        nestedSV.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                // on scroll change we are checking when users scroll as bottom.
                if (scrollY == v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()) {
                    // in this method we are incrementing page number,
                    // making progress bar visible and calling get data method.
                    page++;
                    loadingPB.setVisibility(View.VISIBLE);
                    getDataFromAPI(page, limit);
                }
            }
        });
    }

    private void getDataFromAPI(int page, int limit) {
        if (page > limit) {
            Toast.makeText(this, "That's all the data..", Toast.LENGTH_SHORT).show();
            loadingPB.setVisibility(View.GONE);
            return;
        }

        ApiClient.getInstance().getUsers(page).enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if (response.isSuccessful()) {

                    userModalArrayList.add(
                            new UserModel("Bibina", "ghALE", "bb@gmail.com", "https://reqres.in/img/faces/6-image.jpg"));

                    // passing array list to our adapter class.
                    userRVAdapter = new AdapterUser(userModalArrayList, PPActivity.this);

                    // setting layout manager to our recycler view.
                    userRV.setLayoutManager(new LinearLayoutManager(PPActivity.this));

                    userRV.setAdapter(userRVAdapter);
                }


            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {

            }
        });
    }

    private void setAdapter(ArrayList<UserModel> userList) {
        AdapterUser adapter = new AdapterUser(userList, this);
        userRV.setAdapter(adapter);
    }
}



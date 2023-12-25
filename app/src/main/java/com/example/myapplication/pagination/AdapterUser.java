package com.example.myapplication.pagination;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.databinding.UserRvItemBinding;
import com.example.myapplication.pagination.model.UserModel;
import com.example.myapplication.pagination.response.Data;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterUser extends RecyclerView.Adapter<AdapterUser.ViewHolder> {
    private ArrayList<Data> userModalArrayList;
    private Context context;

    // creating a constructor.
    public AdapterUser(ArrayList<Data> userModalArrayList, Context context) {
        this.userModalArrayList = userModalArrayList;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        UserRvItemBinding userRvItemBinding = UserRvItemBinding.inflate(LayoutInflater.from(context), parent,
                false);
        return new ViewHolder(userRvItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Data userModal = userModalArrayList.get(position);
        holder.binding.idTVEmail.setText(userModal.getFirst_name());
        holder.binding.idTVLastName.setText(userModal.getLast_name());
        holder.binding.idTVEmail.setText(userModal.getEmail());
        Picasso.get().load(userModal.getAvatar()).into(holder.binding.idIVUser);
    }

    @Override
    public int getItemCount() {
        return userModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public UserRvItemBinding binding;

        public ViewHolder(@NonNull UserRvItemBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;

        }
    }
}

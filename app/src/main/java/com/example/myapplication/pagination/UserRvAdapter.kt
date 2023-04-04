package com.example.myapplication.pagination

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.UserRvItemBinding
import com.example.myapplication.pagination.model.UserModel
import com.squareup.picasso.Picasso


class UserRvAdapter(private var userList: List<UserModel>) :
    RecyclerView.Adapter<UserRvAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = UserRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)

    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // on below line we are setting data to our text view.
        // on below line we are setting data to our text view.


//        holder.F.setText(userModal.getFirst_name())
//        holder.lastNameTV.setText(userModal.getLast_name())
//        holder.emailTV.setText(userModal.getEmail())
//        Picasso.get().load(userModal.getAvatar()).into(holder.userIV)
    }

    inner class ViewHolder(val binding: UserRvItemBinding) :
        RecyclerView.ViewHolder(binding.root)

}


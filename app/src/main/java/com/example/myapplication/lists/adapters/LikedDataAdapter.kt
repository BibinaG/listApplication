package com.example.myapplication.lists.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.EmpoloyeListLayoutBinding
import com.example.myapplication.lists.EmployeData

class LikedDataAdapter(
    private var mList: List<EmployeData>,
) :
    RecyclerView.Adapter<LikedDataAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            EmpoloyeListLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var data = mList[position]
        holder.binding.Age.text = data.employeeAge.toString()
        holder.binding.Salary.text = data.employeeSalary.toString()
        holder.binding.tvName.text = data.employeeName
        holder.binding.deleteButton.visibility = View.VISIBLE
        holder.binding.like.visibility = View.GONE


        holder.binding.deleteButton.setOnClickListener {
            remove(data)

        }


    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(val binding: EmpoloyeListLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {


    }


    fun remove(item: EmployeData) {
        val list = mList.toMutableList()
        list.remove(item)
        mList = list
        notifyDataSetChanged()

    }


}

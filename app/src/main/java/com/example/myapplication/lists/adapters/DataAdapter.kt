package com.example.myapplication.lists.adapters

import android.service.autofill.OnClickAction
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.EmpoloyeListLayoutBinding
import com.example.myapplication.lists.EmployeData
import com.example.myapplication.lists.fragments.FragOne

class DataAdapter(
    private val mList: List<EmployeData>,
    private val onClickAction: (EmployeData) -> Unit
) :
    RecyclerView.Adapter<DataAdapter.ViewHolder>() {


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
        val data = mList[position]
//        holder.imageView.setImageResource(data.profileImage)
        holder.binding.Age.text = data.employeeAge.toString()
        holder.binding.Salary.text = data.employeeSalary.toString()
        holder.binding.tvName.text = data.employeeName

        holder.binding.like.setOnClickListener {
            onClickAction(data)

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
}

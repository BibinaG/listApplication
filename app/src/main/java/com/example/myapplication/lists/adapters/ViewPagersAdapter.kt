package com.example.myapplication.lists.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myapplication.lists.fragments.FragOne
import com.example.myapplication.lists.fragments.FragTwo

// ViewPagerAdapter.kt
class ViewPagersAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return 2 // Number of fragments
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FragOne()
            1 -> FragTwo()
            else -> throw IllegalArgumentException("Invalid position: $position")
        }
    }
}


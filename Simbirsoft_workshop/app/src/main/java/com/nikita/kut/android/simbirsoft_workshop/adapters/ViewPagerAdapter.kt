package com.nikita.kut.android.simbirsoft_workshop.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.nikita.kut.android.simbirsoft_workshop.screens.ItemViewPagerFragment

class ViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private var itemViewPagerFragments = arrayListOf<ItemViewPagerFragment>()

    fun updateList(newItemViewPagerFragments: ArrayList<ItemViewPagerFragment>) {
        itemViewPagerFragments = newItemViewPagerFragments
    }

    override fun getItemCount(): Int = itemViewPagerFragments.size

    override fun createFragment(position: Int): Fragment {
        return itemViewPagerFragments[position]
    }
}
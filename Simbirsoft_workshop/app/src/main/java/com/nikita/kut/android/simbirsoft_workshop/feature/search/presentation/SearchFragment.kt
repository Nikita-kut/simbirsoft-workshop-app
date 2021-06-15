package com.nikita.kut.android.simbirsoft_workshop.feature.search.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayoutMediator
import com.nikita.kut.android.simbirsoft_workshop.R
import com.nikita.kut.android.simbirsoft_workshop.feature.search.presentation.adapter.ViewPagerAdapter
import com.nikita.kut.android.simbirsoft_workshop.databinding.FragmentSearchBinding
import com.nikita.kut.android.simbirsoft_workshop.app.util.openFragment
import com.nikita.kut.android.simbirsoft_workshop.feature.help.presentation.HelpFragment
import com.nikita.kut.android.simbirsoft_workshop.feature.news.presentation.NewsFragment
import com.nikita.kut.android.simbirsoft_workshop.feature.profile.presentation.ProfileFragment

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding

    private val viewPagerAdapter: ViewPagerAdapter
        get() = binding.viewPager.adapter as ViewPagerAdapter

    private var itemViewPagerFragments: ArrayList<ItemViewPagerFragment> = arrayListOf(
        ItemViewPagerFragment(),
        ItemViewPagerFragment()
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.bnvSearch.selectedItemId = R.id.item_search
        setTabLayout()
        setBottomNavViewListener()
    }

    private fun setTabLayout() {
        binding.viewPager.adapter = ViewPagerAdapter(this)
        viewPagerAdapter.updateList(itemViewPagerFragments)
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.setText(R.string.search_events)
                1 -> tab.setText(R.string.search_nko)
            }
        }.attach()
    }

    private fun setBottomNavViewListener() {
        val onNavigateItemSelectListener =
            BottomNavigationView.OnNavigationItemSelectedListener { item ->
                return@OnNavigationItemSelectedListener when (item.itemId) {
                    R.id.item_news -> {
                        NewsFragment().openFragment(requireActivity())
                        true
                    }
                    R.id.item_search -> false
                    R.id.item_help -> {
                        HelpFragment().openFragment(requireActivity())
                        true
                    }
                    R.id.item_history -> false
                    R.id.item_profile -> {
                        ProfileFragment().openFragment(requireActivity())
                        true
                    }
                    else -> false
                }
            }
        binding.bnvSearch.setOnNavigationItemSelectedListener(onNavigateItemSelectListener)
    }

}
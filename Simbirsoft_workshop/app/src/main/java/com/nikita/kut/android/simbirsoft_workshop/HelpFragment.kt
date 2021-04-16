package com.nikita.kut.android.simbirsoft_workshop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nikita.kut.android.simbirsoft_workshop.adapters.FriendAdapter
import com.nikita.kut.android.simbirsoft_workshop.adapters.HelpCategoryAdapter
import com.nikita.kut.android.simbirsoft_workshop.data.HelpCategory
import com.nikita.kut.android.simbirsoft_workshop.databinding.FragmentHelpBinding
import com.nikita.kut.android.simbirsoft_workshop.util.ItemOffsetDecoration
import kotlin.random.Random

class HelpFragment : Fragment() {

    private lateinit var binding: FragmentHelpBinding

    private val categories = arrayListOf<HelpCategory>(
        HelpCategory(Random.nextLong(), R.drawable.icon_kids, R.string.children),
        HelpCategory(Random.nextLong(), R.drawable.icon_adult, R.string.adult),
        HelpCategory(Random.nextLong(), R.drawable.icon_elderly, R.string.elderly),
        HelpCategory(Random.nextLong(), R.drawable.icon_animals, R.string.animals),
        HelpCategory(Random.nextLong(), R.drawable.icon_events, R.string.events)
    )
    
    private val helpCategoryAdapter: HelpCategoryAdapter
        get() = binding.rvCategoryList.adapter as HelpCategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHelpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.bnvHelp.selectedItemId = R.id.item_help
        initCategoryList()
        setBottomNavViewListener()
    }

    private fun initCategoryList() {
        with(binding.rvCategoryList) {
            adapter = HelpCategoryAdapter()
            addItemDecoration(ItemOffsetDecoration(requireContext()))
            layoutManager =
                GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
        }
        helpCategoryAdapter.updateCategories(categories)
    }

    private fun setBottomNavViewListener() {
        val onNavigateItemSelectListener =
            BottomNavigationView.OnNavigationItemSelectedListener { item ->
                return@OnNavigationItemSelectedListener when (item.itemId) {
                    R.id.item_news -> false
                    R.id.item_search -> {
                        openFragment(SearchFragment())
                        true
                    }
                    R.id.item_help -> false
                    R.id.item_history -> false
                    R.id.item_profile -> {
                        openFragment(ProfileFragment())
                        true
                    }
                    else -> false
                }
            }
        binding.bnvHelp.setOnNavigationItemSelectedListener(onNavigateItemSelectListener)
    }

    private fun openFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}
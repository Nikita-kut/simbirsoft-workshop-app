package com.nikita.kut.android.simbirsoft_workshop

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nikita.kut.android.simbirsoft_workshop.adapters.HelpCategoryAdapter
import com.nikita.kut.android.simbirsoft_workshop.databinding.FragmentHelpBinding
import com.nikita.kut.android.simbirsoft_workshop.util.ItemOffsetDecoration
import com.nikita.kut.android.simbirsoft_workshop.util.openFragment
import com.nikita.kut.android.simbirsoft_workshop.viewmodel.HelpViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.util.*

class HelpFragment : Fragment() {

    private lateinit var binding: FragmentHelpBinding

    private val helpCategoryAdapter: HelpCategoryAdapter
        get() = binding.rvCategoryList.adapter as HelpCategoryAdapter

    private val helpViewModel: HelpViewModel by activityViewModels()

    private val categoriesScope = CoroutineScope(Dispatchers.Main)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHelpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadImitation()
        binding.bnvHelp.selectedItemId = R.id.item_help
        initCategoryList()
        setBottomNavViewListener()
    }

    private fun loadImitation() {
        categoriesScope.launch {
            helpViewModel.initCategoriesListFromDatabase()
            Thread.sleep(HelpViewModel.SLEEP_TIME)
            binding.progressBarHelp.visibility = View.GONE

        }
    }

    private fun initCategoryList() {
        with(binding.rvCategoryList) {
            adapter = HelpCategoryAdapter()
            addItemDecoration(ItemOffsetDecoration(requireContext()))
            layoutManager =
                GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
        }
        helpCategoryAdapter.updateCategories(helpViewModel.categories)
    }

    private fun setBottomNavViewListener() {
        val onNavigateItemSelectListener =
            BottomNavigationView.OnNavigationItemSelectedListener { item ->
                return@OnNavigationItemSelectedListener when (item.itemId) {
                    R.id.item_news -> {
                        NewsFragment().openFragment(requireActivity())
                        true
                    }
                    R.id.item_search -> {
                        SearchFragment().openFragment(requireActivity())
                        true
                    }
                    R.id.item_help -> false
                    R.id.item_history -> false
                    R.id.item_profile -> {
                        ProfileFragment().openFragment(requireActivity())
                        true
                    }
                    else -> false
                }
            }
        binding.bnvHelp.setOnNavigationItemSelectedListener(onNavigateItemSelectListener)
    }

}
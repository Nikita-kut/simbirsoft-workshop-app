package com.nikita.kut.android.simbirsoft_workshop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nikita.kut.android.simbirsoft_workshop.adapters.HelpCategoryAdapter
import com.nikita.kut.android.simbirsoft_workshop.data.HelpCategory
import com.nikita.kut.android.simbirsoft_workshop.databinding.FragmentHelpBinding
import com.nikita.kut.android.simbirsoft_workshop.util.ItemOffsetDecoration
import com.nikita.kut.android.simbirsoft_workshop.util.getJSONFromAssets
import com.nikita.kut.android.simbirsoft_workshop.util.openFragment
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.util.*

class HelpFragment : Fragment() {

    private lateinit var binding: FragmentHelpBinding

    private val categories = arrayListOf<HelpCategory>()

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

    override fun onResume() {
        super.onResume()
        convertHelpCategoryJsonToInstance()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.bnvHelp.selectedItemId = R.id.item_help
        initCategoryList()
        setBottomNavViewListener()
    }

    private fun convertHelpCategoryJsonToInstance() {
        val helpJSONString = getJSONFromAssets(requireActivity(), "categories.json")
        val moshi = Moshi.Builder().build()

        val listType = Types.newParameterizedType(List::class.java, HelpCategory::class.java)
        val adapter = moshi.adapter<List<HelpCategory>>(listType)

        val listCategory = adapter.fromJson(helpJSONString)
        for (i in listCategory!!.indices) {
            categories.add(listCategory[i])
        }
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
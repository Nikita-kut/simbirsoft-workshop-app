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
import com.nikita.kut.android.simbirsoft_workshop.util.openFragment
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setCategoriesListFromJson()
        binding.bnvHelp.selectedItemId = R.id.item_help
        initCategoryList()
        setBottomNavViewListener()
    }

    private fun setCategoriesListFromJson() {
        try {
            val obj = JSONObject(getJSONFromAssets()!!)
            val categoriesArray = obj.getJSONArray("categories")

            for (i in 0 until categoriesArray.length()) {
                val category = categoriesArray.getJSONObject(i)
                val id = category.getInt("id")
                val categoryName = category.getString("name")
                val imageResId = resources.getIdentifier(
                    category.getString("image"),
                    "drawable",
                    requireContext().packageName
                )
                val categoryForAdd = HelpCategory(id.toLong(), imageResId, categoryName)
                categories.add(categoryForAdd)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }


    private fun getJSONFromAssets(): String? {
        var json: String? = null
        val charset = Charsets.UTF_8
        try {
            val myUserJSONFile = requireActivity().assets.open("categories.json")
            val size = myUserJSONFile.available()
            val buffer = ByteArray(size)
            myUserJSONFile.read(buffer)
            myUserJSONFile.close()
            json = String(buffer, charset)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
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
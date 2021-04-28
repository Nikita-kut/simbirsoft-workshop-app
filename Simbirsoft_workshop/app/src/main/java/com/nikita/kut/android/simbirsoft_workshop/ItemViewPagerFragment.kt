package com.nikita.kut.android.simbirsoft_workshop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.nikita.kut.android.simbirsoft_workshop.adapters.SearchResultAdapter
import com.nikita.kut.android.simbirsoft_workshop.data.SearchResult
import com.nikita.kut.android.simbirsoft_workshop.databinding.ItemViewPagerBinding
import kotlin.random.Random

class ItemViewPagerFragment : Fragment() {

    private lateinit var binding: ItemViewPagerBinding

    private val searchResultAdapter: SearchResultAdapter
        get() = binding.rvSearchResult.adapter as SearchResultAdapter

    private val searchResults = arrayListOf(
        SearchResult(Random.nextLong(), R.string.search_result_1),
        SearchResult(Random.nextLong(), R.string.search_result_2),
        SearchResult(Random.nextLong(), R.string.search_result_3),
        SearchResult(Random.nextLong(), R.string.search_result_4),
        SearchResult(Random.nextLong(), R.string.search_result_5)
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ItemViewPagerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initList()
    }

    private fun initList() {
        with(binding.rvSearchResult) {
            adapter = SearchResultAdapter()
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
//            layoutManager = MaxCountLayoutManager(
//                requireContext()
//            ).apply { setMaxCount(5) }
        }
        searchResultAdapter.updateSearchResults(searchResults)
    }


}
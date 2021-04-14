package com.nikita.kut.android.simbirsoft_workshop.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nikita.kut.android.simbirsoft_workshop.R
import com.nikita.kut.android.simbirsoft_workshop.data.SearchResult
import com.nikita.kut.android.simbirsoft_workshop.databinding.ItemSearchResultBinding

class SearchResultAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var searchResults = arrayListOf<SearchResult>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_search_result, parent, false)
        return SearchResultViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is SearchResultViewHolder) {
            val searchResult = searchResults[position]
            holder.bind(searchResult)
        }
    }

    override fun getItemCount(): Int = searchResults.size

    fun updateSearchResults(newSearchResults: ArrayList<SearchResult>) {
        searchResults = newSearchResults
    }

    class SearchResultViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemSearchResultBinding.bind(view)

        fun bind(searchResult: SearchResult) {
            binding.tvSearchResult.setText(searchResult.searchResult)
        }

    }
}
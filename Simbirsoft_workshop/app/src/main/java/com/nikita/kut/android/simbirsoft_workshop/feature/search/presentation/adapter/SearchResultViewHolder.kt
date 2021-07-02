package com.nikita.kut.android.simbirsoft_workshop.feature.search.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.nikita.kut.android.simbirsoft_workshop.feature.search.model.SearchResult
import com.nikita.kut.android.simbirsoft_workshop.databinding.ItemSearchResultBinding

class SearchResultViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemSearchResultBinding.bind(view)

    fun bind(searchResult: SearchResult) {
        binding.tvSearchResult.setText(searchResult.searchResult)
    }

}
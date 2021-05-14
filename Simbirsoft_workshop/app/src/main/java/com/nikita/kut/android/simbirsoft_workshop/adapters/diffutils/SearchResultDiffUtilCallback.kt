package com.nikita.kut.android.simbirsoft_workshop.adapters.diffutils

import androidx.recyclerview.widget.DiffUtil
import com.nikita.kut.android.simbirsoft_workshop.model.SearchResult

class SearchResultDiffUtilCallback : DiffUtil.ItemCallback<SearchResult>() {

    override fun areItemsTheSame(oldItem: SearchResult, newItem: SearchResult): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: SearchResult, newItem: SearchResult): Boolean =
        oldItem == newItem
}
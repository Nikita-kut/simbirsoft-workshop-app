package com.nikita.kut.android.simbirsoft_workshop.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.nikita.kut.android.simbirsoft_workshop.R
import com.nikita.kut.android.simbirsoft_workshop.adapters.diffutils.SearchResultDiffUtilCallback
import com.nikita.kut.android.simbirsoft_workshop.adapters.viewholders.SearchResultViewHolder
import com.nikita.kut.android.simbirsoft_workshop.data.SearchResult

class SearchResultAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val differ = AsyncListDiffer(this, SearchResultDiffUtilCallback())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_search_result, parent, false)
        return SearchResultViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is SearchResultViewHolder) {
            val searchResult = differ.currentList[position]
            holder.bind(searchResult)
        }
    }

    override fun getItemCount(): Int = differ.currentList.size

    fun updateSearchResults(newSearchResults: ArrayList<SearchResult>) {
        differ.submitList(newSearchResults)
    }
}
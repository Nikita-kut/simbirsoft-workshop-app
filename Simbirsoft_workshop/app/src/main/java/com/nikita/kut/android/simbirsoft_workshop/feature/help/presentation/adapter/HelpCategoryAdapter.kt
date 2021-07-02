package com.nikita.kut.android.simbirsoft_workshop.feature.help.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.nikita.kut.android.simbirsoft_workshop.R
import com.nikita.kut.android.simbirsoft_workshop.app.data.db.model.HelpCategory

class HelpCategoryAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val differ = AsyncListDiffer(this, HelpCategoryDiffUtilCallBack())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_help, parent, false)
        return HelpCategoryHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is HelpCategoryHolder) {
            val category = differ.currentList[position]
            holder.bind(category)
        }
    }

    override fun getItemCount(): Int = differ.currentList.size

    fun updateCategories(newCategories: ArrayList<HelpCategory>) {
        differ.submitList(newCategories)
    }
}
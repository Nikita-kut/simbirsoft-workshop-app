package com.nikita.kut.android.simbirsoft_workshop.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nikita.kut.android.simbirsoft_workshop.R
import com.nikita.kut.android.simbirsoft_workshop.data.HelpCategory
import com.nikita.kut.android.simbirsoft_workshop.databinding.ItemHelpBinding

class HelpCategoryAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var categories: ArrayList<HelpCategory> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_help, parent, false)
        return HelpCategoryHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is HelpCategoryHolder) {
            val category = categories[position]
            holder.bind(category)
        }
    }

    override fun getItemCount(): Int = categories.size

    fun updateCategories(newCategories: ArrayList<HelpCategory>) {
        categories = newCategories
    }

    class HelpCategoryHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemHelpBinding.bind(view)

        fun bind(helpCategory: HelpCategory) {
            binding.ivItemHelp.setImageResource(helpCategory.drawableRes)
            binding.tvItemHelp.text = helpCategory.category
        }
    }
}
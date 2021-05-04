package com.nikita.kut.android.simbirsoft_workshop.adapters.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nikita.kut.android.simbirsoft_workshop.data.HelpCategory
import com.nikita.kut.android.simbirsoft_workshop.databinding.ItemHelpBinding

class HelpCategoryHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemHelpBinding.bind(view)

    fun bind(helpCategory: HelpCategory) {
        Glide.with(itemView)
            .load(
                itemView.resources.getIdentifier(
                    helpCategory.drawableRes,
                    "drawable",
                    itemView.context.packageName
                )
            )
            .into(binding.ivItemHelp)
        binding.tvItemHelp.text = helpCategory.category
    }
}
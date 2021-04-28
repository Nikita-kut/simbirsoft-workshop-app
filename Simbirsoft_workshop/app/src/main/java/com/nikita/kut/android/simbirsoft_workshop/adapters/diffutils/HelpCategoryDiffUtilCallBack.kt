package com.nikita.kut.android.simbirsoft_workshop.adapters.diffutils

import androidx.recyclerview.widget.DiffUtil
import com.nikita.kut.android.simbirsoft_workshop.data.HelpCategory

class HelpCategoryDiffUtilCallBack : DiffUtil.ItemCallback<HelpCategory>() {

    override fun areItemsTheSame(oldItem: HelpCategory, newItem: HelpCategory): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: HelpCategory, newItem: HelpCategory): Boolean =
        oldItem == newItem
}
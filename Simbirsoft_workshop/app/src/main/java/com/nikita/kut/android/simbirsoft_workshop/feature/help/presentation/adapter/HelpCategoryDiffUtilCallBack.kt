package com.nikita.kut.android.simbirsoft_workshop.feature.help.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.nikita.kut.android.simbirsoft_workshop.app.data.db.model.HelpCategory

class HelpCategoryDiffUtilCallBack : DiffUtil.ItemCallback<HelpCategory>() {

    override fun areItemsTheSame(oldItem: HelpCategory, newItem: HelpCategory): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: HelpCategory, newItem: HelpCategory): Boolean =
        oldItem == newItem
}
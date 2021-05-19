package com.nikita.kut.android.simbirsoft_workshop.adapters.diffutils

import androidx.recyclerview.widget.DiffUtil
import com.nikita.kut.android.simbirsoft_workshop.model.News

class NewsDiffUtilCallback : DiffUtil.ItemCallback<News>() {

    override fun areItemsTheSame(oldItem: News, newItem: News): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: News, newItem: News): Boolean = oldItem == newItem
}
package com.nikita.kut.android.simbirsoft_workshop.feature.news.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.nikita.kut.android.simbirsoft_workshop.app.data.db.model.News

class NewsDiffUtilCallback : DiffUtil.ItemCallback<News>() {

    override fun areItemsTheSame(oldItem: News, newItem: News): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: News, newItem: News): Boolean = oldItem == newItem
}
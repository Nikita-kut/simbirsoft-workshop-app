package com.nikita.kut.android.simbirsoft_workshop.feature.profile.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.nikita.kut.android.simbirsoft_workshop.feature.profile.model.Friend

class FriendDiffUtilCallback : DiffUtil.ItemCallback<Friend>() {

    override fun areItemsTheSame(oldItem: Friend, newItem: Friend): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Friend, newItem: Friend): Boolean = oldItem == newItem
}
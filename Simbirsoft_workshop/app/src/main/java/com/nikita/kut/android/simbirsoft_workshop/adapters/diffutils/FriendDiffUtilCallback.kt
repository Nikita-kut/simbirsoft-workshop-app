package com.nikita.kut.android.simbirsoft_workshop.adapters.diffutils

import androidx.recyclerview.widget.DiffUtil
import com.nikita.kut.android.simbirsoft_workshop.model.Friend

class FriendDiffUtilCallback : DiffUtil.ItemCallback<Friend>() {

    override fun areItemsTheSame(oldItem: Friend, newItem: Friend): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Friend, newItem: Friend): Boolean = oldItem == newItem
}
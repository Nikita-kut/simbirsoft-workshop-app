package com.nikita.kut.android.simbirsoft_workshop.adapters.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.nikita.kut.android.simbirsoft_workshop.data.Friend
import com.nikita.kut.android.simbirsoft_workshop.databinding.ItemFriendBinding

class FriendHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemFriendBinding.bind(view)

    fun bind(friend: Friend) {
        binding.tvName.setText(friend.name)
        binding.avatar.setImageResource(friend.drawableRes)
    }
}
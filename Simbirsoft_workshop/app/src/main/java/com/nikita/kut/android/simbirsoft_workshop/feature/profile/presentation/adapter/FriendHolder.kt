package com.nikita.kut.android.simbirsoft_workshop.feature.profile.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.nikita.kut.android.simbirsoft_workshop.R
import com.nikita.kut.android.simbirsoft_workshop.feature.profile.model.Friend
import com.nikita.kut.android.simbirsoft_workshop.databinding.ItemFriendBinding

class FriendHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemFriendBinding.bind(view)

    fun bind(friend: Friend) {
        binding.tvName.setText(friend.name)
        Glide.with(itemView)
            .load(friend.drawableRes)
            .placeholder(R.drawable.ic_no_photo)
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
            .dontAnimate()
            .into(binding.avatar)
    }
}
package com.nikita.kut.android.simbirsoft_workshop.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nikita.kut.android.simbirsoft_workshop.R
import com.nikita.kut.android.simbirsoft_workshop.data.Friend
import com.nikita.kut.android.simbirsoft_workshop.databinding.ItemFriendBinding

class FriendAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var friends: ArrayList<Friend> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_friend, parent, false)
        return FriendHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is FriendHolder) {
            val friend = friends[position]
            holder.bind(friend)
        }

    }

    override fun getItemCount(): Int = friends.size


    fun updateListFriends(newListFriends: ArrayList<Friend>) {
        friends = newListFriends
    }

    class FriendHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemFriendBinding.bind(view)

        fun bind(friend: Friend) {
            binding.tvName.setText(friend.name)
            binding.avatar.setImageResource(friend.drawableRes)
        }
    }


}
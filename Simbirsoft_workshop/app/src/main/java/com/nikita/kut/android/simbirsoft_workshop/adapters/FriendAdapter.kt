package com.nikita.kut.android.simbirsoft_workshop.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.nikita.kut.android.simbirsoft_workshop.R
import com.nikita.kut.android.simbirsoft_workshop.adapters.diffutils.FriendDiffUtilCallback
import com.nikita.kut.android.simbirsoft_workshop.adapters.viewholders.FriendHolder
import com.nikita.kut.android.simbirsoft_workshop.model.Friend

class FriendAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private val differ = AsyncListDiffer(this, FriendDiffUtilCallback())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_friend, parent, false)
        return FriendHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is FriendHolder) {
            val friend = differ.currentList[position]
            holder.bind(friend)
        }

    }

    override fun getItemCount(): Int = differ.currentList.size


    fun updateListFriends(newListFriends: ArrayList<Friend>) {
        differ.submitList(newListFriends)
    }

}
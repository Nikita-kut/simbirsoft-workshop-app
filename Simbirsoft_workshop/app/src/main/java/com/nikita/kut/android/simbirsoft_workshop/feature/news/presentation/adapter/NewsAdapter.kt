package com.nikita.kut.android.simbirsoft_workshop.feature.news.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.nikita.kut.android.simbirsoft_workshop.R
import com.nikita.kut.android.simbirsoft_workshop.app.data.db.model.News

class NewsAdapter(newsListener: OnNewsClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val differ = AsyncListDiffer(this, NewsDiffUtilCallback())
    private val mNewsListener: OnNewsClickListener = newsListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_news, parent, false)
        return NewsHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is NewsHolder) {
            val news = differ.currentList[position]
            holder.bind(news, mNewsListener)
        }
    }

    override fun getItemCount(): Int = differ.currentList.size

    fun updateNewsList(newNews: ArrayList<News>) {
        differ.submitList(newNews)
    }

    interface OnNewsClickListener {
        fun onNewsClick(position: Int)
    }


}
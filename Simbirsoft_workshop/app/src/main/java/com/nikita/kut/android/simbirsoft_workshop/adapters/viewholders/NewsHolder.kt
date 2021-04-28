package com.nikita.kut.android.simbirsoft_workshop.adapters.viewholders

import android.net.Uri
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nikita.kut.android.simbirsoft_workshop.adapters.NewsAdapter
import com.nikita.kut.android.simbirsoft_workshop.data.News
import com.nikita.kut.android.simbirsoft_workshop.databinding.ItemNewsBinding

class NewsHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
    private val binding = ItemNewsBinding.bind(view)
    lateinit var newsListener: NewsAdapter.OnNewsClickListener

    fun bind(news: News, newsListener: NewsAdapter.OnNewsClickListener) {
        Glide.with(itemView)
            .load(Uri.parse(news.newsPicture))
            .into(binding.ivNewsPicture)
        binding.tvNewsTitle.text = news.newsTitle
        binding.tvBodyNews.text = news.newsBody
        binding.tvNewsDate.text = news.newsDate
        this.newsListener = newsListener
        itemView.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        newsListener.onNewsClick(adapterPosition)
    }
}
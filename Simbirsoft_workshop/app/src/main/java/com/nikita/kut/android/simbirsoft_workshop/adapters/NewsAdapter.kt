package com.nikita.kut.android.simbirsoft_workshop.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.nikita.kut.android.simbirsoft_workshop.R
import com.nikita.kut.android.simbirsoft_workshop.data.HelpCategory
import com.nikita.kut.android.simbirsoft_workshop.data.News
import com.nikita.kut.android.simbirsoft_workshop.databinding.ItemNewsBinding

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

    class NewsDiffUtilCallback : DiffUtil.ItemCallback<News>() {

        override fun areItemsTheSame(oldItem: News, newItem: News): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: News, newItem: News): Boolean = oldItem == newItem
    }


    class NewsHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        private val binding = ItemNewsBinding.bind(view)
        lateinit var newsListener: OnNewsClickListener

        fun bind(news: News, newsListener: OnNewsClickListener) {
            binding.ivNewsPicture.setImageResource(news.newsPicture)
            binding.tvNewsTitle.setText(news.newsTitle)
            binding.tvBodyNews.setText(news.newsBody)
            binding.tvNewsDate.setText(news.newsDate)
            this.newsListener = newsListener
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            newsListener.onNewsClick(adapterPosition)
        }
    }

    interface OnNewsClickListener {
        fun onNewsClick(position: Int)
    }


}
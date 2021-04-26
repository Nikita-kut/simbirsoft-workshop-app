package com.nikita.kut.android.simbirsoft_workshop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nikita.kut.android.simbirsoft_workshop.data.News
import com.nikita.kut.android.simbirsoft_workshop.databinding.FragmentNewsItemBinding
import kotlin.random.Random

class NewsItemFragment : Fragment() {

    private lateinit var binding: FragmentNewsItemBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    private val defaultNews by lazy {
        News(
        id = Random.nextLong(),
        newsPicture = R.drawable.ic_no_photo,
        newsTitle = resources.getString(R.string.animals),
        newsBody = resources.getString(R.string.delete),
        newsDate = resources.getString(R.string.delete),
        categoriesOfHelp = arrayListOf()
    )
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setDisplayParams()
        setNavigationArrow()
    }

    private fun setDisplayParams() {
        val news = requireArguments().getParcelable(KEY_NEWS) ?: defaultNews
        binding.tvTitleItemNews.text = news.newsTitle
        binding.tvNewsItemTitle.text = news.newsTitle
        binding.tvNewsItemDate.text = news.newsDate
        binding.picture1.setImageResource(news.newsPicture)
        binding.tvNewsItemBody.text = news.newsBody
    }

    private fun setNavigationArrow() {
        binding.toolbarItemNews.setNavigationOnClickListener {
            requireFragmentManager().popBackStack()
        }
    }


    companion object {
        private const val KEY_NEWS = "KEY_NEWS"

        fun newInstanceWithArgs(news: News): NewsItemFragment {
            val fragment = NewsItemFragment()
            val args = Bundle().apply {
                putParcelable(KEY_NEWS, news)
            }
            fragment.arguments = args
            return fragment
        }

    }

}
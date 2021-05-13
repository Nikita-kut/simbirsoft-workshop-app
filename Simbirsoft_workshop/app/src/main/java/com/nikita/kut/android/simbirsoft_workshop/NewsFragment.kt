package com.nikita.kut.android.simbirsoft_workshop

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nikita.kut.android.simbirsoft_workshop.adapters.NewsAdapter
import com.nikita.kut.android.simbirsoft_workshop.adapters.NewsAdapter.OnNewsClickListener
import com.nikita.kut.android.simbirsoft_workshop.data.News
import com.nikita.kut.android.simbirsoft_workshop.data.SharedPreferenceModel
import com.nikita.kut.android.simbirsoft_workshop.databinding.FragmentNewsBinding
import com.nikita.kut.android.simbirsoft_workshop.util.openFragment
import com.nikita.kut.android.simbirsoft_workshop.util.openFragmentWithAddBackStack
import com.nikita.kut.android.simbirsoft_workshop.viewmodel.NewsViewModel
import com.nikita.kut.android.simbirsoft_workshop.viewmodel.NewsViewModel.Companion.SLEEP_TIME

class NewsFragment : Fragment(), FilterFragment.ClickListener, OnNewsClickListener {

    private lateinit var binding: FragmentNewsBinding

    private val newsAdapter: NewsAdapter
        get() = binding.rvListNews.adapter as NewsAdapter

    private val newsViewModel: NewsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        loadImitation()
        binding.bnvNews.selectedItemId = R.id.item_news
        SharedPreferenceModel.with(requireActivity().application)
        setBottomNavViewListener()
        initList()
        setMenu()
        onCheckClick()
    }

    private fun loadImitation() {
        newsViewModel.initNewsFromDatabase()
        Handler().postDelayed({
            Thread.sleep(SLEEP_TIME)
            binding.progressBarNews.visibility = View.GONE
        }, 0)
    }

    private fun setBottomNavViewListener() {
        val onNavigateItemSelectListener =
            BottomNavigationView.OnNavigationItemSelectedListener { item ->
                return@OnNavigationItemSelectedListener when (item.itemId) {
                    R.id.item_news -> false
                    R.id.item_search -> {
                        SearchFragment().openFragment(requireActivity())
                        true
                    }
                    R.id.item_help -> {
                        HelpFragment().openFragment(requireActivity())
                        true
                    }
                    R.id.item_history -> false
                    R.id.item_profile -> {
                        ProfileFragment().openFragment(requireActivity())
                        true
                    }
                    else -> false
                }
            }
        binding.bnvNews.setOnNavigationItemSelectedListener(onNavigateItemSelectListener)
    }


    private fun initList() {
        with(binding.rvListNews) {
            adapter = NewsAdapter(this@NewsFragment)
            layoutManager =
                LinearLayoutManager(
                    requireContext(),
                    LinearLayoutManager.VERTICAL,
                    false
                )
        }
        newsAdapter.updateNewsList(newsViewModel.newsList)
    }

    private fun setMenu() {
        binding.toolbarNews.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.item_filter -> {
                    FilterFragment().openFragmentWithAddBackStack(requireActivity())
                    true
                }
                else -> false
            }
        }
    }

    override fun onCheckClick() {
        newsViewModel.newsFilteredList = newsViewModel.newsList.filter { news ->
            news.categoriesOfHelp.map { category -> category.name }
                .intersect(newsViewModel.getCategoriesList().toSet())
                .isNotEmpty()
        } as ArrayList<News>
        newsAdapter.updateNewsList(newsViewModel.newsFilteredList)
    }

    override fun onNewsClick(position: Int) {
        NewsItemFragment.newInstanceWithArgs(newsViewModel.newsFilteredList[position])
            .openFragmentWithAddBackStack(requireActivity())
    }
}
package com.nikita.kut.android.simbirsoft_workshop.screens

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nikita.kut.android.simbirsoft_workshop.R
import com.nikita.kut.android.simbirsoft_workshop.adapters.NewsAdapter
import com.nikita.kut.android.simbirsoft_workshop.adapters.NewsAdapter.OnNewsClickListener
import com.nikita.kut.android.simbirsoft_workshop.model.News
import com.nikita.kut.android.simbirsoft_workshop.util.SharedPreferenceModel
import com.nikita.kut.android.simbirsoft_workshop.databinding.FragmentNewsBinding
import com.nikita.kut.android.simbirsoft_workshop.util.openFragment
import com.nikita.kut.android.simbirsoft_workshop.util.openFragmentWithAddBackStack
import com.nikita.kut.android.simbirsoft_workshop.viewmodel.NewsViewModel
import com.nikita.kut.android.simbirsoft_workshop.viewmodel.NewsViewModel.Companion.SLEEP_TIME
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsFragment : Fragment(), FilterFragment.ClickListener, OnNewsClickListener {

    private lateinit var binding: FragmentNewsBinding

    private val newsAdapter: NewsAdapter
        get() = binding.rvListNews.adapter as NewsAdapter

    private val newsViewModel: NewsViewModel by activityViewModels()

    private val newsScope = CoroutineScope(Dispatchers.Main)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) {
            newsScope.launch {
                newsViewModel.insertCategoriesListFromFirebaseToRoom()
            }
        }
        loadImitation()
        binding.bnvNews.selectedItemId = R.id.item_news
        SharedPreferenceModel.with(requireActivity().application)
        setBottomNavViewListener()
        initList()
        setMenu()
        onCheckClick()
    }

    private fun loadImitation() {
        newsScope.launch {
            newsViewModel.getNewsList()
            Thread.sleep(SLEEP_TIME)
            binding.progressBarNews.visibility = View.GONE
        }
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
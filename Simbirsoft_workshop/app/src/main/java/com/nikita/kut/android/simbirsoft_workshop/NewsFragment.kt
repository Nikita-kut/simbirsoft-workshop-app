package com.nikita.kut.android.simbirsoft_workshop

import android.opengl.Visibility
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nikita.kut.android.simbirsoft_workshop.adapters.NewsAdapter
import com.nikita.kut.android.simbirsoft_workshop.adapters.NewsAdapter.OnNewsClickListener
import com.nikita.kut.android.simbirsoft_workshop.data.CategoriesOfHelp
import com.nikita.kut.android.simbirsoft_workshop.data.News
import com.nikita.kut.android.simbirsoft_workshop.data.SharedPreferenceModel
import com.nikita.kut.android.simbirsoft_workshop.databinding.FragmentNewsBinding
import com.nikita.kut.android.simbirsoft_workshop.util.getJSONFromAssets
import com.nikita.kut.android.simbirsoft_workshop.util.openFragment
import com.nikita.kut.android.simbirsoft_workshop.util.openFragmentWithAddBackStack
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NewsFragment : Fragment(), FilterFragment.ClickListener, OnNewsClickListener {

    private lateinit var binding: FragmentNewsBinding

    private val newsAdapter: NewsAdapter
        get() = binding.rvListNews.adapter as NewsAdapter

    private var news = arrayListOf<News>()

    private var newNews = news

    private var keyList = listOf<String>()

    private val newsScope = CoroutineScope(Dispatchers.Default)

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
        coroutineStart()
        binding.bnvNews.selectedItemId = R.id.item_news
        SharedPreferenceModel.with(requireActivity().application)
        setBottomNavViewListener()
        initList()
        setMenu()
        onCheckClick()
    }

    private fun coroutineStart() {
        newsScope.launch {
            Log.d(TAG, "Coroutine inside from thread = ${Thread.currentThread().name}")
            news = convertNewsJsonToInstance()
        }
        Log.d(TAG, "Coroutine launched from thread = ${Thread.currentThread().name}")
        Handler().postDelayed({
            Log.d(TAG, "App from thread = ${Thread.currentThread().name}")
            Thread.sleep(SLEEP_TIME)
            binding.progressBarNews.visibility = View.GONE
            Log.d(TAG, "App from thread = ${Thread.currentThread().name}")
        }, 0)
    }

    private fun convertNewsJsonToInstance(): ArrayList<News> {
        val newsJSONString = getJSONFromAssets(requireActivity(), "news.json")
        val moshi = Moshi.Builder().build()

        val listType = Types.newParameterizedType(List::class.java, News::class.java)
        val adapter = moshi.adapter<List<News>>(listType)

        val newsFromJson = adapter.fromJson(newsJSONString)
        return newsFromJson as ArrayList<News>
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
        newsAdapter.updateNewsList(news)
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

    private fun getCategoriesList(): List<String> {
        val children =
            SharedPreferenceModel.get<CategoriesOfHelp>(FilterFragment.KEY_CHILDREN).toString()
        val adult =
            SharedPreferenceModel.get<CategoriesOfHelp>(FilterFragment.KEY_ADULT).toString()
        val elderly =
            SharedPreferenceModel.get<CategoriesOfHelp>(FilterFragment.KEY_ELDERLY).toString()
        val animals =
            SharedPreferenceModel.get<CategoriesOfHelp>(FilterFragment.KEY_ANIMALS).toString()
        val events =
            SharedPreferenceModel.get<CategoriesOfHelp>(FilterFragment.KEY_EVENTS).toString()
        return listOf(children, adult, elderly, animals, events)
    }

    override fun onCheckClick() {
        keyList = getCategoriesList()
        newNews = news.filter { news ->
            news.categoriesOfHelp.map { category -> category.name }
                .intersect(keyList.toSet())
                .isNotEmpty()
        } as ArrayList<News>
        newsAdapter.updateNewsList(newNews)
    }

    override fun onNewsClick(position: Int) {
        NewsItemFragment.newInstanceWithArgs(newNews[position])
            .openFragmentWithAddBackStack(requireActivity())
    }

    companion object {
        private const val TAG = "news_fragment"
        private const val SLEEP_TIME = 5000L
    }
}
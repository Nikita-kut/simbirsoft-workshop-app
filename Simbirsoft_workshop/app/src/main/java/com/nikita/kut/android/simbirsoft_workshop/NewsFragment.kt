package com.nikita.kut.android.simbirsoft_workshop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nikita.kut.android.simbirsoft_workshop.adapters.NewsAdapter
import com.nikita.kut.android.simbirsoft_workshop.adapters.NewsAdapter.OnNewsClickListener
import com.nikita.kut.android.simbirsoft_workshop.data.CategoriesOfHelp
import com.nikita.kut.android.simbirsoft_workshop.data.HelpCategory
import com.nikita.kut.android.simbirsoft_workshop.data.News
import com.nikita.kut.android.simbirsoft_workshop.data.SharedPreferenceModel
import com.nikita.kut.android.simbirsoft_workshop.databinding.FragmentNewsBinding
import com.nikita.kut.android.simbirsoft_workshop.util.openFragment
import com.nikita.kut.android.simbirsoft_workshop.util.openFragmentWithAddBackStack
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import kotlin.random.Random

class NewsFragment : Fragment(), FilterFragment.ClickListener, OnNewsClickListener {

    private lateinit var binding: FragmentNewsBinding

    private val newsAdapter: NewsAdapter
        get() = binding.rvListNews.adapter as NewsAdapter

    private val news = arrayListOf<News>()

    private var newNews = news

    private var keyList = arrayListOf<String>()

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
        setNewsListFromJson()
        binding.bnvNews.selectedItemId = R.id.item_news
        SharedPreferenceModel.with(requireActivity().application)
        setBottomNavViewListener()
        initList()
        setMenu()
        onCheckClick()
    }

    private fun setNewsListFromJson() {
        try {
            val obj = JSONObject(getJSONFromAssets())
            val newsArray = obj.getJSONArray("news")

            for (i in 0 until newsArray.length()) {
                val currentNews = newsArray.getJSONObject(i)
                val id = currentNews.getInt("id").toLong()
                val imageResId = resources.getIdentifier(
                    currentNews.getString("image"),
                    "drawable",
                    requireContext().packageName
                )
                val title = currentNews.getString("title")
                val body = currentNews.getString("body")
                val date = currentNews.getString("date")

                val categoriesArray = currentNews.getJSONArray("category")
                val enumCategory: ArrayList<CategoriesOfHelp> = arrayListOf()
                for (index in 0 until categoriesArray.length()) {
                    val currentCategory = categoriesArray.getJSONObject(index)
                    val children = currentCategory.getString("children")
                    if (children.length > 2) enumCategory.add(CategoriesOfHelp.CHILDREN)
                    val adult = currentCategory.getString("adult")
                    if (adult.length > 2) enumCategory.add(CategoriesOfHelp.ADULT)
                    val elderly = currentCategory.getString("elderly")
                    if (elderly.length > 2) enumCategory.add(CategoriesOfHelp.ELDERLY)
                    val animals = currentCategory.getString("animals")
                    if (animals.length > 2) enumCategory.add(CategoriesOfHelp.ANIMALS)
                    val events = currentCategory.getString("events")
                    if (events.length > 2) enumCategory.add(CategoriesOfHelp.EVENTS)
                }
                val newsForAdd = News(id, imageResId, title, body, date, enumCategory)
                news.add(newsForAdd)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }


    private fun getJSONFromAssets(): String {
        var json: String
        val charset = Charsets.UTF_8
        try {
            val myUserJSONFile = requireActivity().assets.open("news.json")
            val size = myUserJSONFile.available()
            val buffer = ByteArray(size)
            myUserJSONFile.read(buffer)
            myUserJSONFile.close()
            json = String(buffer, charset)
        } catch (ex: IOException) {
            ex.printStackTrace()
            json = String(ByteArray(1), charset)
        }
        return json
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

    private fun getCategoriesList(): ArrayList<String> {
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
        return arrayListOf(children, adult, elderly, animals, events)
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
}
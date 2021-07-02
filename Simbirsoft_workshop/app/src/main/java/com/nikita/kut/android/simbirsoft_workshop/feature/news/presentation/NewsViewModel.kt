package com.nikita.kut.android.simbirsoft_workshop.feature.news.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.*
import com.nikita.kut.android.simbirsoft_workshop.app.data.db.NewsDB
import com.nikita.kut.android.simbirsoft_workshop.feature.news.model.CategoriesOfHelp
import com.nikita.kut.android.simbirsoft_workshop.app.data.db.model.News
import com.nikita.kut.android.simbirsoft_workshop.feature.filter.presentation.FilterFragment
import com.nikita.kut.android.simbirsoft_workshop.app.data.prefernce.SharedPreferenceModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {

    var newsList: ArrayList<News> = arrayListOf()

    var newsFilteredList = newsList

    private var newsFirebaseList = arrayListOf<News>()

    private var categoriesList = listOf<String>()

    private val firebaseDB = FirebaseDatabase.getInstance().getReference(NEWS_DB_TAG)

    private val newsDao = NewsDB.newsDBInstance.newsDao()

    fun getNewsList() {
        viewModelScope.launch {
            newsDao.getAllNews().collect {
                newsList = it as ArrayList<News>
            }
        }
    }

    suspend fun insertCategoriesListFromFirebaseToRoom() {
        newsDao.insertCategories(initNewsListFromDatabase())
    }

    private fun initNewsListFromDatabase(): ArrayList<News> {
        firebaseDB.addValueEventListener(
            object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.hasChild(NEWS_DB_TAG)) {
                        viewModelScope.launch {
                            val memberList = snapshot.child(NEWS_DB_TAG).value as ArrayList<*>
                            newsFirebaseList = getNewsList(memberList)
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
        return newsFirebaseList
    }

    private fun getNewsList(newsList: ArrayList<*>): ArrayList<News> {
        val size = newsList.size
        var index = 0
        val userList = ArrayList<News>()

        while (index < size) {
            val hashMap: HashMap<*, *> = newsList[index] as HashMap<*, *>
            val id = hashMap["id"].toString().toLong()
            val image = hashMap["image"].toString()
            val title = hashMap["title"].toString()
            val body = hashMap["body"].toString()
            val date = hashMap["date"].toString()
            val categoryString = hashMap["category"].toString()
            val category =
                CategoriesOfHelp.valueOf(categoryString.substring(1, categoryString.length - 1))
            userList.add(News(id, title, image, body, date, listOf(category)))
            index++
        }
        return userList
    }

    fun getCategoriesList(): List<String> {
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
        categoriesList = listOf(children, adult, elderly, animals, events)
        return categoriesList
    }

    companion object {
        const val TAG_NEWS_FRAGMENT = "news_fragment"
        const val NEWS_DB_TAG = "news"
        const val SLEEP_TIME = 2000L
    }
}
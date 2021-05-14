package com.nikita.kut.android.simbirsoft_workshop.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.database.*
import com.nikita.kut.android.simbirsoft_workshop.screens.FilterFragment
import com.nikita.kut.android.simbirsoft_workshop.model.CategoriesOfHelp
import com.nikita.kut.android.simbirsoft_workshop.model.News
import com.nikita.kut.android.simbirsoft_workshop.util.SharedPreferenceModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {

    var newsList: ArrayList<News> = arrayListOf()

    var newsFilteredList = newsList

    private var categoriesList = listOf<String>()

    private val newsScope = CoroutineScope(Dispatchers.Default)

    private var database = FirebaseDatabase.getInstance().getReference(NEWS_DB_TAG)

    fun initNewsFromDatabase() {
        database.addValueEventListener(
            object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.hasChild(NEWS_DB_TAG)) {
                        newsScope.launch {
                            Log.d(
                                TAG_NEWS_FRAGMENT,
                                "Coroutine inside from thread = ${Thread.currentThread().name}"
                            )
                            val memberList = snapshot.child(NEWS_DB_TAG).value as ArrayList<*>
                            newsList = getNewsList(memberList)
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
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

//    fun initNewsFromJsonAssets(activity: FragmentActivity) {
//        newsScope.launch {
//            newsList = convertNewsJsonToInstance(activity)
//        }
//    }
//
//    private fun convertNewsJsonToInstance(activity: FragmentActivity): ArrayList<News> {
//        val newsJSONString = getJSONFromAssets(activity, "news.json")
//        val moshi = Moshi.Builder().build()
//
//        val listType = Types.newParameterizedType(List::class.java, News::class.java)
//        val adapter = moshi.adapter<List<News>>(listType)
//
//        val newsFromJson = adapter.fromJson(newsJSONString)
//        return newsFromJson as ArrayList<News>
//    }

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
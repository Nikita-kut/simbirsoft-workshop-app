package com.nikita.kut.android.simbirsoft_workshop.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.nikita.kut.android.simbirsoft_workshop.data.HelpCategory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HelpViewModel : ViewModel() {

    var categories = arrayListOf<HelpCategory>()

    private val categoriesScope = CoroutineScope(Dispatchers.Default)

    private val database = FirebaseDatabase.getInstance().reference.child(CATEGORIES_DB_TAG)

    fun initCategoriesListFromDatabase() {
        database.addValueEventListener(
            object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.hasChild(CATEGORIES_DB_TAG)) {
                        categoriesScope.launch {
                            Log.d(
                                TAG_HELP_FRAGMENT,
                                "Coroutine inside from thread = ${Thread.currentThread().name}"
                            )
                            val memberList = snapshot.child(CATEGORIES_DB_TAG).value as ArrayList<*>
                            categories = getHelpCategoryList(memberList)
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
    }

    private fun getHelpCategoryList(helpCategoryList: ArrayList<*>): ArrayList<HelpCategory> {
        val size = helpCategoryList.size
        var index = 0
        val userList = ArrayList<HelpCategory>()

        while (index < size) {
            val hashMap: HashMap<*, *> = helpCategoryList[index] as HashMap<*, *>
            val id = hashMap["id"].toString().toLong()
            val name = hashMap["name"].toString()
            val image = hashMap["image"].toString()
            userList.add(HelpCategory(id, name, image))
            index++
        }
        return userList
    }

    // get data from JSON assets

//    fun initCategoriesFromJsonAssets(activity: FragmentActivity): ArrayList<HelpCategory> {
//        categoriesScope.launch {
//            Log.d(
//                NewsViewModel.TAG,
//                "Coroutine inside from thread = ${Thread.currentThread().name}"
//            )
//            categories = convertNewsJsonToInstance(activity)
//        }
//        return categories
//    }
//
//    private fun convertNewsJsonToInstance(activity: FragmentActivity): ArrayList<HelpCategory> {
//        val helpJSONString = getJSONFromAssets(activity, "categories.json")
//        val moshi = Moshi.Builder().build()
//
//        val listType = Types.newParameterizedType(List::class.java, HelpCategory::class.java)
//        val adapter = moshi.adapter<List<HelpCategory>>(listType)
//
//        val listCategory = adapter.fromJson(helpJSONString)
//        return listCategory as ArrayList<HelpCategory>
//    }

    companion object {
        const val CATEGORIES_DB_TAG = "categories"
        const val TAG_HELP_FRAGMENT = "help_fragment"
        const val SLEEP_TIME = 2000L
    }

}
package com.nikita.kut.android.simbirsoft_workshop.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.nikita.kut.android.simbirsoft_workshop.data.database.CategoriesDB
import com.nikita.kut.android.simbirsoft_workshop.model.HelpCategory
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HelpViewModel : ViewModel() {

    var categories = arrayListOf<HelpCategory>()

    private var firebaseListCategories: ArrayList<HelpCategory> = arrayListOf()

    private val firebaseDB = FirebaseDatabase.getInstance().reference.child(CATEGORIES_DB_TAG)

    private val categoriesDao = CategoriesDB.categoriesDBInstance.categoriesDao()

    fun getCategoriesList() {
        viewModelScope.launch {
            categoriesDao.getAllCategories().collect {
                categories = it as ArrayList<HelpCategory>
            }
        }
    }

    suspend fun insertCategoriesListFromFirebaseToRoom() {
        categoriesDao.insertCategories(initCategoriesListFromDatabase())
    }

    private fun initCategoriesListFromDatabase(): ArrayList<HelpCategory> {
        firebaseDB.addValueEventListener(
            object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.hasChild(CATEGORIES_DB_TAG)) {
                        viewModelScope.launch {
                            val memberList = snapshot.child(CATEGORIES_DB_TAG).value as ArrayList<*>
                            firebaseListCategories = getHelpCategoryList(memberList)
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
        return firebaseListCategories
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

    companion object {
        const val CATEGORIES_DB_TAG = "categories"
        const val TAG_HELP_FRAGMENT = "help_fragment"
        const val SLEEP_TIME = 2000L
    }

}
package com.nikita.kut.android.simbirsoft_workshop.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.nikita.kut.android.simbirsoft_workshop.model.HelpCategory
import com.nikita.kut.android.simbirsoft_workshop.model.News

@Dao
interface CategoriesDao {

    @Query("SELECT * FROM ${HelpCategory.TABLE_NAME}")
    suspend fun getAllCategories(): List<HelpCategory>

    @Insert
    suspend fun insertCategories(categories: List<HelpCategory>)
}
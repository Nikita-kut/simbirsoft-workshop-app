package com.nikita.kut.android.simbirsoft_workshop.data.database

import androidx.room.Dao
import androidx.room.Query
import com.nikita.kut.android.simbirsoft_workshop.model.News

@Dao
interface NewsDao {

    @Query("SELECT * FROM ${News.TABLE_NAME}")
    fun getAllNews(): List<News>
}
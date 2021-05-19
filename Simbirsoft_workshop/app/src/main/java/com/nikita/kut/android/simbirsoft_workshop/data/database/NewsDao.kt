package com.nikita.kut.android.simbirsoft_workshop.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nikita.kut.android.simbirsoft_workshop.model.HelpCategory
import com.nikita.kut.android.simbirsoft_workshop.model.News
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Query("SELECT * FROM ${News.TABLE_NAME}")
    fun getAllNews(): Flow<List<News>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategories(categories: List<News>)
}
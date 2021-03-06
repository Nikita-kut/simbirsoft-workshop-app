package com.nikita.kut.android.simbirsoft_workshop.app.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nikita.kut.android.simbirsoft_workshop.app.data.db.model.HelpCategory
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoriesDao {

    @Query("SELECT * FROM ${HelpCategory.TABLE_NAME}")
    fun getAllCategories(): Flow<List<HelpCategory>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategories(categories: List<HelpCategory>)
}
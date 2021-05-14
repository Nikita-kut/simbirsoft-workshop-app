package com.nikita.kut.android.simbirsoft_workshop.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nikita.kut.android.simbirsoft_workshop.data.database.DataBaseNews.Companion.DB_VERSION
import com.nikita.kut.android.simbirsoft_workshop.model.News

@Database(entities = [News::class], version = DB_VERSION)
abstract class DataBaseNews: RoomDatabase() {

    abstract fun newsDao() : NewsDao

    companion object {
        const val DB_VERSION = 1
        const val DB_NAME = "news-database"
    }
}
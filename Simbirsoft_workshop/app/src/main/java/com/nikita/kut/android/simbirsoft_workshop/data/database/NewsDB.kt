package com.nikita.kut.android.simbirsoft_workshop.data.database

import android.content.Context
import androidx.room.Room

object NewsDB {

    lateinit var newsDBInstance: DataBaseNews
        private set

    fun init(context: Context) {
        newsDBInstance =
            Room.databaseBuilder(context, DataBaseNews::class.java, DataBaseNews.DB_NAME).build()
    }
}
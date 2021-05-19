package com.nikita.kut.android.simbirsoft_workshop.data.database

import android.content.Context
import androidx.room.Room

object CategoriesDB {

    lateinit var categoriesDBInstance: DataBaseCategories
        private set

    fun init(context: Context) {
        categoriesDBInstance =
            Room.databaseBuilder(
            context,
            DataBaseCategories::class.java,
            DataBaseCategories.DB_NAME
        )
            .build()
    }

}
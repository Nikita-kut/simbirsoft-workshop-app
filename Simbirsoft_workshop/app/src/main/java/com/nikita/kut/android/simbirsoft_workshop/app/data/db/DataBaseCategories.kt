package com.nikita.kut.android.simbirsoft_workshop.app.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nikita.kut.android.simbirsoft_workshop.app.data.db.dao.CategoriesDao
import com.nikita.kut.android.simbirsoft_workshop.app.data.db.DataBaseCategories.Companion.DB_VERSION
import com.nikita.kut.android.simbirsoft_workshop.app.data.db.model.HelpCategory

@Database(entities = [HelpCategory::class], version = DB_VERSION)
abstract class DataBaseCategories : RoomDatabase() {

    abstract fun categoriesDao(): CategoriesDao

    companion object {
        const val DB_VERSION = 1
        const val DB_NAME = "categories-database"
    }
}
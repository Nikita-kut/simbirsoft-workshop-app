package com.nikita.kut.android.simbirsoft_workshop

import android.app.Application
import com.nikita.kut.android.simbirsoft_workshop.data.database.CategoriesDB
import com.nikita.kut.android.simbirsoft_workshop.data.database.NewsDB

class SimbirsoftWorkshopApp : Application() {

    override fun onCreate() {
        super.onCreate()
        CategoriesDB.init(this)
        NewsDB.init(this)
    }
}
package com.nikita.kut.android.simbirsoft_workshop.app

import android.app.Application
import com.nikita.kut.android.simbirsoft_workshop.app.data.db.CategoriesDB
import com.nikita.kut.android.simbirsoft_workshop.app.data.db.NewsDB

class SimbirsoftWorkshopApp : Application() {

    override fun onCreate() {
        super.onCreate()
        CategoriesDB.init(this)
        NewsDB.init(this)
    }
}
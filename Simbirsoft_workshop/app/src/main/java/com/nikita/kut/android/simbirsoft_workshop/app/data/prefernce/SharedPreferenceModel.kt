package com.nikita.kut.android.simbirsoft_workshop.app.data.prefernce

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.GsonBuilder

object SharedPreferenceModel {


    lateinit var preferences: SharedPreferences

    private const val PREFERENCES_FILE_NAME = "PREFERENCES_FILE_NAME"

    fun with(application: Application) {
        preferences = application.getSharedPreferences(PREFERENCES_FILE_NAME, Context.MODE_PRIVATE)
    }

    fun <T> put(myObject: T, key: String) {
        val jsonString = GsonBuilder().create().toJson(myObject)
        preferences.edit().putString(key, jsonString).apply()
    }

    inline fun <reified T> get(key: String): T? {
        val value = preferences.getString(key, null)
        return GsonBuilder().create().fromJson(value, T::class.java)
    }
}
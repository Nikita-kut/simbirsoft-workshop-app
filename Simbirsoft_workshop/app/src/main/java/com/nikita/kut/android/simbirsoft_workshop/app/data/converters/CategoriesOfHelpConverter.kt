package com.nikita.kut.android.simbirsoft_workshop.app.data.converters

import androidx.room.TypeConverter
import com.nikita.kut.android.simbirsoft_workshop.feature.news.model.CategoriesOfHelp

class CategoriesOfHelpConverter {

    @TypeConverter
    fun fromCategoriesOfHelp(value: List<CategoriesOfHelp>): String =
        value.joinToString(separator = ",") { it.name }

    @TypeConverter
    fun toCategoriesOfHelp(value: String): List<CategoriesOfHelp> =
        value.split(",").map { CategoriesOfHelp.valueOf(it) }.toList()

}